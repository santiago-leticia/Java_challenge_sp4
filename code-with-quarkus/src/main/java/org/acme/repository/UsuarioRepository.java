package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Consulta;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioRepository {
    @ApplicationScoped

    /*create table T_RHSTU_PACIENTE(
    id_paciente NUMBER primary key, " +
    "nome_paciente VARCHAR(90), " +
    "cpf VARCHAR(180), " +
    "telefone VARCHAR(180),
     email VARCHAR(180)";
    */

    @Inject
    DataSource dataSource;

    public void inserirPaciente(UsuarioDTO usuario) throws SQLException {
        String sqlI = "insert into T_RHSTU_PACIENTE (" +
                    "nome_paciente, " +
                    "cpf, " +
                    "telefone," +
                    "email) " +
                    "values (?,?,?,?,?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sqlI);
            ) {
                ps.setString(2, usuario.getNome_usuario());
                ps.setString(3, usuario.getCpf());
                ps.setString(4, usuario.getTelefone());
                ps.setString(5, usuario.getEmail_usuario());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException();
            }
        }

        public List<Usuario> RelatorioPaciente(int id_paciente) {
            String sql = "select * from T_RHSTU_PACIENTE WHERE id_paciente=?";
            List<Usuario> l = new ArrayList<>();
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1,id_paciente);
                try( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt(1));
                        usuario.setNome_usuario(rs.getString(2));
                        usuario.setCpf(rs.getString(3));
                        usuario.setTelefone(rs.getString(4));
                        usuario.setEmail_usuario(rs.getString(5));
                        usuario.ler(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                        );
                        l.add(usuario);
                    }

                }

            } catch (SQLException e) {
                throw new RuntimeException();
            }
            return l;
        }

        public boolean RemoverPaciente(int id) {
            String sql = "DELETE FROM T_RHSTU_PACIENTE WHERE id_paciente=?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)
            ) {
                ps.setInt(1, id);
                int linhasAfetadas= ps.executeUpdate();
                return linhasAfetadas>0;
            }catch (SQLException e) {
                throw new RuntimeException("Erro de remover");
            }
        }

        public boolean updanteUsuario(int id, String nome, String telefone, String email){
            String sql="UPDATE T_RHSTU_PACIENTE SET" +
                    "nome_paciente=?, " +
                    "telefone=?, " +
                    "email=?" +
                    "WHERE id_paciente=?";
            try(Connection con= dataSource.getConnection();
                PreparedStatement ps= con.prepareStatement(sql))
            {
                ps.setString(1,nome);
                ps.setString(2, telefone);
                ps.setString(3, email);
                ps.setInt(4, id);
                int linhasAlteradas=ps.executeUpdate();
                return linhasAlteradas>0;
            }catch (SQLException e){
                throw new RuntimeException("Erro de executar updante.");
            }
        }
}
