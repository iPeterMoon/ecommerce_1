/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import DAO.UsuarioDAO;
import DAO.interfaces.IUsuarioDAO;
import DTO.UsuarioDTO;
import DTO.login.LoginRequestDTO;
import DTO.login.LoginResponseDTO;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mappers.UsuarioMapper;
import util.JwtUtil;

/**
 * REST Web Service
 *
 * @author petermoon
 */
@Path("auth/login")
@RequestScoped
public class LoginResource {

    @Context
    private UriInfo context;

    private IUsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequest) {
        try {
            if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Email y password son requeridos\"}")
                        .build();
            }

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            UsuarioDTO usuario = UsuarioMapper.toDTO(usuarioDAO.buscarPorCredenciales(email, password));

            if (usuario == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"Credenciales inválidas\"}")
                        .build();
            }

            if (!usuario.isCuentaActiva()) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"La cuenta no está activa\"}")
                        .build();
            }

            String token = JwtUtil.generarToken(usuario);

            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(token);
            response.setUsuario(usuario);

            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al procesar la solicitud: " + e.getMessage() + "\"}")
                    .build();
        }

    }
}
