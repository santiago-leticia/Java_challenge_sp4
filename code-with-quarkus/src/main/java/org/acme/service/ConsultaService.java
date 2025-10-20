package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.model.DTO.FuncionarioDTO;
import org.acme.model.Funcionario;
import org.acme.repository.ConsultaRepository;
import org.acme.repository.FuncionarioRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ConsultaService {
    @Inject
    ConsultaRepository consultaRepository;

    public boolean cadastraConsulta(ConsultaDTO consultaDTO) throws SQLException {
        try{
            if (consultaDTO.getNome_paciente().isEmpty()){
                return false;
            } else if (consultaDTO.getNome_funcionario().isEmpty()){
                return false;
            } else if (consultaDTO.getData_consulta().isEmpty()) {
                return false;
            } else if (consultaDTO.getInformacao_consulta().isEmpty())
            consultaRepository.inserirConsulta(consultaDTO);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean existeConsulta(int id) throws SQLException{
        try {
            Set<Consulta> temS_N= consultaRepository.RelatorioConsulta(id);
            if (temS_N.isEmpty()){
                System.out.println("Não existe um funcionario com esse id.");
                return false;
            }else {
                System.out.println("Funcionario encontrado");
                consultaRepository.RelatorioConsulta(id);
                return true;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean RemoverIdConsulta(int id ) throws SQLException{
        try {
            if (id<0){
                throw new IllegalAccessError("O id invalido");
            }else {
                boolean existe=consultaRepository.RemoverConsulta(id);
                if(!existe){
                    System.out.println("Não existe, esse id para excluir.");
                }
                return consultaRepository.RemoverConsulta(id);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public boolean atualizarInformacaoC(int id_c, String n_u, String n_f, String d_c, String i_c){
        try {
            if (id_c<0){
                return false;
            }
            if (n_u.isEmpty() || n_f.isEmpty() || d_c.isEmpty() || i_c.isEmpty()){
                return false;
            }
            return consultaRepository.updanteConsulta(id_c,n_u,n_f,d_c,i_c);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
