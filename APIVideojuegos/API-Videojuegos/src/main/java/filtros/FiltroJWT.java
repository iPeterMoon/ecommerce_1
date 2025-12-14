package filtros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;
import util.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.io.IOException;

/**
 * Filtro JWT para proteger endpoints de la API REST
 * Solo aplica a rutas /api/*
 * 
 * @author peter
 */
@WebFilter("/api/*")
public class FiltroJWT implements Filter {

    // Endpoints públicos que no requieren autenticación
    private static final String[] PUBLIC_ENDPOINTS = {
        "/api/auth/login",
        "/api/auth/verify",
        "/api/productos"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Configurar CORS para permitir peticiones desde el frontend
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String path = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String relativePath = path.substring(contextPath.length());

        // Verificar si es un endpoint público
        if (isPublicEndpoint(relativePath)) {
            chain.doFilter(request, response);
            return;
        }

        // Obtener el token del header Authorization
        String authHeader = httpRequest.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendUnauthorizedResponse(httpResponse, "Token no proporcionado");
            return;
        }

        String token = authHeader.substring(7); // Remover "Bearer "

        try {
            // Validar el token
            if (!JwtUtil.validarToken(token)) {
                sendUnauthorizedResponse(httpResponse, "Token inválido");
                return;
            }

            // Extraer información del token y agregarla al request
            Long userId = JwtUtil.extraerUserId(token);
            String userEmail = JwtUtil.extraerCorreo(token);
            String userRole = JwtUtil.extraerRol(token);

            // Agregar atributos al request para que los endpoints los puedan usar
            httpRequest.setAttribute("userId", userId);
            httpRequest.setAttribute("userEmail", userEmail);
            httpRequest.setAttribute("userRole", userRole);

            // Token válido, continuar con la cadena de filtros
            chain.doFilter(request, response);

        } catch (JWTVerificationException e) {
            sendUnauthorizedResponse(httpResponse, "Token inválido o expirado");
        } catch (Exception e) {
            sendInternalErrorResponse(httpResponse, "Error al procesar el token");
        }
    }

    /**
     * Verifica si un endpoint es público (no requiere autenticación)
     */
    private boolean isPublicEndpoint(String path) {
        for (String publicPath : PUBLIC_ENDPOINTS) {
            if (path.equals(publicPath) || path.startsWith(publicPath + "/")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Envía una respuesta 401 Unauthorized
     */
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

    /**
     * Envía una respuesta 403 Forbidden
     */
    private void sendForbiddenResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

    /**
     * Envía una respuesta 500 Internal Server Error
     */
    private void sendInternalErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

    @Override
    public void destroy() {
        // Limpieza del filtro
    }
}