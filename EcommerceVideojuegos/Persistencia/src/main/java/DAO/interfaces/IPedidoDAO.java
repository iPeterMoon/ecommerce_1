package DAO.interfaces;

import entidades.Pedido;
import entidades.Usuario;
import java.util.List;

public interface IPedidoDAO extends IGenericDAO<Pedido, Long> {

    List<Pedido> buscarPorUsuario(Usuario usuario);

    List<Pedido> buscarTodosLosPedidos();

    List<Pedido> buscarPorIdPedidoOIdUsuario(String busqueda);

    Pedido buscarPorIdEspecifico(Long id);
}
