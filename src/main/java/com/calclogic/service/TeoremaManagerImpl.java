package com.calclogic.service;
import com.calclogic.dao.TeoremaDAO;
import com.calclogic.dao.ResuelveDAO;
import com.calclogic.entity.Resuelve;
import com.calclogic.entity.Teorema;
import com.calclogic.lambdacalculo.Term;
import com.calclogic.parse.CombUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

/**
 * This class has the implementation of "TeoremaManager" queries.
 *
 * @author miguel
 */
@Service
public class TeoremaManagerImpl implements TeoremaManager {

    @Autowired
    private TeoremaDAO teoremaDAO;
    @Autowired
    private ResuelveDAO resuelveDAO;
    
    //@Autowired
    //private CombUtilities combUtilities;

    /** 
     * Adds a new theorem to the table.
     * @param teorema The new theorem to be added.
     * @return Nothing.
     */
    @Override
    @Transactional
    public Teorema addTeorema(Teorema teorema) {
        // Este teorema sera utilizado para ver si ya existe en la BD
        Teorema teorema2 = this.getTeoremaByEnunciados(teorema.getEnunciado().toString());
        if (teorema2 != null) {
            return teorema2;
        } /*else {
            // Este teorema sera utilizado para ver si el inverso ya existe en la BD
            Teorema teorema3 = this.getTeoremaByEnunciados(teorema.getEnunciadoder().toString(), teorema.getEnunciadoizq().toString());
            if (teorema3 != null) {
                return teorema3;
            }
        }*/
        teoremaDAO.addTeorema(teorema);
        return teorema;
    }

	/**
     * Deletes one of the theorems of the table.
     * @param id Is the principal key of the theorem to delete.
     * @return Nothing.
     */ 
    @Override
    @Transactional
    public void deleteTeorema(int id) {

        // Si solo hay 1 usuario usandolo, entonces aplica teoremaDAO.deleteTeorema(id)
        teoremaDAO.deleteTeorema(id);
    }

	/**
     * Method to get a theorem by its principal key.
	 * If it exists, it parses the string associated with the object.
     * @param id Is the principal key of the theorem.
     */
    @Override
    @Transactional
    public Teorema getTeorema(int id) {
        Teorema teo = teoremaDAO.getTeorema(id);
        if (teo != null) {
            teo.setTeoTerm(CombUtilities.getTerm(teo.getEnunciado()));
        }
        return teo;
    }

    /**
     * Method to get a list of all the entries of the table (all the theorems),
	 * and parsing them in order to be used.
     */
    @Override
    @Transactional
    public List<Teorema> getAllTeoremas() {
        List<Teorema> teoList = teoremaDAO.getAllTeoremas();
        try {
            for (Teorema teo : teoList) {
                //ter.setTermObject((Term)ToString.fromString(ter.getSerializado()));
                teo.setTeoTerm(CombUtilities.getTerm(teo.getEnunciado()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teoList;
    }

    /**
     * Method to get a theorem that corresponds to a statement, and then parsing it.
     * @param enunciado Is the statement used to filter the search.
     */
    @Override
    @Transactional
    public Teorema getTeoremaByEnunciados(String enunciado) {
        Teorema teo = teoremaDAO.getTeoremaByEnunciados(enunciado);
        if (teo != null) {
            teo.setTeoTerm(CombUtilities.getTerm(teo.getEnunciado()));
        }
        return teo;
    }

    /**
     * Method to get a list of theorems that correspond 
	 * to a list of Resuelve objects, and then parsing them.
     * @param resList Is the list of Resuelve objects used to filter the search.
     */
    @Override
    @Transactional
    public List<Teorema> getTeoremaByResuelveList(List<Resuelve> resList) {
        List<Teorema> teoList = new ArrayList<Teorema>();
        Teorema teorema;
        
        Collections.sort(resList, new ResuelveComparator());
        
        for (Resuelve res : resList) {
            teorema = res.getTeorema();
            teorema.setTeoTerm(CombUtilities.getTerm(teorema.getEnunciado()));
            teoList.add(teorema);
        }

        return teoList;
    }

    /**
     * Auxiliar class to implement a method that compares two Resuelve objects.
     */
    class ResuelveComparator implements Comparator<Resuelve> {

		/**
		 * Method that takes two Resuelve objects and returns the arithmetic difference
		 * of the id's of their categories.
		 * @param res1 Minuend of the difference.
		 * @param res2 Subtrahend of the difference.
		 */
        public int compare(Resuelve res1, Resuelve res2) {
            return res1.getCategoria().getId() - res2.getCategoria().getId();
        }
    }

    /**
     * Method to get a list of theorems that corresponds to a specific category.
     * @param categoriaId Is the principal key of the category (Categoria object).
     */
    @Override
    @Transactional
    public List<Teorema> getTeoremasByCategoria(int categoriaId) {
        List<Resuelve> res = resuelveDAO.getResuelveByCategoria(categoriaId);
        List<Teorema> teos = new ArrayList<Teorema>();
        for (int i=0;i<=res.size();i++){
            teos.add(res.get(i).getTeorema());
        }
        if (teos != null) {
            for (Teorema teo : teos){
               teo.setTeoTerm(CombUtilities.getTerm(teo.getEnunciado()));
            }
        }
        return teos;
    }
}
