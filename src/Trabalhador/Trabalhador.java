package Trabalhador;

import Main.*;

/**
 *
 * @author radames
 */
public class Trabalhador {

    private String cpf;
    private String nome;
    private double salario;
    private boolean aposentado;
    private String departamento;

    public Trabalhador(String cpf, String nome, double salario, String departamento, boolean aposentado) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.aposentado = aposentado;
        this.departamento = departamento;
    }

    public Trabalhador() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAposentado() {
        return aposentado;
    }

    public void setAposentado(boolean aposentado) {
        this.aposentado = aposentado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return cpf + ";" + nome + ";" + salario + ";" + departamento + ";" + aposentado;
    }

}
