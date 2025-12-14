package api;

import DAO.ProductoDAO;
import DTO.ItemDTO;
import entidades.Producto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("cart")
@RequestScoped 
public class CartResource {

    @Context
    private HttpServletRequest request; 

    private ProductoDAO productoDAO = new ProductoDAO(); 

    public CartResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> getJson() {
        HttpSession session = request.getSession(true);
        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");

        if (cart == null) {
            return new ArrayList<>();
        }
        return cart;
    }

  
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCart(@QueryParam("id") Long id) {
        HttpSession session = request.getSession(true);
        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;

        for (ItemDTO item : cart) {
            if (item.getIdProducto().equals(id)) {
                item.setCantidad(item.getCantidad() + 1);

                BigDecimal newSubtotal = item.getPrecioUnitario()
                        .multiply(new BigDecimal(item.getCantidad()));
                item.setSubtotal(newSubtotal);

                found = true;
                break;
            }
        }

        if (!found) {
            Producto p = productoDAO.buscarPorId(id); 

            if (p != null) {
                ItemDTO newItem = new ItemDTO();
                newItem.setIdProducto(p.getIdProducto());
                newItem.setNombreProducto(p.getNombreProducto());
                newItem.setPrecioUnitario(p.getPrecio());
                newItem.setCantidad(1);
                newItem.setSubtotal(p.getPrecio());

                if (p.getImagen() != null) {
                    String base64 = java.util.Base64.getEncoder().encodeToString(p.getImagen());
                    newItem.setImagenBase64("data:image/jpeg;base64," + base64);
                }

                cart.add(newItem);
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
            }
        }

        session.setAttribute("cart", cart);

        int totalItems = cart.stream().mapToInt(ItemDTO::getCantidad).sum();

        return Response.ok("{\"totalItems\": " + totalItems + "}").build();
    }

    @DELETE
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeItem(@QueryParam("id") Long id) {

        HttpSession session = request.getSession();
        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");

        if (cart != null) {
            Iterator<ItemDTO> iterator = cart.iterator();

            while (iterator.hasNext()) {
                ItemDTO item = iterator.next();

                if (item.getIdProducto().equals(id)) {
                    if (item.getCantidad() > 1) {
                        item.setCantidad(item.getCantidad() - 1);

                        BigDecimal newSubtotal = item.getPrecioUnitario()
                                .multiply(new BigDecimal(item.getCantidad()));
                        item.setSubtotal(newSubtotal);

                    } else {
                        iterator.remove();
                    }
                    break;
                }
            }

            session.setAttribute("cart", cart);
        }

        int totalItems = (cart == null) ? 0 : cart.stream().mapToInt(ItemDTO::getCantidad).sum();

        return Response.ok("{\"totalItems\": " + totalItems + "}").build();
    }
}
