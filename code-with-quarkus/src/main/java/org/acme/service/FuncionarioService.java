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
            } else if (funcionario.getEmail().isEmpty()){
                return false;
            } else if (funcionario.getSenha().isEmpty()) {
                return false;
            }
            funcionarioRepository.inserirFuncionario(funcionario);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean existeFuncionario(int id) throws SQLException{
        try {
            List<Funcionario> temS_N= funcionarioRepository.RelatorioFuncionario(id);
            if (temS_N.isEmpty()){
                System.out.println("Não existe um funcionario com esse id.");
                return false;
            }else {
                System.out.println("Funcionario encontrado");
                funcionarioRepository.RelatorioFuncionario(id);
                return true;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean RemoverId(int id ) throws SQLException{
        try {
            if (id<0){
                throw new IllegalAccessError("O id invalido");
            }else {
                boolean existe=funcionarioRepository.RemoverFuncionario(id);
                if(!existe){
                    System.out.println("Não existe, esse id para excluir.");
                }
                return funcionarioRepository.RemoverFuncionario(id);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean atualizarInformacaoF(int id, String nome,String email, String senha){
        try {
            if (id<0){
                return false;
            }
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                return false;
            }
            return funcionarioRepository.updanteFuncionario(id,nome,email,senha);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
