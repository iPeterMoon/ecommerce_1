package entidades;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "plataforma")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plataforma")
    private Long idPlataforma;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @OneToMany(mappedBy = "plataforma")
    private List<Producto> productos;

    public Plataforma() {
    }

    public Long getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Plataforma{" + "idPlataforma=" + idPlataforma + ", nombre=" + nombre + ", productos=" + productos + '}';
    }
    
    
}