/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import DTO.PlataformaDTO;
import DTO.ProductoDTO;
import DTO.VideojuegoDTO;
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
            if ("editar".equals(action)) {
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
            request.setAttribute("listaPlataformas", plataformaBO.listarTodas());

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
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
            String idVj = request.getParameter("idVideojuego");
            String nom = request.getParameter("nombreProducto");
            String idPlat = request.getParameter("idPlataforma");
            String stock = request.getParameter("stock");
            String precio = request.getParameter("precio");

            if (idVj == null || nom == null || idPlat == null || stock == null || precio == null) {
                throw new Exception("Faltan campos obligatorios.");
            }

            dto.setIdVideojuego(Long.parseLong(idVj));
            dto.setNombreProducto(nom);
            dto.setIdPlataforma(Long.parseLong(idPlat));
            dto.setStock(Integer.parseInt(stock));
            dto.setPrecio(new BigDecimal(precio));
            dto.setDescripcion(request.getParameter("description"));

            VideojuegoDTO vjDto = videojuegoBO.obtenerVideojuegoParaEditar(dto.getIdVideojuego());
            String nombrePlataforma = obtenerNombrePlataforma(dto.getIdPlataforma());

            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String rutaGuardada = guardarImagenInterna(filePart, request, vjDto, nombrePlataforma);
                dto.setImagenUrl(rutaGuardada);
            } else {
                dto.setImagenUrl("imgs/minecraft.png");
            }

            productoBO.crearProducto(dto);
            response.sendRedirect("VideojuegoServlet?exito=1");

        } catch (Exception e) {
            request.setAttribute("error", "Error al crear: " + e.getMessage());
            request.setAttribute("errorCrearProducto", "1");
            recargarListasParaError(request);
            request.getRequestDispatcher("crud-games.jsp").forward(request, response);
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
            dto.setPrecio(new BigDecimal(request.getParameter("precio")));
            dto.setDescripcion(request.getParameter("description"));

            VideojuegoDTO vjDto = videojuegoBO.obtenerVideojuegoParaEditar(productoActual.getIdVideojuego());
            String nombrePlataforma = obtenerNombrePlataforma(dto.getIdPlataforma());

            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String rutaGuardada = guardarImagenInterna(filePart, request, vjDto, nombrePlataforma);
                dto.setImagenUrl(rutaGuardada);
            } else {
                dto.setImagenUrl(productoActual.getImagenUrl());
            }

            productoBO.actualizarProducto(dto);
            response.sendRedirect("ProductoServlet");

        } catch (Exception e) {
            if (request.getParameter("idProducto") != null) {
                String id = request.getParameter("idProducto");
                response.sendRedirect("ProductoServlet?action=editar&id=" + id + "&errorEditar=1#game-edit-modal");
            } else {
                request.setAttribute("error", e.getMessage());
                recargarListasParaError(request);
                request.getRequestDispatcher("crud-products.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("idProducto"));
            productoBO.eliminarProducto(id);
            response.sendRedirect("ProductoServlet");
        } catch (Exception e) {
            if (e.getMessage().contains("pedidos") || e.getMessage().contains("asociados")) {
                response.sendRedirect("ProductoServlet?errorEliminar=1");
            } else {
                request.setAttribute("error", e.getMessage());
                recargarListasParaError(request);
                request.getRequestDispatcher("crud-products.jsp").forward(request, response);
            }
        }
    }

    private void recargarListasParaError(HttpServletRequest request) {
        try {
            if (request.getServletPath().contains("Videojuego")) {
                request.setAttribute("listaVideojuegos", videojuegoBO.listarTodosLosVideojuegos());
            }
            request.setAttribute("listaProductos", productoBO.listarTodosLosProductos());
            request.setAttribute("listaPlataformas", plataformaBO.listarTodas());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String guardarImagenInterna(Part filePart, HttpServletRequest request, VideojuegoDTO vjDto, String nombrePlataforma) throws IOException {
        String uploadPath = request.getServletContext().getRealPath("/imgs");

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String nombreJuego = limpiarCadena(vjDto.getNombre());
        String plataforma = limpiarCadena(nombrePlataforma);
        String desarrolladora = limpiarCadena(vjDto.getDesarrollador());

        String fileNameOriginal = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String extension = "";
        int i = fileNameOriginal.lastIndexOf('.');
        if (i > 0) {
            extension = fileNameOriginal.substring(i);
        }

        String finalName = nombreJuego + "_" + plataforma + "_" + desarrolladora + extension;

        String fullPath = uploadPath + File.separator + finalName;

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
        }

        return "imgs/" + finalName;
    }

    private String limpiarCadena(String input) {
        if (input == null) {
            return "Desconocido";
        }
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    private String obtenerNombrePlataforma(Long idPlataforma) {
        List<PlataformaDTO> lista = plataformaBO.listarTodas();
        for (PlataformaDTO p : lista) {
            if (p.getIdPlataforma().equals(idPlataforma)) {
                return p.getNombre();
            }
        }
        return "Generica";
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
