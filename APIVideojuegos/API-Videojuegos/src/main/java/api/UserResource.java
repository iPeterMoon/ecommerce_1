package api;

import DAO.DireccionDAO;
import DAO.UsuarioDAO;
import DAO.interfaces.IUsuarioDAO;
import DTO.DireccionDTO;
import DTO.UsuarioDTO;
import DTO.profile.ProfileRequest;
import entidades.Direccion;
import entidades.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mappers.UsuarioMapper;
import util.JwtUtil;
import util.Security; // Asumiendo que existe util.Security para hashear

@Path("users")
@RequestScoped
public class UserResource {

    @Context
    private HttpServletRequest request;

    private IUsuarioDAO usuarioDAO = new UsuarioDAO();
    private DireccionDAO direccionDAO = new DireccionDAO();


    @GET
    @Path("profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPerfil() {
        try {
            Usuario usuario = obtenerUsuarioAutenticado();
            if (usuario == null) return Response.status(Response.Status.UNAUTHORIZED).build();

            UsuarioDTO usuarioDTO = UsuarioMapper.toDTO(usuario);
            return Response.ok(usuarioDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Path("profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPerfil(ProfileRequest datos) {
        try {
            Usuario usuario = obtenerUsuarioAutenticado();
            if (usuario == null) return Response.status(Response.Status.UNAUTHORIZED).build();

            if (datos.getNombre() == null || datos.getNombre().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"El nombre es requerido\"}").build();
            }

            usuario.setNombre(datos.getNombre());
            usuario.setTelefono(datos.getTelefono());

            if (datos.getNuevaPassword() != null && !datos.getNuevaPassword().isEmpty()) {
                if (datos.getPasswordActual() == null || datos.getPasswordActual().isEmpty()) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Debes ingresar tu contraseña actual para cambiarla\"}").build();
                }

                String hashActual = Security.hashear(datos.getPasswordActual());
                if (!hashActual.equals(usuario.getContrasena())) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"La contraseña actual es incorrecta\"}").build();
                }

                usuario.setContrasena(Security.hashear(datos.getNuevaPassword()));
            }

            usuarioDAO.actualizar(usuario);

            return Response.ok("{\"message\": \"Perfil actualizado correctamente\"}").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"Error al actualizar perfil\"}").build();
        }
    }

    // --- DIRECCIONES ---

    @POST
    @Path("addresses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarDireccion(DireccionDTO direccionDTO) {
        try {
            Usuario usuario = obtenerUsuarioAutenticado();
            if (usuario == null) return Response.status(Response.Status.UNAUTHORIZED).build();

            Direccion direccion = new Direccion();
            direccion.setNombre(direccionDTO.getNombre());
            direccion.setCalle(direccionDTO.getCalle());
            direccion.setNumeroExterior(direccionDTO.getNumeroExterior());
            direccion.setColonia(direccionDTO.getColonia());
            direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
            direccion.setCiudad(direccionDTO.getCiudad());
            direccion.setEstado(direccionDTO.getEstado());
            direccion.setUsuario(usuario);

            direccionDAO.crear(direccion);

            return Response.ok("{\"message\": \"Dirección agregada\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"Error al guardar dirección\"}").build();
        }
    }

    @PUT
    @Path("addresses/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarDireccion(@PathParam("id") Long idDireccion, DireccionDTO direccionDTO) {
        try {
            Usuario usuario = obtenerUsuarioAutenticado();
            if (usuario == null) return Response.status(Response.Status.UNAUTHORIZED).build();

            Direccion direccion = direccionDAO.buscarPorId(idDireccion);
            if (direccion == null) return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Dirección no encontrada\"}").build();

            if (!direccion.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                return Response.status(Response.Status.FORBIDDEN).entity("{\"error\": \"No tienes permiso\"}").build();
            }

            direccion.setNombre(direccionDTO.getNombre());
            direccion.setCalle(direccionDTO.getCalle());
            direccion.setNumeroExterior(direccionDTO.getNumeroExterior());
            direccion.setColonia(direccionDTO.getColonia());
            direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
            direccion.setCiudad(direccionDTO.getCiudad());
            direccion.setEstado(direccionDTO.getEstado());

            direccionDAO.actualizar(direccion);

            return Response.ok("{\"message\": \"Dirección actualizada\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"Error al actualizar dirección\"}").build();
        }
    }

    @DELETE
    @Path("addresses/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarDireccion(@PathParam("id") Long idDireccion) {
        try {
            Usuario usuario = obtenerUsuarioAutenticado();
            if (usuario == null) return Response.status(Response.Status.UNAUTHORIZED).build();

            Direccion direccion = direccionDAO.buscarPorId(idDireccion);
            if (direccion == null) return Response.status(Response.Status.NOT_FOUND).build();

            if (!direccion.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            direccionDAO.eliminar(direccion);

            return Response.ok("{\"message\": \"Dirección eliminada\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"Error al eliminar\"}").build();
        }
    }

    // Helper privado
    private Usuario obtenerUsuarioAutenticado() {
        String authHeader = request.getHeader("Authorization");
        String token = JwtUtil.extraerTokenDelHeader(authHeader);
        if (token == null || !JwtUtil.validarToken(token)) return null;
        Long userId = JwtUtil.extraerUserId(token);
        return usuarioDAO.buscarPorId(userId);
    }
}