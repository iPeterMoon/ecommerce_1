package util;

import DTO.UsuarioDTO;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

/**
 *
 * @author petermoon
 */
public class JwtUtil {
    
    private static final String SECRET_KEY = "4F8AB85AB389F4AFA723981F89A89";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    
    private static final String ISSUER = "ecommerce-api";
    
    private static final long EXPIRATION_TIME = 10800000L;
   
    /**
     * Genera un token JWT para el usuario
     * @param usuario DTO del usuario autenticado
     * @return token JWT como String
     */
    public static String generarToken(UsuarioDTO usuario) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getCorreo())
                .withClaim("userId", usuario.getIdUsuario())
                .withClaim("correo", usuario.getCorreo())
                .withClaim("nombre", usuario.getNombre())
                .withClaim("rol", usuario.getRol())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(ALGORITHM);
    }
    
    /**
     * Valida el token JWT
     * @param token token a validar
     * @return true si el token es válido, false en caso contrario
     */
    public static boolean validarToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    
    /**
     * Decodifica el token sin validarlo (útil para inspección)
     * @param token token a decodificar
     * @return token decodificado
     */
    public static DecodedJWT decodificarToken(String token) {
        return JWT.decode(token);
    }
    
    /**
     * Verifica y decodifica el token
     * @param token token a verificar
     * @return token decodificado y verificado
     * @throws JWTVerificationException si el token no es válido
     */
    public static DecodedJWT verificarYDecodificar(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }
    
    /**
     * Extrae el correo del token
     * @param token token JWT
     * @return correo del usuario
     */
    public static String extraerCorreo(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }
    
    /**
     * Extrae el ID del usuario del token
     * @param token token JWT
     * @return ID del usuario
     */
    public static Long extraerUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId").asLong();
    }
    
    /**
     * Extrae el rol del usuario del token
     * @param token token JWT
     * @return rol del usuario
     */
    public static String extraerRol(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("rol").asString();
    }
    
    /**
     * Extrae el nombre del usuario del token
     * @param token token JWT
     * @return nombre del usuario
     */
    public static String extraerNombre(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("nombre").asString();
    }
    
    /**
     * Verifica si el token ha expirado
     * @param token token JWT
     * @return true si ha expirado, false en caso contrario
     */
    public static boolean esTokenExpirado(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().before(new Date());
        } catch (JWTDecodeException e) {
            return true;
        }
    }
    
    /**
     * Extrae el token del header Authorization
     * @param authorizationHeader header de autorización
     * @return token sin el prefijo "Bearer "
     */
    public static String extraerTokenDelHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
