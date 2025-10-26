package org.acme.model;

public class Usuario {
    private int id_usuario;
    private String nome_usuario;
    private String cpf;
    private String telefone;
    private String email_usuario;
    private String senha_usuario;

    public Usuario() {
    }


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public void ler(int id, String nm_paciente, String cpf, String tl, String email, String senha){
        System.out.println("ID do paciente: " + id);
        System.out.println("Nome do paciente: " + nm_paciente);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + tl);
        System.out.println("Email: " + email);
        System.out.println("Senha: "+senha);
    }
}