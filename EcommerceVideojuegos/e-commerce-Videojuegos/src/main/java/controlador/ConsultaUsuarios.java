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
import java.util.List;
import modelo.UsuarioBO;

/**
 *
 * @author petermoon
 */
@WebServlet(name = "ConsultaUsuarios", urlPatterns = {"/ConsultaUsuarios"})
public class ConsultaUsuarios extends HttpServlet {

    private UsuarioBO usuarioBO;

    @Override
    public void init() {
        this.usuarioBO = new UsuarioBO();
    }

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
        List<UsuarioDTO> usuarios = usuarioBO.cargarUsuarios();

        request.setAttribute("listaUsuarios", usuarios);
        request.getRequestDispatcher("manage-users.jsp").forward(request, response);
        return;

    }

}
