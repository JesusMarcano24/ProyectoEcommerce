package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IInventario;
import model.Inventario;

public class ClassInventarioImp implements IInventario {

    @Override
    public void RegistrarInventario(Inventario inventario) {
        EntityManagerFactory fabr = Persistence.createEntityManagerFactory("ProyectoEcommerce");
        EntityManager em = fabr.createEntityManager();
        em.getTransaction().begin();
        em.persist(inventario);
        System.out.println("Inventario registrado con éxito en la BD");
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Inventario> ListadoInventario() {
        EntityManagerFactory fabr = Persistence.createEntityManagerFactory("ProyectoEcommerce");
        EntityManager em = fabr.createEntityManager();
        em.getTransaction().begin();
        List<Inventario> listadoInventarios = em.createQuery("select p from Inventario p", Inventario.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return listadoInventarios;
    }

    @Override
    public void ActualizarInventario(Inventario inventario) {
        EntityManagerFactory fabr = Persistence.createEntityManagerFactory("ProyectoEcommerce");
        EntityManager em = fabr.createEntityManager();
        em.getTransaction().begin();
        em.merge(inventario);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void EliminarInventario(Inventario inventario) {
        EntityManagerFactory fabr = Persistence.createEntityManagerFactory("ProyectoEcommerce");
        EntityManager em = fabr.createEntityManager();
        em.getTransaction().begin();
        try {
            Inventario inventarioExistente = em.find(Inventario.class, inventario.getId());
            if (inventarioExistente != null) {
                em.remove(inventarioExistente);
                System.out.println("Inventario eliminado con éxito en la BD");
            } else {
                System.out.println("Inventario no encontrado para eliminar");
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Inventario BuscarInventario(Inventario inventario) {
        EntityManagerFactory fabr = Persistence.createEntityManagerFactory("ProyectoEcommerce");
        EntityManager em = fabr.createEntityManager();
        em.getTransaction().begin();
        Inventario buscarInventario = em.find(Inventario.class, inventario.getId());
        em.getTransaction().commit();
        em.close();
        return buscarInventario;
    }
}