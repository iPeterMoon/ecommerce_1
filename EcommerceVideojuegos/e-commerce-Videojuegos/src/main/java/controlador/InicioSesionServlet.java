package controlador;

import java.io.IOException;

import DTO.UsuarioDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 *
 * @author petermoon
 */
@WebServlet(name = "InicioSesionServlet", urlPatterns = {"/InicioSesion"})
public class InicioSesionServlet extends HttpServlet {

    private final Gson gson = new Gson();
    
    @Override
    public void init() {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try (BufferedReader reader = request.getReader()) {
            // Leer el cuerpo de la petición (que contiene el JSON del UsuarioDTO)
            UsuarioDTO usuario = gson.fromJson(reader, UsuarioDTO.class);
            
            if (usuario != null && usuario.getIdUsuario() != null) {
                // Sincronización exitosa: guardar el usuario en la sesión HTTP
                request.getSession().setAttribute("usuario", usuario);
                
                // Responder 200 OK
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"message\": \"Sesión sincronizada con éxito\", \"idUsuario\": " + usuario.getIdUsuario() + "}");
            } else {
                // El cuerpo de la petición no contenía un usuario válido
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Datos de usuario incompletos o inválidos\"}");
            }
        } catch (Exception e) {
            // Error en la lectura o procesamiento del JSON
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error interno al sincronizar sesión: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
