package Interfaces;

import java.util.List;

import model.Usuario;

public interface IUsuarioDao {
	Usuario usuarioLogin(String usuario, String clave);
	public List<Usuario> ListadoUsuario();
}