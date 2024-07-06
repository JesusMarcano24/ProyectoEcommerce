package Interfaces;

import java.util.List;

import model.Pedido;

public interface IPedido {
 //Declaramos metodos
	public void RegistrarPedido (Pedido pedido);
	public List<Pedido > ListadoPedido ();
	public void ActualizarPedido(Pedido  pedido);
	public void EliminarPedido (Pedido  pedido);
	public Pedido  BuscarPedido (Pedido  pedido);
}
