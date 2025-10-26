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

    public boolean cadastraFuncionario(FuncionarioDTO funcionario) throws SQLException{
        try{
            if (funcionario.getNome_funcionario().isEmpty()){
                return false;
            } else if (funcionario.getEmail_funcionario().isEmpty()){
                return false;
            } else if (funcionario.getSenha_usuario().isEmpty()) {
                return false;
            }
            funcionarioRepository.inserirFuncionario(funcionario);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Funcionario> existeFuncionario(int id, String email_f, String s_f) throws SQLException{
        try {
            List<Funcionario> temS_N= funcionarioRepository.RelatorioFuncionario(id, email_f, s_f);
            if (temS_N.isEmpty()){
                System.out.println("Não existe um funcionario com esse id.");
                return List.of();
            }else {
                System.out.println("Funcionario encontrado");
                return temS_N;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean RemoverId(int id, String email, String senha) throws SQLException{
        try {
            if (id<0){
                throw new IllegalAccessError("O id invalido");
            }else {
                boolean existe=funcionarioRepository.RemoverFuncionario(id, email, senha);
                if(!existe){
                    System.out.println("Não existe, esse id para excluir.");
                }
                return existe;
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean atualizarInformacaoF(int id, String nome_funcionario,String tipo_funcionario,String email, String senha){
        try {
            if (id<0){
                return false;
            }
            if (nome_funcionario.isEmpty() || tipo_funcionario.isEmpty() || email.isEmpty() || senha.isEmpty()){
                return false;
            }
            return funcionarioRepository.updanteFuncionario(id,nome_funcionario,tipo_funcionario,email,senha);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
