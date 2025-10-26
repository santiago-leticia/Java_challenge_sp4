package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Funcionario;
import org.acme.model.Usuario;
import org.acme.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public boolean cadastraUsuario(UsuarioDTO usuario) throws SQLException{
        try {
            if (usuario.getNome_usuario().isEmpty()){
                return false;
            } else if (usuario.getCpf().isEmpty()) {
                return false;
            } else if (usuario.getTelefone().isEmpty()) {
                return false;
            } else if (usuario.getEmail_usuario().isEmpty()) {
                return false;
            }else  if (usuario.getSenha_usuario().isEmpty()){
                return false;
            }
            usuarioRepository.inserirPaciente(usuario);
            return true;

        } catch (Exception e){
            throw  new RuntimeException(e);
        }

    }
    public List<Usuario> existeUsuario(int id_usuario, String email, String senha) throws SQLException{
        try {
            List<Usuario> temS_N= usuarioRepository.RelatorioPaciente(id_usuario, email, senha);
            if (temS_N.isEmpty()){
                System.out.println("Não existe paciente que possui o ID: "+id_usuario);
                return List.of();
            }else {
                System.out.println("Paciente encontrado com sucesso!");
            }
            return temS_N;

        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    public boolean RemoverIdUsuario(int id, String email, String senha) throws  SQLException{
        try {
            if(id<0){
                throw new IllegalAccessError("O id não pode ter valor menor do que 0");
            }else {
                boolean existe=usuarioRepository.RemoverPaciente(id,email,senha);
                if (!existe){
                    System.out.println("Não existe o id prsente");
                }
                return existe;
            }

        } catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    public boolean atualizaInformacaoU(int id, String nome,String cpf, String telefone, String email, String senha) throws SQLException{
        try {
            if (id<0){
                return false;
            }
            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()){
                return false;
            }
            return usuarioRepository.updanteUsuario(id,nome,cpf,telefone,email, senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

