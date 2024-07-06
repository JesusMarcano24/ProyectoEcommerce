package Interfaces;

import model.Usuario;

public interface IUsuarioDao {
	Usuario usuarioLogin(String usuario, String clave);
}