package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;
import org.acme.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    public void cadastraUsuario(UsuarioDTO usuario) throws SQLException{
            valicaoI(usuario);
            usuarioRepository.inserirPaciente(usuario);
    }
    public void valicaoI(UsuarioDTO usuario){
        if (usuarioRepository.Existe(usuario.getEmail_usuario())){
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        if (usuarioRepository.existeCpf(usuario.getCpf())){
            throw new IllegalArgumentException("CPF ja cadastrado.");
        }
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

    public List<Usuario> existeUsuario( String email, String senha) throws SQLException{
        try {
            valiazaoRelatorio(email,senha);
            return usuarioRepository.RelatorioPaciente(email,senha);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    public void valiazaoRelatorio(String email, String senha){
        List<Usuario>l=usuarioRepository.RelatorioPaciente( email, senha);
        if (l.isEmpty()){
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        if (email.isEmpty()){
            throw new IllegalArgumentException("Email invalido");
        }
        if (senha.isEmpty()){
            throw new IllegalArgumentException("Senha invalida");
        }
    }

    public void RemoverIdUsuario(int id, String email, String senha) throws  SQLException{
        validacaoR(id,email,senha);
        usuarioRepository.RemoverPaciente(id,email,senha);
    }
    public void validacaoR(int id, String email, String senha){
        try {
            if (!usuarioExiste(email, senha)){
                throw new IllegalArgumentException("Existe");}
            if(id<0){
                throw new IllegalAccessError("O id não pode ter valor menor do que 0");
            }
            if (email==null || email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha==null || senha.isEmpty()){
                throw new IllegalArgumentException("Senha incorreta.");
            }
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    public boolean usuarioExiste(String email, String senha){
        List<Usuario> c= usuarioRepository.RelatorioPaciente(email,senha);
        return  !c.isEmpty();
    }

    public void atualizaInformacaoU(int id, String nome,String cpf, String telefone, String email, String senha) throws SQLException{
        valiacaoA_In_U(id,nome,cpf,telefone,email,senha);
        usuarioRepository.updanteUsuario(
                    id,
                    nome,
                    cpf,
                    telefone,
                    email,
                    senha
            );
    }
    public void valiacaoA_In_U(int id, String nome,String cpf, String telefone, String email, String senha){
        try{

            if (id<0){
                throw new IllegalArgumentException("Id não pode ser menor doque zero.");
            }
            if (nome==null || nome.isEmpty()){
                throw new IllegalArgumentException("Nome está incorreto");
            }
            if (cpf==null || cpf.isEmpty()){
                throw new IllegalArgumentException("cpf está incorreto");
            }
            if (telefone==null || telefone.isEmpty()){
                throw new IllegalArgumentException("Telefone está incorreto");
            }
            if (email==null || email.isEmpty()){
                throw new IllegalArgumentException("Email incorreto");
            }
            if (senha==null || senha.isEmpty()){
                throw new IllegalArgumentException("Senha está incorreta");
            }

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro apresetado: ",e);
        }
    }


}

