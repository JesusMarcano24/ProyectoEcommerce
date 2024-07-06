package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String usuario = request.getParameter("nombre");
		String clave = request.getParameter("password");
		Usuario usuarioLogin = usuarioDaoImpl.usuarioLogin(usuario, clave);
		if(usuarioLogin == null) {
			request.setAttribute("loginError", "Credenciales incorrectas");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("Menu.jsp");
			rd.forward(request, response);
		}
	}

}