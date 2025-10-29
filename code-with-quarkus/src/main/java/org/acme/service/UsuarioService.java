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

    public void cadastraUsuario(UsuarioDTO usuario) throws SQLException{
        try {
            valicaoI(usuario);
            usuarioRepository.inserirPaciente(usuario);

        } catch (Exception e){
            throw  new RuntimeException(e);
        }

    }
    public void valicaoI(UsuarioDTO usuario){
        if (usuario.getNome_usuario()==null || usuario.getNome_usuario().isEmpty()){
            throw new IllegalArgumentException("Nome incorreto");
        }
        if (usuario.getCpf()== null || usuario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("Cpf incorreto");
        }
        if (usuario.getTelefone()==null || usuario.getTelefone().isEmpty()){
            throw new IllegalArgumentException("Telefone incorreto");
        }
        if (usuario.getEmail_usuario()==null || usuario.getEmail_usuario().isEmpty()){
            throw new IllegalArgumentException("Email incorreto");
        }
        if (usuario.getSenha_usuario()==null || usuario.getSenha_usuario().isEmpty()){
            throw new IllegalArgumentException("Senha incorreto");
        }
    }

    public List<Usuario> existeUsuario(int id_usuario, String email, String senha) throws SQLException{
        try {
            validaRelatorio(id_usuario,email,senha);
             return usuarioRepository.RelatorioPaciente(id_usuario, email, senha);

        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    public List<Usuario> validaRelatorio(int id_usuario, String email, String senha){
        if (email==null || email.isEmpty()){
            throw new IllegalArgumentException("E-mail incorreto");
        }
        if (senha==null || senha.isEmpty()){
            throw new IllegalArgumentException("senha incorreta.");
        }

        List<Usuario> temS_N= usuarioRepository.RelatorioPaciente(id_usuario, email, senha);
        if (temS_N.isEmpty()){
            System.out.println("Não existe paciente que possui o ID: "+id_usuario);
            return List.of();
        }else {
            System.out.println("Paciente encontrado com sucesso!");
        }
        return temS_N;
    }

    public void RemoverIdUsuario(int id, String email, String senha) throws  SQLException{
        try {
            validacaoR(id,email,senha);
            usuarioRepository.RemoverPaciente(id,email,senha);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    public void validacaoR(int id, String email, String senha){
        try {
            if(id<0){
                throw new IllegalAccessError("O id não pode ter valor menor do que 0");
            }
            if (email==null || email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha==null || senha.isEmpty()){
                throw new IllegalArgumentException("Senha incorreta.");
            }
            boolean existe=usuarioRepository.RemoverPaciente(id,email,senha);
            if (!existe){
                System.out.println("Não existe o id prsente");
            }
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public void atualizaInformacaoU(int id, String nome,String cpf, String telefone, String email, String senha) throws SQLException{
        try {
            valiacaoA_In_U(id,nome,cpf,telefone,email,senha);
            usuarioRepository.updanteUsuario(id,nome,cpf,telefone,email, senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void valiacaoA_In_U(int id, String nome,String cpf, String telefone, String email, String senha){
        try{
            if (id<0){
                throw new IllegalArgumentException("Id não pode ser menor doque zero.");
            }
            if (nome.isEmpty()){
                throw new IllegalArgumentException("Nome está incorreto");
            }
            if (cpf.isEmpty()){
                throw new IllegalArgumentException("cpf está incorreto");
            }
            if (telefone.isEmpty()){
                throw new IllegalArgumentException("Telefone está incorreto");
            }
            if (email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha.isEmpty()){
                throw new IllegalArgumentException("Senha está incorreta");
            }

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro apresetado: ",e);
        }
    }

}

