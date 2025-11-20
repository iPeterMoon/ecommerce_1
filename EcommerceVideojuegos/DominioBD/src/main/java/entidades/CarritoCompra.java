package entidades;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "carritos_compra")
public class CarritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;
    
    @OneToOne(mappedBy = "carritoCompra")
    private Usuario usuario;

    @OneToMany(mappedBy = "carritoCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public CarritoCompra() {
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CarritoCompra{" + "idCarrito=" + idCarrito + ", total=" + total + ", usuario=" + usuario + ", items=" + items + '}';
    }
    
    
}