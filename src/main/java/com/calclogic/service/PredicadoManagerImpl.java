/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calclogic.service;

import com.calclogic.dao.PredicadoDAO;
import com.calclogic.entity.Predicado;
import com.calclogic.entity.PredicadoId;
import com.calclogic.lambdacalculo.App;
import com.calclogic.lambdacalculo.Term;
import com.calclogic.lambdacalculo.Var;
import com.calclogic.parse.CombUtilities;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

/**
 *
 * @author miguel
 */
@Service
public class PredicadoManagerImpl implements PredicadoManager {
       
    @Autowired
    private PredicadoDAO predicadoDAO;
    
    //@Autowired
    //private CombUtilities combUtilities;
    
    
    @Override
    @Transactional
    public void addPredicado(Predicado predicado){
        predicadoDAO.addPredicado(predicado);
    }
    
    @Override
    @Transactional
    public void deletePredicado(PredicadoId id){
        predicadoDAO.deletePredicado(id);
    }
    
    
    @Override
    @Transactional
    public Predicado getPredicado(PredicadoId id){
        Predicado p=predicadoDAO.getPredicado(id);
        if(p!=null)
        {
            p.setTerm(CombUtilities.getTerm(p.getPredicado()));    
        }
        return  p;
    }
    
    public Predicado getPredicado(String username, String comb) {
        Predicado t=predicadoDAO.getPredicado(username, comb);
        if(t != null)
        {
            t.setTerm(CombUtilities.getTerm(t.getPredicado()));    
            return t;
        }
        
        t=predicadoDAO.getPredicado("AdminTeoremas", comb);
        if(t != null)
            return t;
        
        return t;
    }
    
    @Override
    @Transactional
    public void updatePredicado(Predicado pre) {
        predicadoDAO.updatePredicado(pre);
    }
    
    @Override
    @Transactional
    public void modificarAlias(PredicadoId anterior, PredicadoId nuevo) {
        Predicado pre=getPredicado(anterior);
        predicadoDAO.deletePredicado(anterior);
        pre.setId(nuevo);
        Term aux=pre.getTerm();
        aux.setAlias(nuevo.getAlias());
        pre.setTerm(aux);
        predicadoDAO.addPredicado(pre);
    }
    
    @Override
    @Transactional
    public List<Predicado> getAllPredicadosByUser(String userLogin){
        List<Predicado> pres=predicadoDAO.getAllPredicadosByUser(userLogin);
        try
        {
            for(Predicado pre: pres) {
                String[] args = pre.getArgumentos().split(",");
                Term aux = CombUtilities.getTerm(pre.getPredicado());
                for (int i=0; i < args.length; i++) 
                    aux=new App(aux,new Var(args[i].split("@")[0].trim().charAt(0)));
                aux = aux.evaluar();
                pre.setTerm(aux);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return pres;
    }
}
