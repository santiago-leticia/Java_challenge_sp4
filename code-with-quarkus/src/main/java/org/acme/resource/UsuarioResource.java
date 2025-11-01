package org.acme.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.DTO.UsuarioDTO;
import org.acme.model.Usuario;
import org.acme.service.UsuarioService;

import java.sql.SQLException;
import java.util.List;

@Path("/doctorAjuda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @POST
    @Path("/cadastrar/paciente")
    public Response cadastraPaciente(UsuarioDTO usuario){
        try{
            usuarioService.cadastraUsuario(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity("Criando com sucesso").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar paciente: " +e.getMessage())
                    .build();
        }catch (IllegalArgumentException e){
            return  Response.status(422).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/relatorio/paciente")
    public Response Relatorio_paciente(Usuario usuario){
        try {

            List<Usuario> l= usuarioService.existeUsuario(
                            usuario.getId_usuario(),
                            usuario.getEmail_usuario(),
                            usuario.getSenha_usuario());
            return Response.status(Response.Status.OK).entity(l).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar").build();
        }
    }

    @DELETE
    @Path("/deletar/paciente")
    public Response RemoverUsuario (Usuario usuario){
        try {
            usuarioService.RemoverIdUsuario(usuario.getId_usuario(),
                    usuario.getEmail_usuario(),
                    usuario.getSenha_usuario());
            return  Response.status(Response.Status.OK)
                    .entity("Removido com sucesso").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no server.").build();
        }catch (IllegalArgumentException e){
            return  Response.status(200).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/atualizar/paciente")
    public Response atualizarUsuario(Usuario usuario){
        try{
            usuarioService.atualizaInformacaoU(
                    usuario.getId_usuario(),
                    usuario.getNome_usuario(),
                    usuario.getCpf(),
                    usuario.getTelefone(),
                    usuario.getEmail_usuario(),
                    usuario.getSenha_usuario());

            return Response.status(Response.Status.OK)
                    .entity("Dados atualizando")
                    .build();

        }catch (SQLException e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }catch (IllegalArgumentException e){
            return Response.
                    status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

}
