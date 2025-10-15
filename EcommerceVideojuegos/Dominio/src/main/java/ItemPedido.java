
import java.math.BigDecimal;

/**
 *
 * @author Sebastian Moreno
 */
public class ItemPedido {
    Long id;
    Producto producto;
    BigDecimal precioUnitario;
    int cantidad;

    public ItemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "id=" + id + ", producto=" + producto + ", precioUnitario=" + precioUnitario + ", cantidad=" + cantidad + '}';
    }
    
}
