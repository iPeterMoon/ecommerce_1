
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastian Moreno
 */
public abstract class Usuario  {
    Long id;
    String nombre;
    String correo;
    String telefono;
    List<Direccion> direcciones;
    boolean cuentaActiva;
    String contrasenia;
    String rol;
    List<Tarjeta> tarjetas;
}
