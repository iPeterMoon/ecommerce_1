package entidades;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_videojuego")
    private Long idVideojuego;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "desarrollador")
    private String desarrollador;

    @Column(name = "ano_lanzamiento", length = 4)
    private String anoLanzamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clasificacion")
    private Clasificacion clasificacion;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "videojuego_categoria",
        joinColumns = @JoinColumn(name = "id_videojuego"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias;

    public Videojuego() {
    }

    public Long getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Long idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(String anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "idVideojuego=" + idVideojuego + ", nombre=" + nombre + ", desarrollador=" + desarrollador + ", anoLanzamiento=" + anoLanzamiento + ", clasificacion=" + clasificacion + ", categorias=" + categorias + '}';
    }
    
    
    
}