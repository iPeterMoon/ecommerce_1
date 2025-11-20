package entidades;
import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @ManyToMany(mappedBy = "categorias")
    private Set<Videojuego> videojuegos;

    public Categoria() {
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(Set<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + ", videojuegos=" + videojuegos + '}';
    }
    
    
}