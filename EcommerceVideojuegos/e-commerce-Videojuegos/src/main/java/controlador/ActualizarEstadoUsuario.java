/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import DTO.UsuarioDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.UsuarioBO;

/**
 *
 * @author petermoon
 */
@WebServlet(name = "ActualizarEstadoUsuario", urlPatterns = {"/ActualizarEstadoUsuario"})
public class ActualizarEstadoUsuario extends HttpServlet {

    private UsuarioBO usuarioBO;

    @Override
    public void init() {
        this.usuarioBO = new UsuarioBO();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
            
            UsuarioDTO usuario = usuarioBO.getUsuarioPorId(idUsuario);
            if (usuario != null) {
                usuarioBO.alternarActividadUsuario(idUsuario);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        // Redirigir de vuelta a la lista de usuarios
        response.sendRedirect("ConsultaUsuarios");
    }

}
