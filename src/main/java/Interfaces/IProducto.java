package Interfaces;

import java.util.List;

import model.Producto;

public interface IProducto {
 //Declaramos metodos
	public void RegistrarProducto (Producto producto);
	public List<Producto> ListadoProducto();
	public void ActualizarProducto(Producto producto);
	public void EliminarProducto(Producto producto);
	public Producto BuscarProducto(Producto producto);
}
