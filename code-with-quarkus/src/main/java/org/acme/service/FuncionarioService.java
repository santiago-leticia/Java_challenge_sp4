package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.FuncionarioDTO;
import org.acme.model.Funcionario;
import org.acme.repository.FuncionarioRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class FuncionarioService{

    @Inject
    FuncionarioRepository funcionarioRepository;

    public void cadastraFuncionario(FuncionarioDTO funcionario) throws SQLException{
        valiacaoC_F(funcionario);
        funcionarioRepository.inserirFuncionario(funcionario);
    }
    public void valiacaoC_F(FuncionarioDTO f){
        if (f.getNome_funcionario()==null || f.getNome_funcionario().isEmpty()){
            throw new IllegalArgumentException("Nome incorreto");
        }
        if (f.getTipo_funcionario()==null || f.getTipo_funcionario().isEmpty()){
            throw new IllegalArgumentException("Função do funcionario incorreto");
        }
        if (f.getEmail_funcionario()==null || f.getEmail_funcionario().isEmpty()){
            throw new IllegalArgumentException("Email do funcionario incorreta");
        }
        if (f.getSenha_funcionario()==null || f.getSenha_funcionario().isEmpty()){
            throw new IllegalArgumentException("senha incorreta");
        }
    }

    public List<Funcionario> existeFuncionario(int id, String email_f, String s_f) throws SQLException{
        try {
            valiacaoR_F(id,email_f,s_f);
            return funcionarioRepository.RelatorioFuncionario(id,email_f,s_f);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void RemoverId(int id, String email, String senha) throws SQLException{
        try {
            valiacaoRemova(id,email,senha);
            funcionarioRepository.RemoverFuncionario(id,email,senha);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void atualizarInformacaoF(int id, String nome_funcionario,String tipo_funcionario,String email, String senha) throws SQLException{
        try {
            valiacaoAutualiza(id, nome_funcionario, tipo_funcionario, email, senha);
            funcionarioRepository.updanteFuncionario(id, nome_funcionario, tipo_funcionario, email, senha);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void valiacaoRemova(int id, String email, String senha){
        try{
            if (id<0){
                throw new IllegalArgumentException("ID invalido");
            }
            if (email==null || email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha==null || senha.isEmpty()){
                throw new IllegalArgumentException("senha incorreta");
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
    }



    public void valiacaoR_F(int id, String email_f, String s_f ){
        List<Funcionario>l= funcionarioRepository.RelatorioFuncionario(id, email_f, s_f);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Funcionando não encontrado");
        }
        if (id<0){
            throw  new IllegalAccessError("Id invalido");
        }
        if (email_f.isEmpty()){
            throw  new IllegalArgumentException("Email invalido");
        }
        if (s_f.isEmpty()){
            throw new IllegalArgumentException("Senha invalida");
        }
    }
    public void valiacaoAutualiza(int id, String nome_funcionario,String tipo_funcionario,String email, String senha){
        try {
            if (id<0){
                throw new IllegalArgumentException("O id está incorreto");
            }
            if (nome_funcionario==null || nome_funcionario.isEmpty()){
                throw new IllegalArgumentException("O nome do funcionario incorreto.");
            }
            if (tipo_funcionario==null || tipo_funcionario.isEmpty()){
                throw new IllegalArgumentException("Tipo de funcionario incorreto");
            }
            if (email==null || email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha==null || senha.isEmpty()){
                throw new IllegalArgumentException("Senha incorreta");
            }
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Erro apresentado: ", e);
        }
    }

}
