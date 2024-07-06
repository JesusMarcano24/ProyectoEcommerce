package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IProducto;
import modelo.TblProductocl3;

public class ClassProductoImp implements IProducto {

	@Override
	public void RegistrarProducto(TblProductocl3 producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.persist(producto);
		System.out.println("Producto registrado con exito en la BD");
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<TblProductocl3> ListadoProducto() {
		//Gestionamos la entidad
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos los datos
		List<TblProductocl3> listadoProductos=em.createQuery("select p from TblProductocl3 p", TblProductocl3.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return listadoProductos;
	}

	@Override
	public void ActualizarProducto(TblProductocl3 producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.merge(producto);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void EliminarProducto(TblProductocl3 producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		TblProductocl3 elim=em.merge(producto);
		em.remove(elim);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public TblProductocl3 BuscarProducto(TblProductocl3 producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		TblProductocl3 buscarProducto=em.find(TblProductocl3.class, producto.getIdproductoscl3());
		em.getTransaction().commit();
		em.close();
		return buscarProducto;
	}

}
