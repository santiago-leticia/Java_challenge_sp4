package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.FuncionarioDTO;
import org.acme.model.Funcionario;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@ApplicationScoped
public class FuncionarioRepository {

    /*create table T_RHSTU_FUNCIONARIO("
    id_funcionario NUMBER primary key, " +
    "nome_funcionario VARCHAR(90),
     tipo_funcionario" +
    "email_paciente VARCHAR(180), "+
    "senha VARCHAR(180)";
    */
    @Inject
    DataSource dataSource;

    public void inserirFuncionario(FuncionarioDTO funcionario) throws SQLException {
        String sqlI = "insert into T_RHSTU_FUNCIONARIO (" +
                "id_funcionario, " +
                "nome_funcionario," +
                "tipo_funcionario " +
                "email, " +
                "senha " +
                "values (?,?,?, ?,?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlI))
        {
            ps.setString(1, funcionario.getNome_funcionario());
            ps.setString(2,funcionario.getTipo_funcionario());
            ps.setString(3, funcionario.getEmail());
            ps.setString(4, funcionario.getSenha());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
    }

    public List<Funcionario> RelatorioFuncionario(int id) {
        String sql = "select * from T_RHSTU_FUNCIONARIO WHERE id_funcionario=?";
        List<Funcionario> l = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId_funcionario(rs.getInt(1));
                    funcionario.setNome_funcionario(rs.getString(2));
                    funcionario.setTipo_funcionario(rs.getString(3));
                    funcionario.setEmail_funcionario(rs.getString(4));
                    funcionario.setSenha(rs.getString(5));
                    l.add(funcionario);
                    funcionario.lerFuncionario(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                            );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return l;
    }

    public boolean RemoverFuncionario(int id) {
        String sql = "DELETE FROM T_RHSTU_FUNCIONARIO WHERE id_funcionario=?";
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

    public boolean updanteFuncionario(int id_funcionario, String nome_funcionario,String tipo_funcionario, String email_funcionario, String senha_funcionario){
        String sql="UPDATE T_RHSTU_FUNCIONARIO SET" +
                "nome_funcionario=?," +
                "tipo_funcionario=? " +
                "email=?, " +
                "senha=?" +
                "WHERE id_funcionario=?";
        try(Connection con= dataSource.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)) {
            ps.setString(1,nome_funcionario);
            ps.setString(2,tipo_funcionario);
            ps.setString(2,email_funcionario);
            ps.setString(3,senha_funcionario);
            ps.setInt(5,id_funcionario);
            int linhasAlteradas=ps.executeUpdate();
            return linhasAlteradas>0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

