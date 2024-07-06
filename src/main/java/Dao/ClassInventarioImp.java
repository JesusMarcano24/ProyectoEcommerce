package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IInventario;
import model.Inventario;;

public class ClassInventarioImp implements IInventario{

	@Override
	public void RegistrarInventario(Inventario inventario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.persist(inventario);
		System.out.println("Inventario registrado con exito en la BD");
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Inventario> ListadoInventario() {
		//Gestionamos la entidad
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos los datos
		List<Inventario> listadoInventarios=em.createQuery("select p from Inventario p", Inventario.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return listadoInventarios;
	}

	@Override
	public void ActualizarInventario(Inventario inventario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.merge(inventario);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void EliminarInventario(Inventario inventario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		Inventario elim=em.merge(inventario);
		em.remove(elim);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Inventario BuscarInventario(Inventario inventario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("ProyectoEcommerce");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		Inventario buscarInventario=em.find(Inventario.class, inventario.getId());
		em.getTransaction().commit();
		em.close();
		return buscarInventario;
	}

}
