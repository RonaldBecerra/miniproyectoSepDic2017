package com.calclogic.service;

import com.calclogic.dao.ResuelveDAO;
import com.calclogic.dao.SolucionDAO;
import com.calclogic.entity.Resuelve;
import com.calclogic.entity.Solucion;
import com.calclogic.entity.Teorema;
import com.calclogic.parse.CombUtilities;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class has the implementation of "ResuelveManager" queries.
 *
 * @author miguel
 */
@Service
public class ResuelveManagerImpl implements ResuelveManager {
       
    @Autowired
    private ResuelveDAO resuelveDAO;
    
    @Autowired
    private SolucionDAO solucionDAO;
    
    @Autowired
    private CombUtilities combUtilities;
    
    /** 
     * Adds a new object entry to the table only if an equivalent one has not been added yet,
     * and if so then returns again the object.
     * Otherwise, it returns the equivalent object that was previously added.
     * @param resuelve The new Resuelve object to be added.
     */
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
    
    /**
     * Updates one of the Resuelve objects of the table.
     * @param resuelve Is the Resuelve object to be updated.
     * @return Nothing.
     */   
    @Override   
    @Transactional
    public void updateResuelve(Resuelve resuelve){
        resuelveDAO.updateResuelve(resuelve);
    }

    /**
     * Deletes one of the Resuelve objects of the table.
     * @param id Is the principal key of the Resuelve object to delete.
     * @return Nothing.
     */   
    @Override
    @Transactional
    public void deleteResuelve(int id){
        resuelveDAO.deleteResuelve(id);
    }
    
    /**
     * Method to get a Resuelve object by its principal key.
     * @param id Is the principal key of the Resuelve object.
     */ 
    @Override
    @Transactional
    public Resuelve getResuelve(int id){
        return resuelveDAO.getResuelve(id);
    }
    
    /**
     * Method to get a list of all the entries of the table.
     */
    @Override
    @Transactional
    public List<Resuelve> getAllResuelve(){
        return resuelveDAO.getAllResuelve();
    }

    /**
     * Method to get a list of all the entries of the table that correspond to a specific user.
     * @param userLogin Is the string with which the user logs in, and that we use to filter the search.
     */
    @Override
    @Transactional
    public List<Resuelve> getAllResuelveByUser(String userLogin){
        List<Resuelve> resuelves = resuelveDAO.getAllResuelveByUser(userLogin);
        return resuelves;
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelveByUserWithSol(String userLogin){
        List<Resuelve> resuelves = resuelveDAO.getAllResuelveByUser(userLogin);

        try {
            for (Resuelve resuelve : resuelves) {
                List<Solucion> sols = solucionDAO.getAllSolucionesByResuelve(resuelve.getId());
                resuelve.setDemopendiente(-1);                
                int numeroDeSols = 0;
                for (Solucion sol: sols)
                {
                   if (!sol.isResuelto()){
                      resuelve.setDemopendiente(sol.getId());
                   }
                   numeroDeSols ++;
                }
                
                if(resuelve.isResuelto() && numeroDeSols == 0){
                    resuelve.setEsAxioma(true);
                }
                else{
                    resuelve.setEsAxioma(false);
                    
                }
                
                Teorema teo = resuelve.getTeorema();
                teo.setTeoTerm(combUtilities.getTerm(teo.getEnunciado()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resuelves;
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelveByUserWithSolWithoutAxiom(String userLogin,String teoNum){
        List<Resuelve> resuelves = resuelveDAO.getAllResuelveByUserWithoutAxiom(userLogin,teoNum);//getAllResuelveByUser(userLogin);

        try {
            for (Resuelve resuelve : resuelves) {
                List<Solucion> sols = solucionDAO.getAllSolucionesByResuelve(resuelve.getId());
                resuelve.setDemopendiente(-1);                
                int numeroDeSols = 0;
                for (Solucion sol: sols)
                {
                   if (!sol.isResuelto()){
                      resuelve.setDemopendiente(sol.getId());
                   }
                   numeroDeSols ++;
                }
                
                if(resuelve.isResuelto() && numeroDeSols == 0){
                    resuelve.setEsAxioma(true);
                }
                else{
                    resuelve.setEsAxioma(false);
                    
                }
                
                Teorema teo = resuelve.getTeorema();
                teo.setTeoTerm(combUtilities.getTerm(teo.getEnunciado()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resuelves;
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelveByUserResuelto(String userLogin){
        List<Resuelve> resuelves = resuelveDAO.getAllResuelveByUserResuelto(userLogin);
        try {
            for (Resuelve resuelve : resuelves) {
                Teorema teo = resuelve.getTeorema();
                teo.setTeoTerm(combUtilities.getTerm(teo.getEnunciado()));
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
            teo.setTeoTerm(combUtilities.getTerm(teo.getEnunciado()));
            resuel.setTeorema(teo);
        }
        return resuel;
    }
    
    @Override
    @Transactional
    public Resuelve getResuelveByUserAndTeorema(String userLogin,String teo){
            return resuelveDAO.getResuelveByUserAndTeorema(userLogin, teo);
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
            teo.setTeoTerm(combUtilities.getTerm(teo.getEnunciado()));
            resuel.setTeorema(teo);
        }
        return resuel;
    }
}
