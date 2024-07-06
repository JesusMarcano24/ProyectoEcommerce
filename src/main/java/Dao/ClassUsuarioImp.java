package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IUsuario;
import modelo.TblUsuariocl3;

public class ClassUsuarioImp implements IUsuario {

	@Override
	public void RegistrarUsuario(TblUsuariocl3 usuario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		System.out.println("Usuario registrado con exito en la BD");
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<TblUsuariocl3> ListadoUsuario() {
		//Gestionamos la entidad
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos los datos
		List<TblUsuariocl3> listadoUsuarios=em.createQuery("select u from TblUsuariocl3 u", TblUsuariocl3.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return listadoUsuarios;
	}

	@Override
	public void ActualizarUsuario(TblUsuariocl3 usuario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void EliminarUsuario(TblUsuariocl3 usuario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		TblUsuariocl3 elim=em.merge(usuario);
		em.remove(elim);
		System.out.println("Usuario eliminado de la base de datos");
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public TblUsuariocl3 BuscarUsuario(TblUsuariocl3 usuario) {
		EntityManagerFactory fabr= Persistence.createEntityManagerFactory("LPII_CL3_MARCANOABREUJESUSFRANCISCO");
		EntityManager em=fabr.createEntityManager();
		em.getTransaction().begin();
		TblUsuariocl3 buscarUsuario=em.find(TblUsuariocl3.class, usuario.getIdusuariocl3());
		em.getTransaction().commit();
		em.close();
		return buscarUsuario;
	}

}
