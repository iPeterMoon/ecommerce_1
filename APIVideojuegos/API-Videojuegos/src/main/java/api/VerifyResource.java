package api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import util.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * Recurso REST para operaciones de autenticación y validación de tokens.
 * Este endpoint será usado internamente por el servidor de vistas (Server-to-Server).
 */
@Path("auth/verify")
public class VerifyResource {

    /**
     * Endpoint para que otros servicios (como el de vistas) validen un token JWT.
     * Recibe el token en el cuerpo del JSON y devuelve el rol si es válido.
     * @param tokenData JSON con el token: {"token": "el_token_jwt"}
     * @return Response 200 OK con el rol si es válido, 401 Unauthorized si es inválido/expirado.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyToken(TokenRequest tokenData) {
        String token = tokenData.getToken();

        if (token == null || token.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\": \"Token is required\"}")
                           .build();
        }

        try {
            // El JwtUtil usa la SECRET_KEY que está solo en este servidor
            if (!JwtUtil.validarToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                               .entity("{\"error\": \"Token inválido o expirado\"}")
                               .build();
            }

            // Si es válido, extraemos el rol
            String role = JwtUtil.extraerRol(token);
            
            return Response.ok("{\"role\": \"" + role + "\"}")
                           .build();

        } catch (JWTVerificationException e) {
            // Error en la verificación (expiración, firma incorrecta, etc.)
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("{\"error\": \"Token inválido o expirado\"}")
                           .build();
        } catch (Exception e) {
            // Error interno
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\": \"Error interno de verificación: " + e.getMessage() + "\"}")
                           .build();
        }
    }
}

/**
 * Clase auxiliar para mapear el JSON de la petición {"token": "..."}
 */
class TokenRequest {
    private String token;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}