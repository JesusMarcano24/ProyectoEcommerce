package Interfaces;

import java.util.List;

import model.Inventario;

public interface IInventario {
	 //Declaramos metodos
		public void RegistrarInventario(Inventario inventario);
		public List<Inventario> ListadoInventario();
		public void ActualizarInventario(Inventario inventario);
		public void EliminarInventario(Inventario inventario);
		public Inventario BuscarInventario(Inventario inventario);
}
