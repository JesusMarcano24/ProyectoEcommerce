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
	}

}
