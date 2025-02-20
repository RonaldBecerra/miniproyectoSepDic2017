package com.calclogic.dao;

import com.calclogic.entity.Categoria;
import com.calclogic.entity.MostrarCategoria;
import com.calclogic.entity.Usuario;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class has the implementation of the database queries that 
 * have to do with the table "Metateorema".
 *
 * That table implements the relation between the users and
 * the categories (objects of the "Categoria" table) that they
 * can see.
 *
 * @author jt
 */
@Repository
public class MostrarCategoriaDaoImpl implements MostrarCategoriaDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /** 
     * Adds a new MostrarCategoria object to the table.
     * This query is made using standard Hibernate library functions.
     * @param MostrarCategoria The new MostrarCategoria object to be added.
     * @return Nothing.
     */
    @Override   
    @Transactional
    public void addMostrarCategoria(MostrarCategoria MostrarCategoria){
        this.sessionFactory.getCurrentSession().save(MostrarCategoria);
    }
    
    /**
     * Deletes one of the MostrarCategoria objects of the table.
     * This query is made using standard Hibernate library functions.
     * @param id Is the principal key of the MostrarCategoria object to delete.
     * @return Nothing.
     */ 
    @Override
    @Transactional
    public void deleteMostrarCategoria(int id){
        MostrarCategoria MostrarCategoria = (MostrarCategoria) sessionFactory.getCurrentSession().load(
				MostrarCategoria.class, id);
        if (null != MostrarCategoria) {
        	this.sessionFactory.getCurrentSession().delete(MostrarCategoria);
        }
    }
    
    /**
     * Method to get a MostrarCategoria object by its principal key.
     * This query is made using standard Hibernate library functions.
     * @param id Is the principal key of the MostrarCategoria object.
     */
    @Override
    @Transactional
    public MostrarCategoria getMostrarCategoria(int id){
        return (MostrarCategoria)this.sessionFactory.getCurrentSession().get(MostrarCategoria.class,id);
    }
    
    /**
     * Method to get a list of all the entries of the table.
     * This query is made using classic SQL.
     */
    @Override
    @Transactional
    public List<MostrarCategoria> getAllMostrarCategorias(){
        return this.sessionFactory.getCurrentSession().createQuery("FROM MostrarCategoria").list();
    }
    
    /**
     * Method to get a list of all the entries of the table that correspond to a specific user.
     * This query is made using classic SQL.
     * @param usuario Is the Usuario object used to filter the search.
     */
    @Override
    @Transactional
    public List<MostrarCategoria> getAllMostrarCategoriasByUsuario(Usuario usuario){
        return this.sessionFactory.getCurrentSession().createQuery("FROM MostrarCategoria WHERE usuariologin = :usuariologin ORDER BY categoriaid").setParameter("usuariologin",usuario.getLogin()).list();
    }
    
    /**
     * Method to get an entry of the table that relates a category to a user.
     * This query is made using classic SQL.
     * @param categoria Is the Categoria object used to filter the search.
     * @param usuario Is the Usuario object used to filter the search.
     */
    @Override
    @Transactional
    public MostrarCategoria getMostrarCategoriaByCategoriaAndUsuario(Categoria categoria,Usuario usuario){
        return (MostrarCategoria)this.sessionFactory.getCurrentSession().createQuery("FROM MostrarCategoria WHERE usuariologin = :usuariologin AND categoriaid = :categoriaid ORDER BY categoriaid").setParameter("usuariologin", usuario.getLogin()).setParameter("categoriaid",categoria.getId()).list().get(0);
    }
}