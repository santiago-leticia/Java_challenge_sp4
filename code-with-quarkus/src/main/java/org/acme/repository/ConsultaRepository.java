package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ConsultaRepository {

    /*create table T_RHSTU_CONSULTA("
    id_consulta NUMBER primary key, " +
    "nome_paciente VARCHAR(90) REFERENCES T_RHSTU_PACIENTE(nome_paciente),
    email_paciente VARCHAR(180) REFERENCES T_RHSTU_PACIENTE(email_paciente)" +
    "nome_funcionario VARCHAR(180) REFERENCES T_RHSTU_FUNCIONARIO(nome_funcionario), " +
    "data_consulta DATE
    informacao_consulta VARCHAR(180)";
    */

    @Inject
    DataSource dataSource;

    public void inserirConsulta(ConsultaDTO consulta) throws SQLException {
        String sqlI = "insert into T_RHSTU_consulta (" +
                "nome_paciente, " +
                "nome_funcionario," +
                "email_paciente, " +
                "data_consulta," +
                "informacao_consulta) " +
                "values (?,?,?,?, TO_DATE(?, 'DD-MM-YYYY'),?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlI);
        ) {
            ps.setString(1, consulta.getNome_usuario());
            ps.setString(2, consulta.getEmail_usuario());
            ps.setString(3, consulta.getNome_funcionario());
            ps.setString(4, consulta.getData_consulta());
            ps.setString(5, consulta.getInformacao_consulta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public Set<Consulta> RelatorioConsulta(int id, String nome_paciente) {
        String sql = "select * from T_RHSTU_consulta WHERE id_consulta= ? AND nome_paciente=? ";
        Set<Consulta> l = new HashSet<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1,id);
            ps.setString(2,nome_paciente);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Consulta consulta = new Consulta();
                    System.out.println();
                    consulta.setId_consulta(rs.getInt(1));
                    consulta.setNome_usuario(rs.getString(2));
                    consulta.setEmail_usuario(rs.getString(3));
                    consulta.setNome_funcionario(rs.getString(4));
                    consulta.setData_consulta(rs.getString(5));
                    consulta.setInformacao_consulta(rs.getString(6));
                    l.add(consulta);
                    consulta.lerConsulta(rs.getInt(1),
                            rs.getString(2),rs.getString(3),
                            rs.getString(4), rs.getString(5),
                            rs.getString(6));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return l;
    }

    public boolean RemoverConsulta(int id) {
        String sql = "DELETE FROM T_RHSTU_consulta WHERE id_consulta=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, id);
            int linhasAfetadas= ps.executeUpdate();
            return linhasAfetadas>0;
        }catch (SQLException e) {
            throw new RuntimeException("Erro de remover");
        }
    }

    public boolean updanteConsulta(int id_c, String n_u, String email, String n_f, String d_c, String i_c) throws SQLException{
        String sql="UPDATE T_RHSTU_consulta SET" +
                "nome_paciente=?, " +
                "nome_funcionario=?, " +
                "data_consulta=?, " +
                "informacao_consulta=?" +
                "WHERE id_consulta=?";
        try(Connection con= dataSource.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)) {
            ps.setString(1,n_u);
            ps.setString(2,email);
            ps.setString(3, n_f);
            ps.setString(4, d_c);
            ps.setString(5, i_c);
            ps.setInt(6, id_c);
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas>0;
        }catch (SQLException e){
            throw new RuntimeException("Erro de executar updante.");
        }
    }


}
