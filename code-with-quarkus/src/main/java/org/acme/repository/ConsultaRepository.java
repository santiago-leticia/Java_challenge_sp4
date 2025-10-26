package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ConsultaRepository {

    /*create table T_RHSTU_CONSULTA("
    id_consulta NUMBER primary key, " +
    "nome_paciente VARCHAR(90) REFERENCES T_RHSTU_PACIENTE(nome_paciente),
    email_paciente VARCHAR(180) REFERENCES T_RHSTU_PACIENTE(email_paciente),
    senha_paciente VARCHAR(60) REFERENCES T_RHSTU_PACIENTE(senha_paciente)" +
    "nome_funcionario VARCHAR(180) REFERENCES T_RHSTU_FUNCIONARIO(nome_funcionario), " +
    "data_consulta DATE,
    horas_cosnsulta time,
    informacao_consulta VARCHAR(180)";
    */

    @Inject
    DataSource dataSource;


    public void inserirConsulta(ConsultaDTO consulta) throws SQLException {
        String sqlI = "insert into T_RHSTU_consulta (" +
                "nome_paciente, " +
                "nome_funcionario," +
                "email_paciente," +
                "senha_paciente, " +
                "data_consulta," +
                "horas_consulta, " +
                "informacao_consulta) " +
                "values (?,?,?,?, TO_DATE(?, 'DD-MM-YYYY'),?,?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlI);
        ) {
            ps.setString(1, consulta.getNome_usuario());
            ps.setString(2, consulta.getEmail_usuario());
            ps.setString(3, consulta.getNome_funcionario());
            ps.setString(4,consulta.getSenha_usuario());
            ps.setString(5, consulta.getData_consulta());
            ps.setString(6, consulta.getHoras_consultas());
            ps.setString(7, consulta.getInformacao_consulta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }


    public Set<Consulta> RelatorioConsulta(int id_c, String email, String senha) throws SQLException {
        String sql = "select * from T_RHSTU_consulta WHERE id_consulta= ? AND email_paciente=? AND senha_paciente=? ";
        Set<Consulta> l = new HashSet<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1,id_c);
            ps.setString(2, email);
            ps.setString(3, senha);

            try (ResultSet rs = ps.executeQuery()){

                while (rs.next()) {
                    Consulta consulta = new Consulta();
                    System.out.println();
                    consulta.setId_consulta(rs.getInt(1));
                    consulta.setNome_usuario(rs.getString(2));
                    consulta.setEmail_usuario(rs.getString(3));
                    consulta.setNome_funcionario(rs.getString(4));
                    consulta.setData_consulta(rs.getString(5));
                    consulta.setHoras_consulta(rs.getString(6));
                    consulta.setInformacao_consulta(rs.getString(7));

                    l.add(consulta);

                    consulta.lerConsulta(rs.getInt(1), rs.getString(2),
                            rs.getString(3),
                            rs.getString(4), rs.getString(5),
                            rs.getString(6),
                            rs.getString(7));
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return l;
    }

    public boolean RemoverConsulta(int id_consulta, String email_paciente, String s_p) throws SQLException {
        String sql = "DELETE FROM T_RHSTU_consulta WHERE id_consulta=? AND email_paciente=? AND senha_paciente=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, id_consulta);
            ps.setString(2,email_paciente);
            ps.setString(3,s_p);

            int linhasAfetadas= ps.executeUpdate();
            return linhasAfetadas>0;
        }catch (SQLException e) {
            throw new SQLException("Erro de remover");
        }
    }

    public boolean updanteConsulta(int id_c, String n_u, String email, String senha, String n_f, String d_c, String horas, String i_c) throws SQLException{
        String sql="UPDATE T_RHSTU_consulta SET" +
                "nome_paciente=?, " +
                "email_paciente=?," +
                "senha_paciente=? " +
                "nome_funcionario=?, " +
                "data_consulta= TO_DATE(?, 'YYYY-MM-DD)'," +
                "horas_consulta=? " +
                "informacao_consulta=?," +
                "WHERE id_consulta=?";
        try(Connection con= dataSource.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)) {
            ps.setString(1,n_u);
            ps.setString(2,email);
            ps.setString(3,senha);
            ps.setString(4, n_f);
            ps.setString(5, d_c);
            ps.setString(6,horas);
            ps.setString(7,i_c);
            ps.setInt(8, id_c);

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas>0;

        }catch (SQLException e){
            throw new SQLException("Erro de executar updante.");
        }
    }


}
