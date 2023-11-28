package application.controller;

import java.sql.SQLException;

import application.model.Usuario;
import application.persintence.UsuarioDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UsuarioController implements IUsuarioController{
	
	private TextField tfLoginEmail;
	private PasswordField tfLoginSenha;
	
	

	public UsuarioController(TextField tfLoginEmail, PasswordField tfLoginSenha) {
		this.tfLoginEmail = tfLoginEmail;
		this.tfLoginSenha = tfLoginSenha;
	}



	@Override
	public boolean buscarUsuario(Usuario u) throws ClassNotFoundException, SQLException {
		
		UsuarioDao uDao = new UsuarioDao();
		u = uDao.buscarUsuario(u);
		
		if(u.getEmail().equals(tfLoginEmail.getText()) && u.getSenha().equals(tfLoginSenha.getText())) {
			return true;
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Usuario ou senha Invalido(s)");
			alerta.setTitle("ERRO");
			alerta.show();
			LimpaCampos();
			return false;
		}
	}
	
	private void LimpaCampos() {
		 tfLoginSenha.setText("");
	}
	
	public boolean verificaUsuario() {
		
		if(tfLoginEmail.getText().isEmpty() || tfLoginSenha.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique os Campos");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		}else
			return true;
		}
}
