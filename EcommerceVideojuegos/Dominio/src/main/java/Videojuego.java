
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Sebastian Moreno
 */
public class Videojuego extends Producto {

    Long id;
    String desarrolladora;
    String clasificacion;
    int añoLanzamiento;
    String nombre;
    List<Categoria> categorias;

    public Videojuego() {
    }

    public Long getId() {
        return id;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getAñoLanzamiento() {
        return añoLanzamiento;
    }

    public void setAñoLanzamiento(int añoLanzamiento) {
        this.añoLanzamiento = añoLanzamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "id=" + id + ", desarrolladora=" + desarrolladora + ", clasificacion=" + clasificacion + ", a\u00f1oLanzamiento=" + añoLanzamiento + ", nombre=" + nombre + ", categorias=" + categorias + '}';
    }

}
