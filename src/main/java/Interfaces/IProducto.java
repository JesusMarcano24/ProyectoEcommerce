package Interfaces;

import java.util.List;

import modelo.TblProductocl3;

public interface IProducto {
 //Declaramos metodos
	public void RegistrarProducto (TblProductocl3 producto);
	public List<TblProductocl3> ListadoProducto();
	public void ActualizarProducto(TblProductocl3 producto);
	public void EliminarProducto(TblProductocl3 producto);
	public TblProductocl3 BuscarProducto(TblProductocl3 producto);
}
