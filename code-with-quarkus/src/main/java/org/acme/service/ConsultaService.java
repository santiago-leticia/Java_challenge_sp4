package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.repository.ConsultaRepository;


import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;


    public Set<Consulta> existeConsulta(int id, String email_u, String senha_u) throws SQLException{
        try {
            valiacaoRelatorioConsulta(id, email_u, senha_u);
            consultaRepository.RelatorioConsulta(id, email_u, senha_u);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return Set.of();
    }

    public Set<Consulta> valiacaoRelatorioConsulta(int id, String email_u, String senha_u){
        try {
            Set<Consulta> existe= consultaRepository.RelatorioConsulta(id, email_u, senha_u);
            if (existe==null || existe.isEmpty()){
                throw new IllegalArgumentException("Não existe");
            }
            if (id<0){
                throw new IllegalArgumentException("ID invalido");
            }
            if (email_u==null || email_u.isEmpty()){
                throw new IllegalArgumentException("Email invalido");
            }
            if (senha_u==null || senha_u.isEmpty()){
                throw new IllegalArgumentException("Senha invalido");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Set.of();
    }

    public void cadastraConsulta(ConsultaDTO consulta) throws SQLException {
        try{
            valiacaoCadastra(consulta);
            consultaRepository.inserirConsulta(consulta);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void valiacaoCadastra(ConsultaDTO consulta){
        if (consulta==null || consulta.getId_paciente()<0){
            throw new IllegalArgumentException("Id do paciente incorreto");
        }
        if (consulta==null || consulta.getId_funcionario()<0){
            throw new IllegalArgumentException("Id funcionario incorreto");
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

    public void RemoverIdConsulta(int id, String email_consulta, String senha_consulta) throws SQLException{
        try {
            valiacaoRemover(id, email_consulta, senha_consulta);
            consultaRepository.RemoverConsulta(id, email_consulta, senha_consulta);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
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
            boolean e=consultaRepository.RemoverConsulta(id, email_consulta, senha_consulta);
            if (!e){
                throw new IllegalArgumentException("Não achando");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarInformacaoC(int id_c, String d_c, String h_c, String i_c){
        try {
            valiacaoAtualizarC(id_c, d_c, h_c, i_c);
            consultaRepository.updanteConsulta(id_c, d_c, h_c, i_c);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public  void valiacaoAtualizarC(int id_c, String d_c, String h_c, String i_c){
        try{
            if (id_c<0){
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
