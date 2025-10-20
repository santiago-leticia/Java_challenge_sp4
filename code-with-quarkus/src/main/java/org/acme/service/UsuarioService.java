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

        if (usuario.getNome_usuario().isEmpty()){
            return false;
        } else if (usuario.getCpf().isEmpty()) {
            return false;
        } else if (usuario.getTelefone().isEmpty()) {
            return false;
        } else if (usuario.getEmail().isEmpty()) {
            return false;
        }
        usuarioRepository.inserirPaciente(usuario);
        return true;
    }
    public boolean existeUsuario(int id_usuario) throws SQLException{
        List<Usuario> temS_N= usuarioRepository.RelatorioPaciente(id_usuario);
        if (temS_N.isEmpty()){
            System.out.println("Não existe paciente que possui o ID: "+id_usuario);
            return false;
        }else {
            System.out.println("Paciente encontrado com sucesso!");
            usuarioRepository.RelatorioPaciente(id_usuario);
            return true;
        }
    }

    public boolean RemoverIdUsuario(int id) throws  SQLException{
        if(id<0){
            throw new IllegalAccessError("O id não pode ter valor menor do que 0");
        }else {
            boolean existe=usuarioRepository.RemoverPaciente(id);
            if (!existe){
                System.out.println("Não existe o id prsente");
            }
        }
        return usuarioRepository.RemoverPaciente(id);
    }

    public boolean atualizaInformacaoU(int id, String nome, String telefone, String email) throws SQLException{
        try {
            if (id<0){
                return false;
            }
            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()){
                return false;
            }
            return usuarioRepository.updanteUsuario(id,nome,telefone,email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
