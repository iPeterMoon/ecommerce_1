/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import DTO.PlataformaDTO;
import DTO.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import modelo.PlataformaBO;
import modelo.ProductoBO;
import modelo.VideojuegoBO;

/**
 *
 * @author benja
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, 
    maxFileSize = 1024 * 1024 * 10,     
    maxRequestSize = 1024 * 1024 * 15   
)
public class ProductoServlet extends HttpServlet {

    private ProductoBO productoBO;
    private PlataformaBO plataformaBO;
    private VideojuegoBO videojuegoBO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void init() throws ServletException {
        this.productoBO = new ProductoBO();
        this.plataformaBO = new PlataformaBO();
        this.videojuegoBO = new VideojuegoBO();
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

        String action = request.getParameter("action");
        String busqueda = request.getParameter("busqueda");

        try {
            if (action != null && action.equals("editar")) {
                Long id = Long.parseLong(request.getParameter("id"));
                ProductoDTO dto = productoBO.obtenerProductoParaEditar(id);
                request.setAttribute("productoAEditar", dto);
            }

            List<ProductoDTO> lista;
            if (busqueda != null && !busqueda.trim().isEmpty()) {
                lista = productoBO.buscarPorNombre(busqueda);
            } else {
                lista = productoBO.listarTodosLosProductos();
            }
            request.setAttribute("listaProductos", lista);

            List<PlataformaDTO> plataformas = plataformaBO.listarTodas();
            request.setAttribute("listaPlataformas", plataformas);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher("crud-products.jsp").forward(request, response);
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
        
        String method = request.getParameter("_method");

        if (method != null) {
            switch (method.toUpperCase()) {
                case "PUT":
                    doPut(request, response);
                    return;
                case "DELETE":
                    doDelete(request, response);
                    return;
            }
        }

        try {
            ProductoDTO dto = new ProductoDTO();
            dto.setIdVideojuego(Long.parseLong(request.getParameter("idVideojuego")));
            dto.setNombreProducto(request.getParameter("nombreProducto"));
            dto.setIdPlataforma(Long.parseLong(request.getParameter("idPlataforma")));
            dto.setStock(Integer.parseInt(request.getParameter("stock")));
            
            String precioStr = request.getParameter("precio");
            dto.setPrecio(new BigDecimal(precioStr));
            
            dto.setDescripcion(request.getParameter("description"));

            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String rutaImagen = guardarImagen(filePart, request);
                dto.setImagenUrl(rutaImagen);
            } else {
                dto.setImagenUrl("imgs/minecraft.png"); // Imagen por defecto
            }

            productoBO.crearProducto(dto);
            
            response.sendRedirect("VideojuegoServlet?exito=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("VideojuegoServlet?error=" + java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long idProducto = Long.parseLong(request.getParameter("idProducto"));
            
            ProductoDTO productoActual = productoBO.obtenerProductoParaEditar(idProducto);

            ProductoDTO dto = new ProductoDTO();
            dto.setIdProducto(idProducto);
            dto.setNombreProducto(request.getParameter("nombreProducto"));
            dto.setIdPlataforma(Long.parseLong(request.getParameter("idPlataforma")));
            dto.setStock(Integer.parseInt(request.getParameter("stock")));
            
            String precioStr = request.getParameter("precio");
            dto.setPrecio(new BigDecimal(precioStr));

            dto.setDescripcion(request.getParameter("description"));

            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String rutaImagen = guardarImagen(filePart, request);
                dto.setImagenUrl(rutaImagen);
            } else {
                dto.setImagenUrl(productoActual.getImagenUrl());
            }

            productoBO.actualizarProducto(dto);
            response.sendRedirect("ProductoServlet");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            recargarListasParaError(request);
            request.getRequestDispatcher("crud-products.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("idProducto"));
            productoBO.eliminarProducto(id);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            recargarListasParaError(request);
            request.getRequestDispatcher("crud-products.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("ProductoServlet");
    }

    private void recargarListasParaError(HttpServletRequest request) {
        try {
            request.setAttribute("listaProductos", productoBO.listarTodosLosProductos());
            request.setAttribute("listaPlataformas", plataformaBO.listarTodas());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error crítico al recargar listas: " + e.getMessage());
        }
    }

    private String guardarImagen(Part filePart, HttpServletRequest request) throws IOException {
        String rutaRelativa = "temp"; 
        
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + rutaRelativa;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); 
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        
        String fullPath = uploadPath + File.separator + uniqueFileName;
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
        }

        return rutaRelativa + "/" + uniqueFileName; 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para CRUD de Productos";
    }// </editor-fold>

}
