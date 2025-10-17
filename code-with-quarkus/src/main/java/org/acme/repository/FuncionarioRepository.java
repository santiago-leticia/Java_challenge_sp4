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
    "nome_funcionario VARCHAR(90), " +
    "email VARCHAR(180), "+
    "senha VARCHAR(180)";
    */
    @Inject
    DataSource dataSource;

    public void inserirFuncionario(FuncionarioDTO funcionario) throws SQLException {
        String sqlI = "insert into T_RHSTU_FUNCIONARIO (" +
                "id_funcionario, " +
                "nome_funcionario, " +
                "email, " +
                "senha " +
                "values (?,?,?,?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlI))
        {
            ps.setString(1, funcionario.getNome_funcionario());
            ps.setString(2, funcionario.getEmail());
            ps.setString(3, funcionario.getSenha());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
    }

    public List<Funcionario> RelatorioFuncionario() {
        String sql = "select * from T_RHSTU_FUNCIONARIO WHERE+?";
        List<Funcionario> l = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            try(ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId_funcionario(rs.getInt(1));
                    funcionario.setNome_funcionario(rs.getString(2));
                    funcionario.setEmail(rs.getString(3));
                    funcionario.setSenha(rs.getString(4));
                    l.add(funcionario);
                }
                System.out.println("ID da Funcionario: " + rs.getInt(1));
                System.out.println("Nome do funcionario: " + rs.getString(2));
                System.out.println("Email: " + rs.getString(3));
                System.out.println("senha: " + rs.getString(4));
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

    public boolean updanteFuncionario(Funcionario funcionario){
        String sql="UPDATE T_RHSTU_FUNCIONARIO SET" +
                "nome_funcionario=?, " +
                "email=?, " +
                "senha=?" +
                "WHERE id_funcionario=?";
        try(Connection con= dataSource.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)) {
            ps.setString(1,funcionario.getNome_funcionario());
            ps.setString(2,funcionario.getEmail());
            ps.setString(3,funcionario.getEmail());
            ps.setInt(5,funcionario.getId_funcionario());
            int linhasAlteradas=ps.executeUpdate();
            return linhasAlteradas>0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

