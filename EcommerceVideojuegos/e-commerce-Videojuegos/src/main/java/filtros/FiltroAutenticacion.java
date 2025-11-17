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

/**
 * FiltroAutenticacion.java
 * 
 * Filtro para delimitar el acceso a los usuarios a páginas que solo se pueden acceder
 * cuando tienen la sesión iniciada y para páginas a las que solo pueden acceder administradores
 * 
 * @author Pedro Luna
 */
@WebFilter("/*")
public class FiltroAutenticacion implements Filter{
    
    String[] paginasPublicas = {"index.jsp", "catalogo.jsp", "producto.jsp", "login.jsp", "register.jsp"};
    String[] paginasAdmin = {"admin-options.jsp", "manage-users.jsp", "crud-products.jsp", "crud-games.jsp", "historial-pagos.jsp", "moderar-resenas.jsp", "pedidos-pendientes.jsp"};

    /**
     * Metodo para obtener el path al que se quiere acceder
     * @param request 
     * @return El path solicitado en string
     */
    private String getPathSolicitado(HttpServletRequest request) {
        String uriSolicitada = request.getRequestURI();
        String path = uriSolicitada.substring(request.getContextPath().length());
        return path;
    }

    /**
     * Metodo para saber si una url es privada
     * @param path url a la que se quiere acceder
     * @return true si es privada, falso si no es privada
     */
    private boolean isURLPrivada(String path) {
        for (String url : paginasPublicas) {
            if(path.startsWith("/" + url)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo para saber si una URL es una pagina a la
     * que solo puede acceder un administrador.
     * 
     * @param path Url a la que se quiere acceder
     * @return true si es una url de admin, false si no
     */
    private boolean isURLAdmin(String path) {
        for (String url : paginasAdmin) {
            if(path.startsWith("/" + url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para saber si hay un usuario en la sesión de la aplicación
     * 
     * @param request Solicitud Http donde viene la sesión
     * @return true si hay un usuario, false si no.
     */
    private boolean isLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        boolean logged = (session != null && session.getAttribute("usuario") != null);
        return logged;
    }

    /**
     * Metodo para saber si el usuario loggeado es administrador
     * o no
     * 
     * @param request Solicitud Http donde viene la sesión
     * @return true si el usuario es admin, false si no
     */
    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(isLoggedIn(request)){
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            if(RolUsuario.valueOf(usuario.getRol()).equals(RolUsuario.ADMIN)) {
                return true;
            } else {
                return false;
            }
        } 
        return false;
    }

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest peticion = (HttpServletRequest) sr;
        HttpServletResponse respuesta = (HttpServletResponse) sr1;
        String path = getPathSolicitado(peticion);
        boolean privada = this.isURLPrivada(path);
        boolean loggedIn = this.isLoggedIn(peticion);
        boolean adminPage = this.isURLAdmin(path);
        boolean isAdmin = this.isAdmin(peticion);
        
        if(privada && !loggedIn){
            String referer = peticion.getHeader("Referer");
            String redirectURL = (referer != null && !referer.isEmpty()) ? referer : peticion.getContextPath() + "/index.jsp";
            respuesta.sendRedirect(redirectURL);
            return;
        } else if(adminPage && !isAdmin){
            String referer = peticion.getHeader("Referer");
            String redirectURL = (referer != null && !referer.isEmpty()) ? referer : peticion.getContextPath() + "/index.jsp";
            respuesta.sendRedirect(redirectURL);
            return;
        } else {
            fc.doFilter(sr, sr1);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
