package entidades;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @Column(name = "stock")
    private Integer stock;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plataforma")
    private Plataforma plataforma;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_videojuego")
    private Videojuego videojuego;

    @OneToMany(mappedBy = "producto")
    private List<Resena> resenas;

    @OneToMany(mappedBy = "producto")
    private List<Item> items;

    public Producto() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", imagenUrl=" + imagenUrl + ", precio=" + precio + ", stock=" + stock + ", descripcion=" + descripcion + ", plataforma=" + plataforma + ", videojuego=" + videojuego + ", resenas=" + resenas + ", items=" + items + '}';
    }
    

}
