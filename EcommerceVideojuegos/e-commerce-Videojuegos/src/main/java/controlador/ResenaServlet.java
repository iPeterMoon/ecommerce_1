package controlador;

import DTO.ProductoDTO;  
import DTO.ResenaDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import modelo.ProductoBO;
import modelo.ResenaBO;

/**
 *
 * @author lucianobarceloo
 */
@WebServlet(name = "ResenaServlet", urlPatterns = {"/ResenaServlet"})
public class ResenaServlet extends HttpServlet {

    private ResenaBO resenaBO;
    private ProductoBO productoBO; 

    @Override
    public void init() throws ServletException {
        this.resenaBO = new ResenaBO();
        this.productoBO = new ProductoBO(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");

        try {
            // Mostrar reseñas para un producto especifico
            if ("cargarResenas".equals(accion)) {
                Long idProducto = Long.parseLong(request.getParameter("idProducto"));
                
                List<ResenaDTO> lista = resenaBO.listarResenasPorProducto(idProducto); 
                
                request.setAttribute("listaResenas", lista);
                request.getRequestDispatcher("moderar-resenas.jsp").forward(request, response);
                return; 
            }           
            else { // por defecto carga la lista de todos los productos
                List<ProductoDTO> listaProductos = productoBO.listarTodosLosProductos();
                request.setAttribute("listaProductos", listaProductos); 
                request.getRequestDispatcher("videojuegos-resenas.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            e.printStackTrace();
            List<ProductoDTO> listaProductos = productoBO.listarTodosLosProductos();
            request.setAttribute("listaProductos", listaProductos); 
            request.getRequestDispatcher("videojuegos-resenas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");

        try {
            if ("eliminar".equals(accion)) {
                Long idResena = Long.parseLong(request.getParameter("idResena"));
                resenaBO.eliminarResena(idResena);
                
            } else if ("actualizar".equals(accion)) {
                // logica para actualizar (editar)
                ResenaDTO dto = new ResenaDTO();
                dto.setIdResena(Long.parseLong(request.getParameter("idResena")));
                dto.setComentario(request.getParameter("comentario"));
                dto.setCalificacion(Integer.parseInt(request.getParameter("calificacion")));
                
                resenaBO.actualizarResena(dto);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        response.sendRedirect("ResenaServlet"); 
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar reseñas y productos";
    }
}