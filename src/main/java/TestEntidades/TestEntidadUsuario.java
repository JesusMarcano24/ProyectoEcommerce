package TestEntidades;

import java.util.Date;
import java.util.List;

import Dao.UsuarioDaoImpl;
import model.Usuario;

public class TestEntidadUsuario {

	public static void main(String[] args) {
		Usuario usuario= new Usuario();
		UsuarioDaoImpl crud= new UsuarioDaoImpl();
		
		//asignamos valores
		usuario.setNombre("Jesus");
		usuario.setPassword("123");
		usuario.setFechaCreacion(new Date());
		usuario.setEmail("jesus@gmail.com");
		usuario.setRol("admin");
		
		//invocamos el metodo registrar
		
		/*List<TblUsuariocl2> listado=crud.ListadoUsuario();
		for(TblUsuariocl2 list:listado){
			System.out.println("nombre " + list.getUsuariocl2() + " password " + list.getPasswordcl2());
		}*/
	}
	
	
}
