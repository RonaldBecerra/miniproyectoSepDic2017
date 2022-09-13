package com.calclogic.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA

import com.calclogic.lambdacalculo.App;
import com.calclogic.lambdacalculo.Const;
import com.calclogic.lambdacalculo.Term;
import com.calclogic.lambdacalculo.TypedApp;
import com.calclogic.lambdacalculo.TypeVerificationException;
import com.calclogic.proof.CrudOperations;
import com.calclogic.proof.ProofBoolean;
import com.calclogic.parse.CombUtilities;
import com.calclogic.proof.GenericProofMethod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.apache.commons.lang3.text.StrSubstitutor;

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

    private Term typedTerm;
    private Resuelve resuelve;
    private boolean resuelto;
    private String demostracion;
    private String metodo;
    private CrudOperations proofCrudOperations;


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
        this.demostracion = (typedTerm!=null?typedTerm.toString():"");
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
    
    public Solucion(Resuelve resuelve, boolean resuelto, Term typeTerm, String metodo,
                    CrudOperations proofCrudOperations) {
        this.resuelve = resuelve;
        this.resuelto = resuelto;
        this.typedTerm = typeTerm;
        this.demostracion = (typeTerm != null?typeTerm.toString():"");
        this.metodo = metodo;
        this.proofCrudOperations = proofCrudOperations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResuelve(Resuelve resuelve) {
        this.resuelve = resuelve;
    }
    
    public void setDemostracion(String demostracion) {
    	this.demostracion = demostracion;
    }
    
    public void setProofCrudOperations(CrudOperations proofCrudOperations) {
        this.proofCrudOperations = proofCrudOperations;
    }

    public int getId() {
        return id;
    }

    public Resuelve getResuelve() {
        return resuelve;
    }
    
    public boolean getResuelto() {
        return resuelto;
    }
    
    private Term branchedOneLineSubProof(Term formula,Term father) {
        Map<String,String> values1 = new HashMap<>();
        values1.put("ST1",new App(new App(new Const(1,"c_{1}"),formula),formula).toString());
        values1.put("ST2", formula.toString());
        StrSubstitutor sub1 = new StrSubstitutor(values1, "%(",")");
        String metaTheoT= "S (I^{[x_{113} := %(ST1)]} A^{c_{1} x_{113} (c_{1} x_{113} c_{8})}) (L^{\\lambda x_{122}.%(ST2)} A^{c_{1} x_{113} x_{113}})";
        String metaTheo = sub1.replace(metaTheoT);

        try {
            Term newProof = new TypedApp(((App)((App)((App)father).p).q).p, CombUtilities.getTerm(metaTheo));
            newProof = new TypedApp(new TypedApp(((App)((App)father).p).p, newProof),((App)father).q);
            return newProof;
        }
        catch (TypeVerificationException e) {
            Logger.getLogger(Solucion.class.getName()).log(Level.SEVERE, null, e);
            return father;
        }
    }
    
    private Term mergeSubProofs(Term subProof, List<Term> fathers, GenericProofMethod objectMethod) {
        if (fathers.size()==1){
            return subProof;
        }
        else {
            Term auxProof;
            int i;
            if (subProof==null || subProof.type()==null) {
                i=2;
                auxProof =(subProof==null?((TypedApp)fathers.get(1)).q:branchedOneLineSubProof(subProof,fathers.get(1)));
            }
            else {
                i=1;
                auxProof = subProof;
            }
            while (i < fathers.size()) {
                auxProof = objectMethod.finishedMethodProof(fathers.get(i), auxProof);
                i++;
            }
            return auxProof;
        }
    }
    
    public boolean thereIsAxiom(String axiom) {
        List<String> l = new ArrayList<>();
        typedTerm.getAxioms(l);
        
        return false;
    }

    public int retrocederPaso(Term methodTerm, GenericProofMethod objectMethod){
        String currentGroupMethod = objectMethod.getGroupMethod();
        List<Term> li = new ArrayList<>();
        li = proofCrudOperations.getFatherAndSubProof(typedTerm,methodTerm,li);
        Term auxTypedTerm = li.get(0);

        if (ProofBoolean.isOneLineProof(auxTypedTerm)){
            typedTerm= mergeSubProofs(null, li, objectMethod);
            demostracion =(typedTerm==null?"":typedTerm.toString());
            return 0;
        }
        if ( (
              !currentGroupMethod.equals("T") &&
              auxTypedTerm instanceof App && ((App)auxTypedTerm).p.containTypedA()
             ) 
             ||
              // next line checks if it is a no one step proof 
             (
                currentGroupMethod.equals("T")
                &&
                (
                    (auxTypedTerm instanceof TypedApp && ((TypedApp)auxTypedTerm).inferType=='t') ||
                    (auxTypedTerm instanceof TypedApp && ((TypedApp)auxTypedTerm).inferType=='m' &&
                        ((TypedApp)auxTypedTerm).p instanceof TypedApp && 
                        ((TypedApp)((TypedApp)auxTypedTerm).p).inferType=='m' && 
                        ((TypedApp)((TypedApp)auxTypedTerm).p).p instanceof TypedApp &&
                        ((TypedApp)((TypedApp)((TypedApp)auxTypedTerm).p).p).inferType=='i'
                    )
                )
             )
           )
        {
            if (currentGroupMethod.equals("T")&& 
                !(auxTypedTerm instanceof TypedApp && ((TypedApp)auxTypedTerm).inferType=='t')    
               ) 
            {
                typedTerm = mergeSubProofs(((App)((App)auxTypedTerm).p).q, li, objectMethod);
                if (auxTypedTerm instanceof TypedApp && ((TypedApp)auxTypedTerm).inferType=='e' &&
                        ((TypedApp)auxTypedTerm).p instanceof TypedApp && 
                        ((TypedApp)((TypedApp)auxTypedTerm).p).inferType=='s'     
                   )
                {
                    typedTerm = mergeSubProofs(((TypedApp)auxTypedTerm).q, li, objectMethod);
                }
            }
            else {
                typedTerm = mergeSubProofs(((App)auxTypedTerm).p, li, objectMethod);
                if (auxTypedTerm instanceof TypedApp && ((TypedApp)auxTypedTerm).inferType=='e' &&
                        ((TypedApp)auxTypedTerm).p instanceof TypedApp && 
                        ((TypedApp)((TypedApp)auxTypedTerm).p).inferType=='s'     
                   )
                {
                    typedTerm = mergeSubProofs(((TypedApp)auxTypedTerm).q, li, objectMethod);
                }
                
            }
            demostracion = typedTerm.toString();
            return 2;
        }
        else {
            typedTerm = mergeSubProofs(((App)auxTypedTerm.type()).q, li, objectMethod);
            demostracion = typedTerm.toString();
            return 1;
        }
    }
}
