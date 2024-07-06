package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IProducto;
import model.Producto;

public class ClassProductoImp implements IProducto {

	@Override
	public void RegistrarProducto(Producto producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.persist(producto);
		System.out.println("Producto registrado con exito en la BD");
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Producto> ListadoProducto() {
		//Gestionamos la entidad
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos los datos
		List<Producto> listadoProductos=em.createQuery("select p from Producto p", Producto.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return listadoProductos;
	}

	@Override
	public void ActualizarProducto(Producto producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.merge(producto);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void EliminarProducto(Producto producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		Producto elim=em.merge(producto);
		em.remove(elim);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Producto BuscarProducto(Producto producto) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		Producto buscarProducto=em.find(Producto.class, producto.getId());
		em.getTransaction().commit();
		em.close();
		return buscarProducto;
	}

}
