package TestEntidades;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;

import Dao.ClassProductoImp;
import model.Producto;

public class TestEntidadProducto {

	public static void main(String[] args) {
		Producto producto= new Producto();
		ClassProductoImp crud= new ClassProductoImp();
		
		//asignamos valores
		producto.setNombre("ejemplo1");
		producto.setDescripcion("descripcion");
		producto.setPrecio(new BigDecimal("50.50"));
		producto.setFechaCreacion(new Date());
		
		//invocamos el metodo registrar
		crud.RegistrarProducto(producto);
		
		/*List<TblProductocl2> listado=crud.ListadoProducto();
		for(TblProductocl2 list:listado){
			System.out.println("nombre " + list.getNombrecl2() + " precio de compra " + list.getPreciocompcl2() + " precio de venta " + list.getPrecioventacl2() + " estado " + list.getEstadocl2());
		}*/
	}

}
