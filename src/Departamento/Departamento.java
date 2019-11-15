package Departamento;

import java.util.Date;



public class Departamento {


    private int IdDepartamendo;
    private String Nome;
    private String Sigla;


    public Departamento() {
    }

    public Departamento(int IdDepartamendo,String Nome,String Sigla){

        this.IdDepartamendo=IdDepartamendo;
        this.Nome=Nome;
        this.Sigla=Sigla;
    }
    public int getIdDepartamendo(){

        return IdDepartamendo;
    }
    public void setIdDepartamendo(int IdDepartamendo){

        this.IdDepartamendo=IdDepartamendo;
    }
    public String getNome(){

        return Nome;
    }
    public void setNome(String Nome){

        this.Nome=Nome;
    }
    public String getSigla(){

        return Sigla;
    }
    public void setSigla(String Sigla){

        this.Sigla=Sigla;
    }
    @Override

    public String toString(){

        return String.valueOf(IdDepartamendo)+";" +Nome+";" +Sigla;
    }
}
