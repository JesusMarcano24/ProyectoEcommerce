package TestEntidades;

import java.util.List;

import Dao.UsuarioDaoImpl;
import model.Usuario;

public class TestEntidadUsuario {

	public static void main(String[] args) {
		UsuarioDaoImpl crud= new UsuarioDaoImpl();
		
		List<Usuario> listado=crud.ListadoUsuario();
		for(Usuario list:listado){
			System.out.println("nombre " + list.getNombre() + " password " + list.getPassword() + " email " + list.getEmail() + " fecha de creacion " + list.getFechaCreacion() + " rol " + list.getRol());
		}
	}
	
	
}
