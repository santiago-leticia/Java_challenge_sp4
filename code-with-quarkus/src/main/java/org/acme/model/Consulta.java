package org.acme.model;

public class Consulta extends Usuario {
    private int id_consulta;
    private int id_funcionario;
    private String nome_funcionario;
    private String data_consulta;
    private String horas_consulta;
    private String informacao_consulta;

    public Consulta() {
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getHoras_consulta() {
        return horas_consulta;
    }

    public void setHoras_consulta(String horas_consulta) {
        this.horas_consulta = horas_consulta;
    }

    public String getInformacao_consulta() {
        return informacao_consulta;
    }

    public void setInformacao_consulta(String informacao_consulta) {
        this.informacao_consulta = informacao_consulta;
    }

    public void lerConsulta(int id, String nm_paciente, String email, String nm_funcionario, String dt_consulta){
        System.out.println("ID do Consulta: " + id);
        System.out.println("Nome do Paciente: " + nm_paciente);
        System.out.println("Email do paciente: "+ email);
        System.out.println("Nome do Funcionario: "+nm_funcionario);
        System.out.println("Data da consulta: " +dt_consulta);;
    }
}