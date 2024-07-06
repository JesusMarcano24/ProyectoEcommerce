package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UsuarioDaoImpl;
import model.Usuario;

/**
 * Servlet implementation class ControladorUsuario
 */
public class ControladorUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Instanciamos
				Usuario usuario= new Usuario();
				UsuarioDaoImpl crud = new UsuarioDaoImpl();
				List<Usuario> listadoUsuario= crud.ListadoUsuario();
				
				//Hacemos el listado de productos
				request.setAttribute("listadoUsuarios", listadoUsuario);
				//Redireccion
				request.getRequestDispatcher("/ListadoUsuarios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        Usuario usuario = usuarioDao.usuarioLogin(nombre, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario.getNombre());
            session.setAttribute("rolLogueado", usuario.getRol());
            response.sendRedirect("Menu.jsp");
        } else {
            request.setAttribute("loginError", "Usuario o contraseña incorrecta.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}