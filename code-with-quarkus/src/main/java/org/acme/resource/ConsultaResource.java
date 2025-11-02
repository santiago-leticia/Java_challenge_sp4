package org.acme.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.service.ConsultaService;

import java.sql.SQLException;
import java.util.List;


@Path("/doctorAjuda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaService consultaService;

    @POST
    @Path("/cadastrar/consulta")
    public Response cadastraConsulta(ConsultaDTO consulta){
        try{
            consultaService.cadastraConsulta(consulta);
            return Response.status(Response.Status.CREATED)
                    .entity("Consulta cadastrada.").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar Consulta: " +e.getMessage())
                    .build();
        } catch (IllegalArgumentException e){
            return Response.status(422).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/relatorio/consulta")
    public Response Relatorio_Consulta(Consulta c
    ){

        try{
            List<Consulta> l= consultaService.existeConsulta(
                    c.getEmail_usuario(),
                    c.getSenha_usuario()
            );
            return Response.status(Response.Status.OK)
                    .entity(l).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no Server").build();
        }
    }

    @DELETE
    @Path("/deletar/consulta")
    public Response RemoverConsulta (Consulta c){
        try {
            consultaService.RemoverIdConsulta(
                    c.getId_consulta(), c.getEmail_usuario(), c.getSenha_usuario());
            return Response
                    .status(Response.Status.OK)
                    .entity("Removido com sucesso")
                    .build();
        }catch (SQLException e){
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Erro no server.")
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(200)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/consulta")
    public Response atualizarConsulta(Consulta c){
        try {
            consultaService.atualizarInformacaoC(
                    c.getId_consulta(),
                    c.getId_usuario(),
                    c.getId_funcionario(),
                    c.getData_consulta(),
                    c.getHoras_consulta(),
                    c.getInformacao_consulta());

            return Response
                    .status(Response.Status.OK)
                    .entity("Dados atualizando")
                    .build();


        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}