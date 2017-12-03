/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.ResuelveDAO;
import com.howtodoinjava.dao.SolucionDAO;
import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.entity.Teorema;
import com.howtodoinjava.lambdacalculo.App;
import com.howtodoinjava.lambdacalculo.Const;
import com.howtodoinjava.lambdacalculo.Term;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

/**
 *
 * @author miguel
 */
@Service
public class ResuelveManagerImpl implements ResuelveManager {
       
    @Autowired
    private ResuelveDAO resuelveDAO;
    
    @Autowired
    private SolucionDAO solucionDAO;
    
    @Override
    @Transactional
    public Resuelve addResuelve(Resuelve resuelve){
        Resuelve res = this.getResuelveByUserAndTeorema(resuelve.getUsuario().getLogin(),resuelve.getTeorema().getId());
        if (res != null){
            return res;
        }
        resuelveDAO.addResuelve(resuelve);
        return resuelve;
    }
    
    
    @Override   
    @Transactional
    public void updateResuelve(Resuelve resuelve){
        resuelveDAO.updateResuelve(resuelve);
    }
    
    @Override
    @Transactional
    public void deleteResuelve(int id){
        resuelveDAO.deleteResuelve(id);
    }
    
    @Override
    @Transactional
    public Resuelve getResuelve(int id){
        return resuelveDAO.getResuelve(id);
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelve(){
        return resuelveDAO.getAllResuelve();
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelveByUser(String userLogin){
        List<Resuelve> resuelves = resuelveDAO.getAllResuelveByUser(userLogin);
        try {
            for (Resuelve resuelve : resuelves) {
                List<Solucion> sols = solucionDAO.getAllSolucionesByResuelve(resuelve.getId());
                resuelve.setDemopendiente(-1);                
                
                for (Solucion sol: sols)
                {
                   if (!sol.isResuelto())
                      resuelve.setDemopendiente(sol.getId());
                }
                
                if(resuelve.isResuelto()==true && resuelve.getDemopendiente()== -1){
                    resuelve.setEsAxioma(true);
                }
                else{
                    resuelve.setEsAxioma(false);
                    
                }
                
                Teorema teo = resuelve.getTeorema();
                teo.setTeoTerm((Term) SerializationUtils.deserialize(teo.getTeoserializado()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resuelves;
    }
    
    @Override
    @Transactional
    public List<Resuelve> getResuelveByTeorema(int teoremaID){
        return resuelveDAO.getResuelveByTeorema(teoremaID);
    }

    @Override
    @Transactional
    public Resuelve getResuelveByUserAndTeorema(String userLogin,int teoremaID){
        Resuelve resuel = resuelveDAO.getResuelveByUserAndTeorema(userLogin, teoremaID);
        if (resuel != null) {
            Teorema teo = resuel.getTeorema();
            teo.setTeoTerm((Term) SerializationUtils.deserialize(teo.getTeoserializado()));
            resuel.setTeorema(teo);
        }
        return resuel;
    }
    
    @Override
    @Transactional
    public Resuelve getResuelveByUserAndTeoNum(String userLogin,String teoNum){
        Resuelve resuel = resuelveDAO.getResuelveByUserAndTeoNum(userLogin, teoNum);
        if (resuel != null) {
            List<Solucion> sols = solucionDAO.getAllSolucionesByResuelve(resuel.getId());
            resuel.setDemopendiente(-1);
            for (Solucion sol: sols)
            {
                if (!sol.isResuelto())
                    resuel.setDemopendiente(sol.getId());
            }
            Teorema teo = resuel.getTeorema();
            teo.setTeoTerm((Term) SerializationUtils.deserialize(teo.getTeoserializado()));
            resuel.setTeorema(teo);
        }
        return resuel;
    }
}
