package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.repository.ConsultaRepository;


import java.sql.SQLException;
import java.util.Set;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;


    public boolean cadastraConsulta(ConsultaDTO consultaDTO) throws SQLException {
        try{
            if (consultaDTO.getNome_usuario().isEmpty()){
                return false;
            } else if (consultaDTO.getNome_funcionario().isEmpty()){
                return false;
            } else if (consultaDTO.getData_consulta().isEmpty()) {
                return false;
            }else if (consultaDTO.getHoras_consultas().isEmpty()){
                return false;
            } else if (consultaDTO.getInformacao_consulta().isEmpty()){
                return false;
            }
            consultaRepository.inserirConsulta(consultaDTO);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Set<Consulta> existeConsulta(int id, String email_u, String senha_u) throws SQLException{
        try {
            Set<Consulta> temS_N= consultaRepository.RelatorioConsulta(id,email_u,senha_u);
            if (temS_N.isEmpty()){
                System.out.println("Não existe uma consulta com esse id.");
                return Set.of();
            }else {
                System.out.println("consulta encontrado");
            }
            return temS_N;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean RemoverIdConsulta(int id, String email_consulta, String senha_consulta) throws SQLException{
        try {
            if (id<=0){
                throw new IllegalAccessError("O id invalido");
            }else {
                boolean existe=consultaRepository.RemoverConsulta(id,email_consulta,senha_consulta);
                if(!existe){
                    System.out.println("Não existe, esse id para excluir.");
                }
                return existe;
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean atualizarInformacaoC(int id_c, String n_u, String email, String senha, String n_f, String d_c, String h_c, String i_c){
        try {
            if (id_c<0){
                return false;
            }
            if (n_u.isEmpty() || email.isEmpty() || n_f.isEmpty() || d_c.isEmpty() || h_c.isEmpty() || i_c.isEmpty()){
                return false;
            }
            return consultaRepository.updanteConsulta(id_c,n_u, email,senha,n_f, d_c, h_c, i_c);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
