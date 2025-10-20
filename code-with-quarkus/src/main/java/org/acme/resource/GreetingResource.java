package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.model.DTO.FuncionarioDTO;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.service.ConsultaService;
import org.acme.service.FuncionarioService;
import org.acme.service.UsuarioService;

import java.sql.SQLException;
import java.util.Scanner;

@Path("/doctorAjuda")
public class GreetingResource {

    @Inject
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ConsultaService consultaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrarFuncionario")
    public Response cadastraPaciente(UsuarioDTO usuario) throws SQLException{
        try{boolean ver= usuarioService.cadastraUsuario(usuario);
            if (ver){
                return Response.status(Response.Status.OK).entity("ok").build();
            }
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Usuario vazio").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar paciente" +e.getMessage())
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrarFuncionario")
    public Response cadastraFuncionario(FuncionarioDTO funcionario) throws SQLException{
        try{boolean ver= funcionarioService.cadastraFuncionario(funcionario);
            if (ver){
                return Response.status(Response.Status.OK).entity("ok").build();
            }
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Funcionario vazio").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar paciente" +e.getMessage())
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrarConsulta")
    public Response cadastraConsulta(ConsultaDTO consulta) throws SQLException{
        try{boolean ver= consultaService.cadastraConsulta(consulta);
            if (ver){
                return Response.status(Response.Status.OK).entity("ok").build();
            }
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Consulta vazio").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar Consulta" +e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/deletar/paciente/{id}")
    public Response RemoverUsuario (@PathParam("id") int id){
        try {
            boolean deletado=usuarioService.RemoverIdUsuario(id);
            if (deletado){
                return Response.status(Response.Status.OK)
                        .entity("deletado com sucesso").build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (IllegalAccessError e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O id não pode ser menor do que zero.").build();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @DELETE
    @Path("/deletar/funcionario/{id}")
    public Response RemoverFuncionario (@PathParam("id") int id){
        try {
            boolean deletado=funcionarioService.RemoverId(id);
            if (deletado){
                return Response.status(Response.Status.OK)
                        .entity("deletado com sucesso").build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (IllegalAccessError e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O id não pode ser menor do que zero.").build();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @DELETE
    @Path("/deletar/consulta/{id}")
    public Response RemoverConsulta (@PathParam("id") int id){
        try {
            boolean deletado=consultaService.RemoverIdConsulta(id);
            if (deletado){
                return Response.status(Response.Status.OK)
                        .entity("deletado com sucesso").build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (IllegalAccessError e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O id não pode ser menor do que zero.").build();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @PUT
    @Path("/atualizar/paciente/{id}/{nome}/{telefone}/{email}")
    public Response atualizarUsuario(@PathParam("id") int id , @PathParam("nome") String nome,
                                  @PathParam("telefone") String telefone,
                                  @PathParam("email") String email){
        try{
            boolean verificar=usuarioService.atualizaInformacaoU(id, nome,telefone,email);

            if(verificar){
                return Response.status(Response.Status.OK).entity("Dados alterdos com sucesso").build();
            }else{
                return Response.status((Response.Status.NOT_FOUND)).entity("Deu ruim").build();
            }

        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); }
    }


    @PUT
    @Path("/atualizar/Funcionario/{id_funcionario}/{nome_funcionario}/{email}/{senha}")
    public Response atualizarFuncionario(@PathParam("id_funcionario") int id_funcionario,
                                         @PathParam("nome_funcionario") String nome_funcionario,
                                     @PathParam("email") String email,
                                     @PathParam("senha") String senha) throws SQLException {
        boolean verificar=funcionarioService.atualizarInformacaoF(id_funcionario, nome_funcionario,email,senha);

        if(verificar){
            return Response.status(Response.Status.OK).entity("Dados alterdos com sucesso").build();
        }else{
            return Response.status((Response.Status.NOT_FOUND)).entity("Deu ruim").build();
        }


    }

    @PUT
    @Path("/atualizar/Consulta/{id_consulta}/{nome_paciente}/{nome_funcionario}/{data_consulta}/{informacao_consulta}")
    public Response atualizarFuncionario(@PathParam("id_consulta") int id_consulta,
                                         @PathParam("nome_paciente") String nm_paciente,
                                         @PathParam("nome_funcionario") String nm_funcionario,
                                         @PathParam("data_consulta") String dt_consulta,
                                         @PathParam("informacao_consulta") String in_consulta) throws SQLException {
        boolean verificar=consultaService.atualizarInformacaoC(id_consulta, nm_paciente,nm_funcionario,dt_consulta,in_consulta);

        if(verificar){
            return Response.status(Response.Status.OK).entity("Dados alterdos com sucesso").build();
        }else{
            return Response.status((Response.Status.NOT_FOUND)).entity("Deu ruim").build();
        }

    }

}
