package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IPedido;
import model.Pedido;

public class ClassPedidoImp implements IPedido {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoEcommerce");

    public void RegistrarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
    }

    public List<Pedido> ListadoPedido() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Pedido> listadoPedidos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return listadoPedidos;
    }

    public void ActualizarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
        em.close();
    }

    public void EliminarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pedido pedidoExistente = em.find(Pedido.class, pedido.getId());
        if (pedidoExistente != null) {
            em.remove(pedidoExistente);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Pedido BuscarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pedido buscarPedido = em.find(Pedido.class, pedido.getId());
        em.getTransaction().commit();
        em.close();
        return buscarPedido;
    }
}