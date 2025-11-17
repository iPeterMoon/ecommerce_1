package entidades;
import jakarta.persistence.*;
import java.util.List;

import enums.RolUsuario;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "cuenta_activa")
    private Boolean cuentaActiva;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 50)
    private RolUsuario rol;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrito", unique = true, nullable = false)
    private CarritoCompra carritoCompra;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
    
    @OneToMany(mappedBy = "usuario")
    private List<Resena> resenas;

    public Usuario() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(Boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    //TALVEZ AÑADIR EL ADD PARA LOS PEDIDOS.
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + ", contrasena=" + contrasena + ", cuentaActiva=" + cuentaActiva + ", rol=" + rol + ", carritoCompra=" + carritoCompra + ", direcciones=" + direcciones + ", pedidos=" + pedidos + ", resenas=" + resenas + '}';
    }
    
    
    
}