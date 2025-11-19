package controlador;

import DTO.VideojuegoDTO;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import modelo.CategoriaBO;
import modelo.ClasificacionBO;
import modelo.VideojuegoBO;
import modelo.PlataformaBO;
/**
 *
 * @author benja
 */
@WebServlet(name = "VideojuegoServlet", urlPatterns = {"/VideojuegoServlet"})
public class VideojuegoServlet extends HttpServlet {

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
            out.println("<title>Servlet ConsultaPedido</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultaPedido at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private VideojuegoBO videojuegoBO;
    private ClasificacionBO clasificacionBO;
    private CategoriaBO categoriaBO;
    private PlataformaBO plataformaBO;

    @Override
    public void init() throws ServletException {
        this.videojuegoBO = new VideojuegoBO();
        this.clasificacionBO = new ClasificacionBO();
        this.categoriaBO = new CategoriaBO();
        this.plataformaBO = new PlataformaBO();
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

        String idParam = request.getParameter("id");
        String busqueda = request.getParameter("busqueda");

        try {
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                VideojuegoDTO dto = videojuegoBO.obtenerVideojuegoParaEditar(id);
                if (dto == null) {
                    throw new Exception("El videojuego no existe.");
                }
                request.setAttribute("videojuegoAEditar", dto);
            }

            List<VideojuegoDTO> lista;
            if (busqueda != null && !busqueda.trim().isEmpty()) {
                lista = videojuegoBO.buscarPorNombre(busqueda);
            } else {
                lista = videojuegoBO.listarTodosLosVideojuegos();
            }
            request.setAttribute("listaVideojuegos", lista);
            recargarListas(request);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            recargarListas(request);
        }
        request.getRequestDispatcher("crud-games.jsp").forward(request, response);
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

        VideojuegoDTO dto = new VideojuegoDTO();
        try {
            poblarDTO(dto, request);
            validarDTO(dto);

            videojuegoBO.crearVideojuego(dto);
            response.sendRedirect("VideojuegoServlet");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            if (e.getMessage().contains("existe") || e.getMessage().contains("campos")) {
                request.setAttribute("abrirModalAgregar", true);
            }
            recargarListas(request);
            request.getRequestDispatcher("crud-games.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VideojuegoDTO dto = new VideojuegoDTO();
        try {
            String idStr = request.getParameter("idVideojuego");
            if (idStr != null && !idStr.isEmpty()) {
                dto.setIdVideojuego(Long.parseLong(idStr));
            }

            poblarDTO(dto, request);
            validarDTO(dto);

            videojuegoBO.actualizarVideojuego(dto);
            response.sendRedirect("VideojuegoServlet");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("videojuegoAEditar", dto);

            recargarListas(request);
            request.getRequestDispatcher("crud-games.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("idVideojuego"));
            videojuegoBO.eliminarVideojuego(id);
            response.sendRedirect("VideojuegoServlet");
        } catch (Exception e) {
            if (e.getMessage().contains("asociados")) {
                response.sendRedirect("VideojuegoServlet?errorEliminar=1");
            } else {
                request.setAttribute("error", e.getMessage());
                recargarListas(request);
                request.getRequestDispatcher("crud-games.jsp").forward(request, response);
            }
        }
    }

    private void poblarDTO(VideojuegoDTO dto, HttpServletRequest request) {
        dto.setNombre(request.getParameter("game-name"));
        dto.setDesarrollador(request.getParameter("developer"));
        dto.setAnoLanzamiento(request.getParameter("release-year"));

        String idClasif = request.getParameter("classification");
        if (idClasif != null && !idClasif.isEmpty()) {
            try {
                dto.setIdClasificacion(Long.parseLong(idClasif));
            } catch (NumberFormatException e) {
            }
        }

        String[] idsCats = request.getParameterValues("category");
        if (idsCats != null) {
            Set<Long> ids = Arrays.stream(idsCats).map(Long::parseLong).collect(Collectors.toSet());
            dto.setIdsCategorias(ids);
        }
    }

    private void validarDTO(VideojuegoDTO dto) throws Exception {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()
                || dto.getDesarrollador() == null || dto.getDesarrollador().trim().isEmpty()
                || dto.getAnoLanzamiento() == null || dto.getAnoLanzamiento().trim().isEmpty()
                || dto.getIdClasificacion() == null
                || dto.getIdsCategorias() == null || dto.getIdsCategorias().isEmpty()) {
            throw new Exception("Llena todos los campos del formulario.");
        }
    }

    private void recargarListas(HttpServletRequest request) {
        if (request.getAttribute("listaVideojuegos") == null) {
            request.setAttribute("listaVideojuegos", videojuegoBO.listarTodosLosVideojuegos());
        }
        request.setAttribute("listaClasificaciones", clasificacionBO.listarTodas());
        request.setAttribute("listaCategorias", categoriaBO.listarTodas());
        request.setAttribute("listaPlataformas", plataformaBO.listarTodas());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
