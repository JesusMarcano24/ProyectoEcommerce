package TestEntidades;

import java.util.List;

import Dao.ClassUsuarioImp;
import modelo.TblUsuariocl3;

public class TestEntidadUsuario {

	public static void main(String[] args) {
		TblUsuariocl3 usuario= new TblUsuariocl3();
		ClassUsuarioImp crud= new ClassUsuarioImp();
		
		//asignamos valores
		usuario.setUsuariocl3("Andres");
		usuario.setPasswordcl3("sdlvnslvs");
		
		//invocamos el metodo registrar
		crud.RegistrarUsuario(usuario);
		
		/*List<TblUsuariocl2> listado=crud.ListadoUsuario();
		for(TblUsuariocl2 list:listado){
			System.out.println("nombre " + list.getUsuariocl2() + " password " + list.getPasswordcl2());
		}*/
	}
	
	
}
