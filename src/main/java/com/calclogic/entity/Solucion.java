package com.calclogic.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA

import com.calclogic.lambdacalculo.App;
import com.calclogic.lambdacalculo.Const;
import com.calclogic.lambdacalculo.PasoInferencia;
import com.calclogic.lambdacalculo.Term;
import com.calclogic.lambdacalculo.TypedA;
import com.calclogic.lambdacalculo.TypedApp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.springframework.util.SerializationUtils;

/**
 * Solucion generated by hbm2java
 *
 * This table has the solutions (or attempted solutions)
 * to the demonstrations of theorems.
 */
public class Solucion implements java.io.Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "solucion_id_seq")
    @SequenceGenerator(name = "solucion_id_seq", sequenceName = "solucion_id_seq")
    private int id;
    //private List<PasoInferencia> arregloInferencias = new ArrayList<PasoInferencia>();
    private Term typedTerm;
    private Resuelve resuelve;
    private boolean resuelto;
    private String demostracion;
    private String metodo;

    /*public String getNteoinicial() {
        return nteoinicial;
    }

    public void setNteoinicial(String nteoinicial) {
        this.nteoinicial = nteoinicial;
    }*/

    public Solucion() {
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public boolean isResuelto() {
        return resuelto;
    }
    
    public void setTypedTerm(Term typedTerm)
    {
        this.typedTerm = typedTerm;
        this.demostracion = (typedTerm!=null?typedTerm.toStringFinal():"");
    }
    
    public Term getTypedTerm()
    {
        return typedTerm;
    }
    
    public String getDemostracion() {
    	return demostracion;
    }
    
    public String getMetodo() {
	return metodo;
    }

    public void setMetodo(String metodo) {
	this.metodo = metodo;
    }
    
    public Solucion(Term typeTerm) {
        this.typedTerm = typeTerm;
    }
    
    public Solucion(Resuelve resuelve, boolean resuelto, Term typeTerm, String metodo) {
        this.resuelve = resuelve;
        this.resuelto = resuelto;
        this.typedTerm = typeTerm;
        this.demostracion = (typeTerm != null?typeTerm.toStringFinal():"");
        this.metodo = metodo;
    }

   /* public Solucion(PasoInferencia paso) {
        this.arregloInferencias.add(paso);
        this.resuelto = false;
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
//        this.deserialize();

    }

    public Solucion(Resuelve resuelve, PasoInferencia paso) {
        this.resuelve = resuelve;
        this.resuelto = false;
        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
//        this.deserialize();

    }
    
    public Solucion(Resuelve resuelve, boolean resuelto, PasoInferencia paso) {
        this.resuelve = resuelve;
        this.resuelto = resuelto;
        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
//        this.deserialize();

    }*/
    

    public void setId(int id) {
        this.id = id;
    }

    /*public void setArregloInferencias(List<PasoInferencia> arregloInferencias) {
        this.arregloInferencias = arregloInferencias;
//        this.arregloSerializado = SerializationUtils.serialize(arregloInferencias);
        this.serialize();
//        this.deserialize();
    }*/

    public void setResuelve(Resuelve resuelve) {
        this.resuelve = resuelve;
    }
    
    public void setDemostracion(String demostracion) {
    	this.demostracion = demostracion;
    }

    public int getId() {
        return id;
    }

    /*public List<PasoInferencia> getArregloInferencias() {
        return arregloInferencias;
    }*/

    public Resuelve getResuelve() {
        return resuelve;
    }
    
    public boolean getResuelto() {
        return resuelto;
    }
    
    public boolean thereIsAxiom(String axiom) {
        List<String> l = new ArrayList<String>();
        typedTerm.getAxioms(l);
        
        return false;
    }

    /*public void addArregloInferencias(PasoInferencia paso) {
        this.deserialize();
        List<PasoInferencia> newArray;// = new ArrayList<PasoInferencia>();
        newArray = getArregloInferencias();
        newArray.add(paso);
        setArregloInferencias(newArray);
        this.serialize();
    }

    public void serialize() {
//        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        List<byte[]> newArray = new ArrayList<byte[]>();
        for (PasoInferencia x : this.getArregloInferencias()) {
            newArray.add(SerializationUtils.serialize(x.getExpresion()));
            newArray.add(SerializationUtils.serialize(x.getTeoIzq()));
            newArray.add(SerializationUtils.serialize(x.getTeoDer()));
            newArray.add(SerializationUtils.serialize(x.getLeibniz()));
            newArray.add(SerializationUtils.serialize(x.getInstancia()));
            newArray.add(SerializationUtils.serialize(x.getResult()));            
        }

        this.arregloSerializado = SerializationUtils.serialize(newArray);

    }*/

    public int retrocederPaso(){
    
            /*int tam = this.arregloInferencias.size();
            this.deserialize();
            if(tam>0){
                this.arregloInferencias.remove(tam - 1);
            }
            this.serialize();*/
     //       System.out.println(typedTerm.toStringInfFinal());
            if (typedTerm.type() == null){
                typedTerm=null;
                demostracion = "";
                return 0;
            }
            if ( (
                  !metodo.equals("WE") && !metodo.equals("ST") &&
                  !metodo.equals("TR") &&
                  typedTerm instanceof App && ((App)typedTerm).p.containTypedA()
                 ) 
                 ||
                  // next line check if is a no one step proof 
                 (
                   (metodo.equals("WE") || 
                    metodo.equals("ST") || 
                    metodo.equals("TR")
                   ) 
                    &&
                   (
                    (typedTerm instanceof TypedApp && ((TypedApp)typedTerm).inferType=='t') ||
                    (typedTerm instanceof TypedApp && ((TypedApp)typedTerm).inferType=='m' &&
                     ((TypedApp)typedTerm).p instanceof TypedApp && 
                     ((TypedApp)((TypedApp)typedTerm).p).inferType=='m' && 
                     ((TypedApp)((TypedApp)typedTerm).p).p instanceof TypedApp &&
                     ((TypedApp)((TypedApp)((TypedApp)typedTerm).p).p).inferType=='i'
                    )
                   )
                 )
               )
            {
                if ((metodo.equals("WE") || 
                     metodo.equals("ST") ||
                     metodo.equals("TR")
                    ) 
                    && 
                    !(typedTerm instanceof TypedApp && ((TypedApp)typedTerm).inferType=='t')    
                   ) 
                {
                    typedTerm = ((App)((App)typedTerm).p).q;
                    if (typedTerm instanceof TypedApp && ((TypedApp)typedTerm).inferType=='e' &&
                        ((TypedApp)typedTerm).p instanceof TypedApp && 
                        ((TypedApp)((TypedApp)typedTerm).p).inferType=='s'     
                       )
                        typedTerm = ((TypedApp)typedTerm).q;
                }
                else{
                    typedTerm = ((App)typedTerm).p;
                    if (typedTerm instanceof TypedApp && ((TypedApp)typedTerm).inferType=='e' &&
                        ((TypedApp)typedTerm).p instanceof TypedApp && 
                        ((TypedApp)((TypedApp)typedTerm).p).inferType=='s'     
                       )
                        typedTerm = ((TypedApp)typedTerm).q;
                    
                }
                demostracion = typedTerm.toStringFinal();
     //           System.out.println(typedTerm.toStringInfFinal());
     //           System.out.println("2");
                return 2;
            }
            else
            {
                typedTerm = ((App)typedTerm.type()).q;
                demostracion = typedTerm.toStringFinal();
     //           System.out.println(typedTerm.toStringInfFinal());
     //           System.out.println("1");
                return 1;
            }
    }

}
