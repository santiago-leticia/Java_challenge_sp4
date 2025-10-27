package org.acme.repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@ApplicationScoped
public class UsuarioRepository {


    /*CREATE TABLE T_RHSTU_PACIENTE (
    id_paciente NUMBER(9)PRIMARY KEY,
    nm_paciente VARCHAR2(90),
    nr_cpf VARCHAR2(14),
    nr_telefone_paciente VARCHAR2(20),
    ds_email_paciente VARCHAR2(100),
    ds_senha_paciente VARCHAR2(60)
);
    */

    @Inject
    DataSource dataSource;

    public void inserirPaciente(UsuarioDTO usuario) throws SQLException {
        String sqlI = "INSERT INTO T_RHSTU_PACIENTE (" +
                    "nm_paciente, " +
                    "nr_cpf, " +
                    "nr_telefone_paciente," +
                    "ds_email_paciente, " +
                    "ds_senha_paciente) " +
                    "VALUES (?,?,?,?,?,?)";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sqlI);
            ) {
                ps.setString(1, usuario.getNome_usuario());
                ps.setString(2, usuario.getCpf());
                ps.setString(3, usuario.getTelefone());
                ps.setString(4, usuario.getEmail_usuario());
                ps.setString(5, usuario.getSenha_usuario());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }

        public List<Usuario> RelatorioPaciente(int id_paciente, String email_u, String senha_u) {
            String sql = "select * from T_RHSTU_PACIENTE WHERE id_paciente=? AND ds_email_paciente=? AND ds_senha_paciente=?";
            List<Usuario> l = new ArrayList<>();
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1,id_paciente);
                ps.setString(2,email_u);
                ps.setString(3,senha_u);

                try( ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt(1));
                        usuario.setNome_usuario(rs.getString(2));
                        usuario.setCpf(rs.getString(3));
                        usuario.setTelefone(rs.getString(4));
                        usuario.setEmail_usuario(rs.getString(5));
                        usuario.setSenha_usuario(rs.getString(6));

                        usuario.ler(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6)
                        );
                        l.add(usuario);
                    }

                }

            } catch (SQLException e) {
                throw new RuntimeException();
            }
            return l;
        }

        public boolean RemoverPaciente(int id,String email_u, String senha_u) {
            String sql = "DELETE FROM T_RHSTU_PACIENTE WHERE id_paciente=? AND ds_email_paciente=? AND ds_senha_paciente=?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)
            ) {
                ps.setInt(1, id);
                ps.setString(2,email_u);
                ps.setString(3,senha_u);

                int linhasAfetadas= ps.executeUpdate();
                return linhasAfetadas>0;
            }catch (SQLException e) {
                throw new RuntimeException("Erro de remover");
            }
        }

        public boolean updanteUsuario(int id, String nome,String cpf, String telefone, String email, String senha){
            String sql="UPDATE T_RHSTU_PACIENTE SET" +
                    "nm_paciente=?, " +
                    "nr_cpf=?, " +
                    "nr_telefone_paciente=?," +
                    "ds_email_paciente=?, " +
                    "ds_senha_paciente=?" +
                    "WHERE id_paciente=?";
            try(Connection con= dataSource.getConnection();
                PreparedStatement ps= con.prepareStatement(sql))
            {
                ps.setString(1,nome);
                ps.setString(3,cpf);
                ps.setString(2, telefone);
                ps.setString(3, email);
                ps.setString(4,senha);
                ps.setInt(5, id);
                int linhasAlteradas=ps.executeUpdate();
                return linhasAlteradas>0;
            }catch (SQLException e){
                throw new RuntimeException("Erro de executar updante.");
            }
        }
}
