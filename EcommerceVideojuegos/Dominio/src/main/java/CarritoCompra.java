
import java.math.BigDecimal;
import java.util.List;



/**
 *
 * @author Sebastian Moreno
 */
public class CarritoCompra {
    Long id;
    List<ItemCarrito> items;
    BigDecimal total;

    public CarritoCompra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CarritoCompra{" + "id=" + id + ", items=" + items + ", total=" + total + '}';
    }
    
    
}
