package modelo;

import DAO.UsuarioDAO;
import DAO.interfaces.IUsuarioDAO;
import DTO.NuevoUsuarioDTO;
import DTO.UsuarioDTO;
import entidades.CarritoCompra;
import entidades.Usuario;
import enums.RolUsuario;
import excepciones.RegistroException;
import util.Security;

/**
 *
 * @author petermoon
 */
public class UsuarioBO {
    
    private IUsuarioDAO usuarioDAO;

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public UsuarioDTO autenticarUsuario(String email, String password) {
        Usuario usuarioEntidad = usuarioDAO.buscarPorCredenciales(email, password);
        if (usuarioEntidad != null) {
            return toUsuarioDTO(usuarioEntidad);
        }
        return null;
    }
    
    public void registrarUsuario(NuevoUsuarioDTO usuario) throws RegistroException{
        if(usuarioDAO.buscarPorEmail(usuario.getCorreo()) == null){
            Usuario usuarioEntidad = new Usuario();
            usuarioEntidad.setNombre(usuario.getNombre());
            usuarioEntidad.setCorreo(usuario.getCorreo());
            usuarioEntidad.setContrasena(Security.hashear(usuario.getContrasena()));
            usuarioEntidad.setRol(RolUsuario.CLIENTE);
            usuarioEntidad.setCuentaActiva(true);
            
            CarritoCompra carrito = new CarritoCompra();
            
            usuarioEntidad.setCarritoCompra(carrito);
            
            carrito.setUsuario(usuarioEntidad);
            
            usuarioDAO.crear(usuarioEntidad);
            
        } else {
            throw new RegistroException(2);
        }
    }
    
    public UsuarioDTO getUsuarioPorEmail(String email){
        Usuario usuarioEntidad = usuarioDAO.buscarPorEmail(email);
        if(usuarioEntidad != null){
            return toUsuarioDTO(usuarioEntidad);
        }
        return null;
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol().toString());
        return dto;
    }
    
}
