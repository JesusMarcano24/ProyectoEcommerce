package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Interfaces.IUsuarioDao;
import model.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {

    public Usuario usuarioLogin(String usuario, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoEcommerce");
        EntityManager em = emf.createEntityManager();
        Usuario usuarioLogin = null;
        try {
            String sql = "SELECT U FROM Usuario U " +
                         "WHERE U.nombre = :nombre AND " +
                         "U.password = :password";
            Query query = em.createQuery(sql, Usuario.class);
            query.setParameter("nombre", usuario);
            query.setParameter("password", password);
            usuarioLogin = (Usuario) query.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Usuario " + usuario + " no encontrado");
        }
        em.close();
        emf.close();
        return usuarioLogin;
    }
}