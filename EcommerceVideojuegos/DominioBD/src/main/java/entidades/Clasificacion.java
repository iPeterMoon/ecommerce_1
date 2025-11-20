package entidades;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "clasificaciones")
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion")
    private Long idClasificacion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @OneToMany(mappedBy = "clasificacion")
    private List<Videojuego> videojuegos;

    public Clasificacion() {
    }

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Long idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @Override
    public String toString() {
        return "Clasificacion{" + "idClasificacion=" + idClasificacion + ", nombre=" + nombre + ", videojuegos=" + videojuegos + '}';
    }
    
    
}