package application.controller;

import java.sql.SQLException;

import application.model.Usuario;

public interface IMeusDadosController {
	public void buscaDados() throws ClassNotFoundException, SQLException;
}
