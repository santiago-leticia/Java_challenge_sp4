package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.model.Funcionario;
import org.acme.model.Usuario;
import org.acme.repository.ConsultaRepository;
import org.acme.repository.FuncionarioRepository;
import org.acme.repository.UsuarioRepository;


import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    FuncionarioRepository funcionarioRepository;

    public void cadastraConsulta(ConsultaDTO consulta) throws SQLException {
            valiacaoCadastra(consulta);
            consultaRepository.inserirConsulta(consulta);
    }
    public void valiacaoCadastra(ConsultaDTO consulta) throws SQLException {
        if (consulta.getId_paciente()<0){
            throw new IllegalArgumentException("Id do paciente incorreto");
        }
        if (consulta.getId_funcionario()<0){
            throw new IllegalArgumentException("Id funcionario incorreto");
        }
        if (funcionarioExiste(consulta.getId_funcionario())){
            if (!funcionarioExiste(consulta.getId_funcionario())){
                throw new IllegalArgumentException("Funcionario não existe");
            }
        }
        if (usuarioExiste(consulta.getId_paciente())){
            if (!usuarioExiste(consulta.getId_paciente())){
                throw new IllegalArgumentException("Usuario não existe");
            }
        }
        if (consulta.getData_consulta()==null || consulta.getData_consulta().isEmpty()){
            throw new IllegalArgumentException("Data da consulta incorreta");
        }
        if (consulta.getHoras_consultas()==null || consulta.getHoras_consultas().isEmpty()){
            throw new IllegalArgumentException("Hora incorreta");
        }
        if (consulta.getInformacao_consulta()==null || consulta.getInformacao_consulta().isEmpty()){
            throw new IllegalArgumentException("Informação sobre a consulta incorreta");
        }
    }
    public boolean funcionarioExiste(int id) throws SQLException{
        List<Funcionario> f= funcionarioRepository.lista();
        return f.contains(id);
    }
    public boolean usuarioExiste(int id) throws SQLException{
        List<Usuario> u= usuarioRepository.lista();
        return u.contains(id);
    }



    public List<Consulta> existeConsulta( String email_u, String senha_u) throws SQLException{
        try {
            valiacaoRelatorioConsulta( email_u, senha_u);
             return consultaRepository.RelatorioConsulta(email_u, senha_u);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void valiacaoRelatorioConsulta( String email_u, String senha_u){
        try {
            List<Consulta>c = consultaRepository.RelatorioConsulta(email_u, senha_u);
            if (c.isEmpty()){
                throw new IllegalArgumentException("Consulta não encontrada");
            }
            if (email_u==null || email_u.isEmpty()){
                throw new IllegalArgumentException("Email invalido");
            }
            if (senha_u==null || senha_u.isEmpty()){
                throw new IllegalArgumentException("Senha invalido");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void RemoverIdConsulta(int id, String email_consulta, String senha_consulta) throws SQLException{
        valiacaoRemover(id, email_consulta, senha_consulta);
        consultaRepository.RemoverConsulta(id, email_consulta, senha_consulta);
    }

    public void valiacaoRemover(int id, String email_consulta, String senha_consulta){
        try{

            if (id<0){
                throw  new IllegalAccessError("Id está incorreta");
            }
            if (email_consulta==null || email_consulta.isEmpty()){
                throw new IllegalArgumentException("Email do paciente incorreto");
            }
            if (senha_consulta==null || senha_consulta.isEmpty()){
                throw new IllegalArgumentException("Senha incorreta");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void atualizarInformacaoC(int id_c,int id_u, int id_f, String d_c, String h_c, String i_c) throws SQLException{
            valiacaoAtualizarC(id_c,id_u,id_f, d_c, h_c, i_c);
            consultaRepository.updanteConsulta(id_c, d_c, h_c, i_c);
    }
    public  void valiacaoAtualizarC(int id_c,int id_u, int id_f, String d_c, String h_c, String i_c){
        try{
            if (id_c<0){
                throw new IllegalArgumentException("Id não pode ser menor do que zero.");
            }
            if (id_u<0){
                throw new  IllegalArgumentException("Id não pode ser menor do que zero.");
            }
            if (id_f<0){
                throw new IllegalArgumentException("Id não pode ser menor do que zero.");
            }
            if (d_c.isEmpty()){
                throw new IllegalArgumentException("Data da consulta está incorreta");
            }
            if (h_c.isEmpty()){
                throw new IllegalArgumentException("horário da consulta está incorreta");
            }
            if (i_c.isEmpty()){
                throw new IllegalArgumentException("Informação da consulta está incorreto");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

    }
}
