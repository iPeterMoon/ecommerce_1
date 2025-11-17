package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import DTO.UsuarioDTO;
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
@WebServlet(name = "InicioSesionServlet", urlPatterns = {"/InicioSesion"})
public class InicioSesionServlet extends HttpServlet {

    private UsuarioBO usuarioBO;

    @Override
    public void init(){
        usuarioBO = new UsuarioBO();
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
                String mail = request.getParameter("mail");
                String password = request.getParameter("pswd");
                UsuarioDTO usuario = usuarioBO.autenticarUsuario(mail, password);
                if (usuario != null) {
                    // Autenticación exitosa
                    request.getSession().setAttribute("usuario", usuario);
                    response.sendRedirect("index.jsp");
                } else {
                    // Autenticación fallida
                    response.sendRedirect("login.jsp?error=1");
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
