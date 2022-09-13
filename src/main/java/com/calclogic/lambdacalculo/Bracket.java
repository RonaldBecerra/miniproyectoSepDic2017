/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calclogic.lambdacalculo;

import com.calclogic.service.PredicadoManager;
import com.calclogic.service.SimboloManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author federico
 */
public class Bracket extends Term{
    final Var x;
    public Term t;
    public String tipo;

    public Bracket(Var x, Term t, String tipo) {
        this.x = x;
        this.t = t;
        this.tipo = tipo;
    }
    
    
    public Bracket(Var x1,Term t1)
    {
        x=x1;
        t=t1;
    }
    
    public int x()
    {
        return x.indice;
    }
    
    public boolean occur(Var y)
    {
         if(x.equals(y))
             return false;
         else
             return t.occur(y);
    }
    
    public String position(Var y)
    {
         if(x.equals(y))
             return "3";
         else {
            if (t.occur(y))
             return "1"+t.position(y);
            else
             return "3";
         }
    }
    
    @Override
    public Term subterm(String position) {
        if (position.equals(""))
           return this;
        else if (position.charAt(0) == '1')
           return subterm(position.substring(1));
        else
           return null;
    }
    
    public Term sust(Var x,Term t)
    {
        return null;
    }
    
    public Term type()
    {
        return null;
    }
    
    public boolean containTypedA()
    {
        return t.containTypedA();
    }
    
    public void getAxioms(List<String> l)
    {
        t.getAxioms(l);
    }
    
    public Term leibniz(int z, Term subterm)
    {
       if (this == subterm)
           return new Var(z);
       else
           return new Bracket(x,t.leibniz(z, subterm));
    }
    
    public boolean isIdFunction() {
       return t.equals(x);
    }
    
    public int setAlias(int currentAlia)
    {
        if(t.alias != null)
        {
            t.alias = t.alias+"@"+currentAlia;
            currentAlia++;
        }
        
        currentAlia = t.setAlias(currentAlia);
        return currentAlia;
    }
    
    public Term bracketAbsSH(Var y)
    {
        return t.bracketAbsSH(x).bracketAbsSH(y);
    }
    
    public Term bracketAbsBD(Var y)
    {
        return t.bracketAbsBD(x).bracketAbsBD(y);
    }
    
    public Term bracketAbsBD()
    {
        return t.bracketAbsBD(x);
    }
    
    public Term traducBD()
    {
        return t.traducBD().bracketAbsBD(x);
    }
    
    public List<Term> contandotraducBD()
    {
        List<Term> list=t.contandotraducBD();
        for (int i=0; i<list.size(); i++) {
            Var xaux = new Var(x.indice);
            list.set(i,new Bracket(xaux, list.get(i)));            
        }
        list.add(t.traducBD().bracketAbsBD(x));
        
        return list;
    }
    
    public int maxVar()
    {
        return t.maxVar();
    }
    
    public Tipo esRedex()
    {
        return new Tipo(false,false);
    }
    
     public Tipo esRedexFinal()
    {
        return new Tipo(false,false,false);
    }
    
    public Redex buscarRedexIzq(Term contexto,boolean p)
    {
        return t.buscarRedexIzq(this,false);
    }
    
    public Redex buscarRedexIzqFinal(Term contexto,boolean p)
    {
        return t.buscarRedexIzqFinal(this,false);
    }
    
    public Term invBraBD()
    {
        return this;
    }
    
    public Term invBD()
    {
        Term term = new Bracket(x,t.invBD());
        term.alias = this.alias;
        return term;
    }
    
    public Term invBDOneStep()
    {
        return new Bracket(x,t.invBDOneStep());
    }
    
    public String toStringAll()
    {   
        if(t.alias == null)
            return "(\\lambda "+x.toStringAll()+"."+t.toString()+")";
        else{
            return "(\\lambda "+x.toStringLaTeXFinal(null)+"."+t.alias +")";
            //return "(\\lambda "+x.toString()+"."+t.alias.split("@")[0].replace("_", "\\_") +")";
        }
    }
    
    @Override
    public String toStringLaTeX(SimboloManager s,String numTeo)
    {
        if(t.alias == null) {
            //FALTA IMPLEMENTAR FINAL
            return "E: "+t.toStringLaTeX(s,"");
        }
        else {
            return "E: "+t.alias ;
        }
    }
    
    @Override
    public String toStringLaTeXLabeled(SimboloManager s,int z, Term t, List<Term> leibniz, 
                                     List<String> l2, Id id, int nivel){
        id.id++;
        leibniz.add(t.leibniz(z, this));
        l2.add(l2.remove(0)+id.id+",");
        if(t.alias == null) {
            //FALTA IMPLEMENTAR FINAL
            return "\\cssId{"+(id.id-1)+"}{\\class{"+nivel+" terminoClick}{(\\lambda "+x.toStringLaTeXFinal(null)+"."+t.toStringLaTeXFinal(null)+")}}";
        }
        else {
            return "\\cssId{"+(id.id-1)+"}{\\class{"+nivel+" terminoClick}{(\\lambda "+x.toStringLaTeXFinal(null)+"."+t.alias +")}}";
        }
    }
    
    @Override
    public String toStringFormatC(SimboloManager s, String pos, int id, String rootId)
    {
        char ascii = (char) x.indice; 
        return "(\\lambda "+ascii+"."+t.toStringFormatC(s,pos,id,rootId)+")";
    }
    
    @Override
    public String toStringLaTeXWithInputs(SimboloManager s, String position, String rootId) {
        char ascii = (char) x.indice; 
        return "(E^{"+ascii+"}:"+t.toStringLaTeXWithInputs(s,position,rootId)+")";
    }
    
    @Override
    public ToString toStringAbrv(ToString toString)
    {
        if(t.alias == null)
        {
            t.toStringAbrvFinal(toString);
            toString.term= "(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        else
        {
            toString.setNuevoAlias(t.alias, t);
            toString.term="(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        
    }
    
    @Override
    public ToString toStringLaTeXAbrv(ToString toString,SimboloManager s, PredicadoManager p,String nTeo)
    {
        if(t.alias == null)
        {
            t.toStringLaTeXAbrvFinal(toString);
            toString.term= "(\\lambda "+x.toStringLaTeX(s,"")+"."+toString.term+")";
            return toString;
        }
        else
        {
            toString.setNuevoAlias(t.alias, t);
            toString.term="(\\lambda "+x.toStringLaTeX(s,"")+"."+toString.term+")";
            return toString;
        }
        
    }
    
    public ToString toStringAbrvV1(ToString toString)
    {
        if(t.alias == null)
        {
            t.toStringAbrvFinalV1(toString);
            toString.term= "(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        else
        {
            toString.setNuevoAliasV1(t.alias, t);
            toString.term="(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bracket other = (Bracket) obj;
        if (this.x != other.x && (this.x == null || !this.x.equals(other.x))) {
            return false;
        }
        if (this.t != other.t && (this.t == null || !this.t.equals(other.t))) {
            return false;
        }
        return true;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        Term term=new Bracket(x,(Term)t.clone());
        term.alias=this.alias;
        return term;
    }

    @Override
    public Term sustParall(List<Var> Vars, List<Term> varsTerm) {
        ArrayList<Var> aux = new ArrayList<Var>();
        int i = 0;
        for (Iterator<Var> it = Vars.iterator(); it.hasNext();) {
            Var var = it.next();
            if (!(var.occur(x))) {
                aux.add(x);     
                varsTerm.remove(i);
            } 
            i++;
        }
        if (aux.size() != 0) {
            
            return new Bracket((Var) x,t.sustParall(aux, varsTerm));
        }else{
            return this;
        }
        
            
        }

    @Override
    public Term checkApp() {
        
        return this;
    }
    
    @Override
	public String aliases(String position) {
		
		return "";
	}

    
    }

