
import java.math.BigDecimal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Sebastian Moreno
 */
public class Item {

    Long id;
    Producto producto;
    BigDecimal precioUnitario;
    int cantidad;

    public Item() {
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

    public BigDecimal getSubtotal() {
        return precioUnitario;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.precioUnitario = subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ItemCarrito{" + "id=" + id + ", producto=" + producto + ", precioUnitario=" + precioUnitario + ", cantidad=" + cantidad + '}';
    }

}
