package application.controller;

import java.sql.SQLException;

import application.model.Usuario;

public interface IUsuarioController {
	
	public boolean buscarUsuario(Usuario u) throws ClassNotFoundException, SQLException;
	public boolean verificaUsuario();
}
