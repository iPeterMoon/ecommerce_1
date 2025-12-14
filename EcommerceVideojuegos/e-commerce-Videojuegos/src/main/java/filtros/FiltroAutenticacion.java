package filtros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.UsuarioDTO;
import enums.RolUsuario;
import jakarta.servlet.annotation.WebFilter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * FiltroAutenticacion.java
 *
 * Filtro corregido para priorizar la sesión HTTP (Cookies) y usar JWT como respaldo.
 *
 * @author Pedro Luna
 */
@WebFilter("/*")
public class FiltroAutenticacion implements Filter {

    private static final String API_BASE_URL = "http://localhost:8080/API-Videojuegos/api";
    private static final String VERIFY_URL = API_BASE_URL + "/auth/verify";
    
    private HttpClient httpClient; 
    
    String[] paginasUsuario = {"account.jsp", "agregar-direccion.jsp", "detalles-pedido-contraentrega.jsp", "detalles-pedido-tarjeta-pagado.jsp", "detalles-pedido-transfe.jsp", "introducir_datos_bancarios.jsp", "order.jsp", "purchase-history.jsp", "realizar_pedido.jsp", "shopping-cart.jsp", "seleccionar_metodo_pago.jsp"};
    String[] servletsAdmin = {"ActualizarEstadoUsuario", "actualizarPedido", "consultarPedidos", "ConsultaUsuarios", "consultarDetallePedido", "EliminarUsuario", "ProductoServlet", "ResenaServlet", "VideojuegoServlet"};
    String[] paginasAdmin = {"admin-options.jsp", "manage-users.jsp", "crud-products.jsp", "crud-games.jsp", "historial-pagos.jsp", "moderar-resenas.jsp", "pedidos-pendientes.jsp"};

    private String getPathSolicitado(HttpServletRequest request) {
        String uriSolicitada = request.getRequestURI();
        String path = uriSolicitada.substring(request.getContextPath().length());
        return path;
    }

    private boolean isURLUsuario(String path) {
        for (String url : paginasUsuario) {
            if (path.startsWith("/" + url)) return true;
        }
        return false;
    }

    private boolean isServlet(String path) {
        for (String url : servletsAdmin) {
            if (path.startsWith("/" + url)) return true;
        }
        return false;
    }

    private boolean isURLAdmin(String path) {
        for (String url : paginasAdmin) {
            if (path.startsWith("/" + url)) return true;
        }
        return false;
    }

    /**
     * Verifica si existe una sesión válida de Java (JSESSIONID)
     */
    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null && session.getAttribute("usuario") != null);
    }

    /**
     * Verifica si el usuario en sesión es ADMIN
     */
    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (isLoggedIn(request)) {
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            // Asegúrate que el DTO retorna el rol correctamente como String o Enum
            return RolUsuario.valueOf(usuario.getRol()).equals(RolUsuario.ADMIN);
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        httpClient = HttpClient.newBuilder().build();
    }

    /**
     * Valida JWT Server-to-Server
     */
    private String validarTokenYObtenerRol(String token) {
        try {
            String jsonBody = "{\"token\": \"" + token.replace("\"", "\\\"") + "\"}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(token).create(VERIFY_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                int start = responseBody.indexOf("\"role\":\"") + 8;
                int end = responseBody.indexOf("\"", start);
                if (start > 8 && end > start) {
                    return responseBody.substring(start, end);
                }
            }
        } catch (Exception e) {
            System.err.println("Error S2S al validar token: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest peticion = (HttpServletRequest) sr;
        HttpServletResponse respuesta = (HttpServletResponse) sr1;
        String path = getPathSolicitado(peticion);

        boolean isUserPage = this.isURLUsuario(path);
        boolean isAdminPage = this.isURLAdmin(path);
        boolean isAdminServlet = this.isServlet(path);

        // Si la página no está en ninguna lista protegida, dejar pasar
        if (!isUserPage && !isAdminPage && !isAdminServlet) {
            fc.doFilter(sr, sr1);
            return;
        }

        if (isLoggedIn(peticion)) {
            // El usuario ya tiene sesión activa. Validamos roles si es necesario.
            
            // Si intenta entrar a una zona ADMIN, verificamos el rol en la sesión
            if ((isAdminPage || isAdminServlet) && !isAdmin(peticion)) {
                respuesta.sendRedirect(peticion.getContextPath() + "/index.jsp");
                return;
            }
            
            // Si es usuario normal en página de usuario, o admin autorizado, pase.
            fc.doFilter(sr, sr1);
            return; 
        }

        String token = null;
        String authHeader = peticion.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        String userRole = null;
        if (token != null) {
            userRole = validarTokenYObtenerRol(token);
        }

        if (isUserPage && userRole == null) {
            respuesta.sendRedirect(peticion.getContextPath() + "/login.jsp");
            return;
        }

        boolean isTokenAdmin = userRole != null && userRole.equals(RolUsuario.ADMIN.name());
        
        if ((isAdminServlet || isAdminPage) && !isTokenAdmin) {
            respuesta.sendRedirect(peticion.getContextPath() + "/index.jsp");
            return;
        }
        
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); 
    }
}