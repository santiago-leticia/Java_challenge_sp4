package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.model.DTO.FuncionarioDTO;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Funcionario;
import org.acme.model.Usuario;
import org.acme.service.ConsultaService;
import org.acme.service.FuncionarioService;
import org.acme.service.UsuarioService;

import java.sql.SQLException;
import java.util.*;

@Path("/doctorAjuda")
public class GreetingResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    FuncionarioService funcionarioService;

    @Inject
    ConsultaService consultaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/paciente")
    public Response cadastraPaciente(UsuarioDTO usuario) throws SQLException{
        try{
            usuarioService.cadastraUsuario(usuario);
            return Response.status(Response.Status.CREATED).entity("Criando com sucesso").build();

        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar paciente" +e.getMessage())
                    .build();
        }catch (IllegalAccessError e){
            return Response.status(422).entity(e.getMessage()).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/funcionario")
    public Response cadastraFuncionario(FuncionarioDTO funcionario) throws SQLException{
        try{
            funcionarioService.cadastraFuncionario(funcionario);
            return Response.status(Response.Status.CREATED)
                    .entity("Funcionario criando").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar paciente" +e.getMessage())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/consulta")
    public Response cadastraConsulta(ConsultaDTO consulta){
        try{
           consultaService.cadastraConsulta(consulta);
           return Response.status(Response.Status.CREATED).entity("Consulta cadastrada.").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar Consulta" +e.getMessage())
                    .build();
        }
        catch (IllegalArgumentException e){
            return Response.status(422).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/relatorio/paciente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Relatorio_paciente(
            @PathParam("id") int id,
            @QueryParam("email_usuario") String email_usuario,
            @QueryParam("senha_usuario") String senha_usuario){
        try {

            List<Usuario>l=
                    usuarioService.existeUsuario(
                            id,
                            email_usuario,
                            senha_usuario
                    );
            if(l.isEmpty()){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Paciente nao encontrado.").build();
            }

            return Response.status(Response.Status.OK).entity(l).build();

        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        }
    }

    @GET
    @Path("/relatorio/funcionario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Relatorio_funcionario(
            @PathParam("id") int id_funcionario,
            @QueryParam("email_funcionario") String email_f,
            @QueryParam("senha_funcionario") String senha_f){
        try {
            List<Funcionario>l=
                    funcionarioService.existeFuncionario(
                            id_funcionario,
                            email_f,
                            senha_f);
            if (l.isEmpty()){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Funcionario não encontrado").build();
            }
            return Response.status(Response.Status.OK)
                    .entity(l).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        }
    }

    @GET
    @Path("/relatorio/consulta/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Relatorio_Consulta(
            @PathParam("id") int id_consulta,
            @QueryParam("email_usuario") String email_usuario,
            @QueryParam("senha_usuario") String senha_usuario){

        try{
            Set<Consulta>l= consultaService.existeConsulta(
                    id_consulta,
                    email_usuario,
                    senha_usuario);
            return Response.status(Response.Status.OK)
                    .entity(l).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no Server").build();
        }
    }

    @DELETE
    @Path("/deletar/paciente/{id}")
    public Response RemoverUsuario (@PathParam("id") int id, UsuarioDTO usuarioDTO){
        try {
            usuarioService.RemoverIdUsuario(id, usuarioDTO.getEmail_usuario(), usuarioDTO.getSenha_usuario());
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no server.").build();
        }catch (IllegalArgumentException e){
            return  Response.status(200).entity(e.getMessage()).build();
        }
    }


    @DELETE
    @Path("/deletar/funcionario/{id}")
    public Response RemoverFuncionario (@PathParam("id") int id, FuncionarioDTO funcionarioDTO){
        try {
            funcionarioService.RemoverId(id, funcionarioDTO.getEmail_funcionario(), funcionarioDTO.getSenha_funcionario());
            return Response.status(Response.Status.OK).entity("Removido com sucesso").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no server.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(200).entity(e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/deletar/consulta/{id}")
    public Response RemoverConsulta (@PathParam("id") int id, ConsultaDTO consultaDTO){
        try {
            consultaService.RemoverIdConsulta(id, consultaDTO.getEmail_usuario(), consultaDTO.getEmail_usuario());
            return Response.status(Response.Status.OK).entity("Removido com sucesso").build();
        }catch (SQLException e){
            Map<String, String> erro= new HashMap<>();
            return Response.status(Response.Status.UNAUTHORIZED).entity(erro).build();
        } catch (IllegalArgumentException e) {
            return Response.status(200).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/atualizar/paciente/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") int id , @QueryParam("nome") String nome,
                                     @QueryParam("cpf") String cpf,
                                     @QueryParam("telefone") String telefone,
                                     @QueryParam("email") String email,
                                     @QueryParam("senha_usuario") String senha_usuario){
        try{
            usuarioService.atualizaInformacaoU(id, nome,cpf,telefone,email, senha_usuario);
            return Response.status(Response.Status.OK).entity("Dados atualizando").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/atualizar/funcionario/{id_funcionario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarFuncionario(@PathParam("id_funcionario") int id_funcionario,
                                         @QueryParam("nome_funcionario") String nome_funcionario,
                                        @QueryParam("tipo_funcionario") String tipo_funcionario,
                                        @QueryParam("email") String email,
                                        @QueryParam("senha") String senha) {
        try {
            funcionarioService.atualizarInformacaoF(id_funcionario, nome_funcionario, tipo_funcionario, email, senha);
            return Response.status(Response.Status.OK).entity("Dados atualizando").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();        }

    }


    @PUT
    @Path("/atualizar/consulta/{id_consulta}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarConsulta(@PathParam("id_consulta") int id_consulta,
                                      @QueryParam("data_consulta") String d_c,
                                      @QueryParam("horas_consulta") String horas,
                                      @QueryParam("informacao_consulta") String in_consulta) throws SQLException {
        try {

            consultaService.atualizarInformacaoC(id_consulta, d_c, horas, in_consulta);
            return Response.status(Response.Status.OK).entity("Dados atualizando").build();


        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
