/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import DTO.NuevoUsuarioDTO;
import excepciones.RegistroException;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
public class RegistrarUsuarioServlet extends HttpServlet {

    private UsuarioBO usuarioBO;
    
    @Override
    public void init(){
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
        
        NuevoUsuarioDTO usuario = obtenerDatos(request, response);
        
        if(usuario != null) {
            registrarUsuario(usuario, response);
        }
        
    }
    
    private NuevoUsuarioDTO obtenerDatos(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String nombre = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("pswd");
        String password2 = request.getParameter("confirm-pswd");
        
        if(!password.equals(password2)){
            response.sendRedirect("register.jsp?error=1");
            return null;
        }
        
        NuevoUsuarioDTO usuario = new NuevoUsuarioDTO();
        usuario.setNombre(nombre);
        usuario.setCorreo(mail);
        usuario.setContrasena(password);
        
        return usuario;
    }
    
    private void registrarUsuario(NuevoUsuarioDTO usuario, HttpServletResponse response) throws IOException{
        try{
            usuarioBO.registrarUsuario(usuario);
            response.sendRedirect("login.jsp");
        } catch(RegistroException e){
            response.sendRedirect("register.jsp?error="+e.getCodeError());
        }
    }


}
