package modelo;

import java.util.List;

import DAO.UsuarioDAO;
import DAO.interfaces.IUsuarioDAO;
import DTO.NuevoUsuarioDTO;
import DTO.UsuarioDTO;
import DTO.DireccionDTO;
import entidades.CarritoCompra;
import entidades.Direccion;
import entidades.Usuario;
import enums.RolUsuario;
import excepciones.RegistroException;
import java.util.LinkedList;
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

    public List<UsuarioDTO> cargarUsuarios(){
        List<Usuario> usuariosEntidades = usuarioDAO.buscarTodos();
        List<UsuarioDTO> usuariosDTOs = new LinkedList<>();  
        for(Usuario usuario : usuariosEntidades) {
            usuariosDTOs.add(toUsuarioDTO(usuario));
        }
        return usuariosDTOs;
    }
    
    public UsuarioDTO getUsuarioPorId(Long id){
        Usuario usuarioEntidad = usuarioDAO.buscarPorId(id);
        if(usuarioEntidad != null) {
            return toUsuarioDTO(usuarioEntidad);
        }
        return null;
    }

    public UsuarioDTO alternarActividadUsuario(Long id){
        Usuario usuarioEntidad = usuarioDAO.alternarActividad(id);
        if(usuarioEntidad != null){
            return toUsuarioDTO(usuarioEntidad);
        }
        return null;
    }

    public UsuarioDTO eliminarUsuario(Long id){
        Usuario usuarioEntidad = usuarioDAO.buscarPorId(id);
        if(usuarioEntidad != null) {
            usuarioDAO.eliminarConCascada(id);
            return toUsuarioDTO(usuarioEntidad);
        } else {
            return null;
        }
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
        dto.setDirecciones(toDireccionesDTO(usuario.getDirecciones()));
        dto.setCuentaActiva(usuario.getCuentaActiva());
        return dto;
    }

    private List<DireccionDTO> toDireccionesDTO(List<Direccion> direcciones){
        List<DireccionDTO> direccionesDTO = direcciones.stream().map(direccion -> {
            DireccionDTO dto = new DireccionDTO();
            dto.setIdDireccion(direccion.getIdDireccion());
            dto.setCalle(direccion.getCalle());
            dto.setColonia(direccion.getColonia());
            dto.setCiudad(direccion.getCiudad());
            dto.setEstado(direccion.getEstado());
            dto.setCodigoPostal(direccion.getCodigoPostal());
            return dto;
        }).toList();
        return direccionesDTO;
    }

    
}
