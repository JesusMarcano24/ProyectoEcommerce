package Interfaces;

import java.util.List;

import modelo.TblProductocl3;
import modelo.TblUsuariocl3;

public interface IUsuario {
 //Declaramos metodos
	public void RegistrarUsuario(TblUsuariocl3 usuario);
	public List<TblUsuariocl3> ListadoUsuario();
	public void ActualizarUsuario(TblUsuariocl3 usuario);
	public void EliminarUsuario(TblUsuariocl3 usuario);
	public TblUsuariocl3 BuscarUsuario(TblUsuariocl3 usuario);
}
