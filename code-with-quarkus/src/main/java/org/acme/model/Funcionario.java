package org.acme.model;

public class Funcionario extends Usuario {
    private int id_funcionario;
    private String nome_funcionario;
    private String email_funcionario;
    private String senha;

    public Funcionario() {}


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

    public String getEmail_funcionario() {
        return email_funcionario;
    }

    public void setEmail_funcionario(String email_funcionario) {
        this.email_funcionario = email_funcionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void lerFuncionario(int id, String nm_funcionario, String email, String senha){
        System.out.println("ID do Funcionario: " + id);
        System.out.println("Nome do Funcionario: " + nm_funcionario);
        System.out.println("Email do funcionario: " + email);
        System.out.println("Senha: " + senha);
    }
}
