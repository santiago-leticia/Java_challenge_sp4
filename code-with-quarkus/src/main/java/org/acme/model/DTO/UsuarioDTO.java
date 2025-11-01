package org.acme.model.DTO;

public class UsuarioDTO  {
    private String nome_usuario;
    private String cpf;
    private String telefone;
    private String email_usuario;
    private String senha_usuario;

    public UsuarioDTO() {}

    public UsuarioDTO(String nome_usuario, String cpf, String telefone, String email_usuario, String senha_usuario) {
        this.nome_usuario = nome_usuario;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
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
}
