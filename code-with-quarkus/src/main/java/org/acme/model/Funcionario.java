package org.acme.model;

public class Funcionario {
    private int id_funcionario;
    private String nome_funcionario;
    private String tipo_funcionario;
    private String email_funcionario;
    private String senha_funcionario;

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

    public String getTipo_funcionario() {
        return tipo_funcionario;
    }

    public void setTipo_funcionario(String tipo_funcionario) {
        this.tipo_funcionario = tipo_funcionario;
    }

    public String getEmail_funcionario() {
        return email_funcionario;
    }

    public void setEmail_funcionario(String email_funcionario) {
        this.email_funcionario = email_funcionario;
    }

    public String getSenha_funcionario() {
        return senha_funcionario;
    }

    public void setSenha_funcionario(String senha_funcionario) {
        this.senha_funcionario = senha_funcionario;
    }

    public void lerFuncionario(int id, String nm, String tf, String email, String senha ){
        System.out.println("ID do Funcionario: " + id);
        System.out.println("Nome do Funcionario: " + nm);
        System.out.println("Função: "+ tf);
        System.out.println("Email do funcionario: " +email);
        System.out.println("Senha: " + senha);
    }
}
