package com.calclogic.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private String login;
     @NotEmpty(message="You must not leave this field empty")
     private String nombre;
     private String apellido;
     private String correo;
     private String password;
     private Materia materia;
     private boolean admin;
     private boolean autosust;
     private Set predicados = new HashSet(0);
     private Set terminos = new HashSet(0);
     private Set dispones = new HashSet(0);
     private Set resuelves = new HashSet(0);

    public Usuario() {
    }


    public Usuario(String login, String nombre, String apellido, String correo, String password, boolean admin) {
        this.login = login;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.admin = admin;
	this.autosust = false;
    }
    
    public Usuario(String login, String nombre, String apellido, String correo, String password, Materia materia, boolean admin) {
        this.login = login;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.materia = materia;
        this.admin = admin;
	this.autosust = false;
    }
    public Usuario(String login, String nombre, String apellido, String correo, String password, Materia materia, boolean admin, Set predicados, Set terminos, Set dispones, Set resuelves) {
       this.login = login;
       this.nombre = nombre;
       this.apellido = apellido;
       this.correo = correo;
       this.password = password;
       this.materia = materia;
       this.admin = admin;
       this.predicados = predicados;
       this.terminos = terminos;
       this.dispones = dispones;
       this.resuelves = resuelves;
       this.autosust = false;
    }
   
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Materia getMateria() {
        return this.materia;
    }
    
    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    public boolean isAdmin() {
        return this.admin;
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAutosust() {
        return this.autosust;
    }

    public void setAutosust(boolean autosust) {
        this.autosust = autosust;
    }

    public Set getPredicados() {
        return this.predicados;
    }
    
    public void setPredicados(Set predicados) {
        this.predicados = predicados;
    }
    public Set getTerminos() {
        return this.terminos;
    }
    
    public void setTerminos(Set terminos) {
        this.terminos = terminos;
    }
    public Set getDispones() {
        return this.dispones;
    }
    
    public void setDispones(Set dispones) {
        this.dispones = dispones;
    }
    public Set getResuelves() {
        return this.resuelves;
    }
    
    public void setResuelves(Set resuelves) {
        this.resuelves = resuelves;
    }
}

