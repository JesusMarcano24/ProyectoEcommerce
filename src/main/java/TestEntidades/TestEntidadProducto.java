package TestEntidades;

import java.util.List;

import Dao.ClassProductoImp;
import modelo.TblProductocl3;

public class TestEntidadProducto {

	public static void main(String[] args) {
		TblProductocl3 producto= new TblProductocl3();
		ClassProductoImp crud= new ClassProductoImp();
		
		//asignamos valores
		producto.setNombrecl3("ejemplo1");
		producto.setDescripcl3("example1");
		producto.setEstadocl3("Viejo");
		producto.setPreciocompcl3(10);
		producto.setPrecioventacl3(100);
		
		//invocamos el metodo registrar
		crud.RegistrarProducto(producto);
		
		/*List<TblProductocl2> listado=crud.ListadoProducto();
		for(TblProductocl2 list:listado){
			System.out.println("nombre " + list.getNombrecl2() + " precio de compra " + list.getPreciocompcl2() + " precio de venta " + list.getPrecioventacl2() + " estado " + list.getEstadocl2());
		}*/
	}

}
