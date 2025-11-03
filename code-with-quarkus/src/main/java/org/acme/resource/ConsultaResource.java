package org.acme.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Consulta;
import org.acme.model.DTO.ConsultaDTO;
import org.acme.service.ConsultaService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                    .entity(consulta).build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro no servidor");
            return Response.serverError().entity(erro).build();
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
                    .entity("Consulta enviada").build();
        }catch (SQLException e){
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro interno do servidor");
            return Response.serverError().entity(erro).build();
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
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Erro no servido");
            return Response.serverError().entity(erro).build();
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

            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Atualizando com sucesso.");
            return Response.ok(resposta).build();

        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}