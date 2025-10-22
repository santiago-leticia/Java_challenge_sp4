package org.acme.model;

public class Consulta extends Funcionario {
    private int id_consulta;
    private String data_consulta;
    private String informacao_consulta;

    public Consulta() {
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getInformacao_consulta() {
        return informacao_consulta;
    }

    public void setInformacao_consulta(String informacao_consulta) {
        this.informacao_consulta = informacao_consulta;
    }
    public void lerConsulta(int id, String nm_paciente,String email, String nm_funcionario, String dt_consulta, String in_c){
        System.out.println("ID do Consulta: " + id);
        System.out.println("Nome do Paciente: " + nm_paciente);
        System.out.println("Email do paciente: "+ email);
        System.out.println("Nome do Funcionario: "+nm_funcionario);
        System.out.println("Data da consulta: " +dt_consulta);
        System.out.println("Informacao da consulta: " +in_c);
    }
}