package modelo;

import DAO.UsuarioDAO;
import DAO.interfaces.IUsuarioDAO;
import DTO.UsuarioDTO;
import entidades.Usuario;

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
