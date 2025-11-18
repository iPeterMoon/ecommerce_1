package controlador;

import DTO.CategoriaDTO;
import DTO.ClasificacionDTO;
import DTO.VideojuegoDTO;
import DTO.PlataformaDTO; // <-- NUEVO
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

            List<ClasificacionDTO> clasificaciones = clasificacionBO.listarTodas();
            request.setAttribute("listaClasificaciones", clasificaciones);

            List<CategoriaDTO> categorias = categoriaBO.listarTodas();
            request.setAttribute("listaCategorias", categorias);

            List<PlataformaDTO> plataformas = plataformaBO.listarTodas();
            request.setAttribute("listaPlataformas", plataformas);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
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

        try {
            VideojuegoDTO dto = poblarDTODesdeRequest(request, response);
            if (dto != null) {
                videojuegoBO.crearVideojuego(dto);
                response.sendRedirect("VideojuegoServlet");
            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            recargarListasParaError(request); 
            request.getRequestDispatcher("crud-games.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            VideojuegoDTO dto = poblarDTODesdeRequest(request, response);
            Long id = Long.parseLong(request.getParameter("idVideojuego"));
            dto.setIdVideojuego(id);
            videojuegoBO.actualizarVideojuego(dto);
            response.sendRedirect("VideojuegoServlet");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            recargarListasParaError(request);
            request.getRequestDispatcher("crud-games.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("idVideojuego"));
            videojuegoBO.eliminarVideojuego(id);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            recargarListasParaError(request);
            request.getRequestDispatcher("crud-games.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("VideojuegoServlet");
    }

    private VideojuegoDTO poblarDTODesdeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VideojuegoDTO dto = new VideojuegoDTO();
        dto.setNombre(request.getParameter("game-name"));
        dto.setDesarrollador(request.getParameter("developer"));
        dto.setAnoLanzamiento(request.getParameter("release-year"));

        String idClasif = request.getParameter("classification");

        if (idClasif != null && !idClasif.isEmpty()) {
            dto.setIdClasificacion(Long.parseLong(idClasif));
        } else {
            response.sendRedirect("VideojuegoServlet?error=1#game-modal");
            return null;
        }

        String[] idsCats = request.getParameterValues("category");
        if (idsCats != null) {
            Set<Long> ids = Arrays.stream(idsCats)
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            dto.setIdsCategorias(ids);
        } else {
            response.sendRedirect("VideojuegoServlet?error=1#game-modal");
            return null;
        }
        return dto;
    }

    private void recargarListasParaError(HttpServletRequest request) {
        List<VideojuegoDTO> lista = videojuegoBO.listarTodosLosVideojuegos();
        request.setAttribute("listaVideojuegos", lista);
        List<ClasificacionDTO> clasificaciones = clasificacionBO.listarTodas();
        request.setAttribute("listaClasificaciones", clasificaciones);
        List<CategoriaDTO> categorias = categoriaBO.listarTodas();
        request.setAttribute("listaCategorias", categorias);
        List<PlataformaDTO> plataformas = plataformaBO.listarTodas();
        request.setAttribute("listaPlataformas", plataformas);
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
