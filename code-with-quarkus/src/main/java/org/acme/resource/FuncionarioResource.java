package org.acme.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.DTO.FuncionarioDTO;
import org.acme.model.Funcionario;
import org.acme.service.FuncionarioService;

import java.sql.SQLException;
import java.util.List;

@Path("/doctorAjuda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    FuncionarioService funcionarioService;

    @POST
    @Path("/cadastrar/funcionario")
    public Response cadastraFuncionario(FuncionarioDTO funcionario){
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

    @DELETE
    @Path("/deletar/funcionario")
    public Response RemoverFuncionario (Funcionario funcionario){
        try {
            funcionarioService.RemoverId(funcionario.getId_funcionario(), funcionario.getEmail_funcionario(),funcionario.getSenha_funcionario());
            return Response.status(Response.Status.OK).entity("Removido com sucesso").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no server.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(200).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/relatorio/funcionario")
    public Response Relatorio_funcionario(Funcionario f){
        try {
            List<Funcionario>l= funcionarioService.existeFuncionario(
                            f.getId_funcionario(),
                            f.getEmail_funcionario(),
                            f.getSenha_funcionario());

            return Response.status(Response.Status.OK)
                    .entity(l).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        } catch (IllegalArgumentException e){
            return Response.status(422).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/atualizar/funcionario")
    public Response atualizarFuncionario(Funcionario f){
        try {
            funcionarioService.atualizarInformacaoF(
                    f.getId_funcionario(),
                    f.getNome_funcionario(),
                    f.getTipo_funcionario(),
                    f.getEmail_funcionario(),
                    f.getSenha_funcionario());

            return Response.status(Response.Status.OK)
                    .entity("Dados atualizando")
                    .build();

        }catch (SQLException e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();        }

    }
}
