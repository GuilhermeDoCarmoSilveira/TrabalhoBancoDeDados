package application.controller;

import java.sql.SQLException;

import application.model.Cliente;
import application.model.Empresa;
import application.model.Usuario;
import application.persintence.MeusDadosDao;
import javafx.scene.control.TextArea;

public class MeusDadosController implements IMeusDadosController {

	private TextArea taMeusDadosInfo;
	private Usuario u;

	public MeusDadosController(TextArea taMeusDadosInfo, Usuario u) {
		super();
		this.taMeusDadosInfo = taMeusDadosInfo;
		this.u = u;
	}

	@Override
	public void buscaDados() throws ClassNotFoundException, SQLException {
		MeusDadosDao mDao = new MeusDadosDao();

		if (mDao.verificaTipoUsuario(u)) {
			String sexo = "";
			Cliente cl = mDao.buscaDadosUsuario(u, true);
			StringBuffer mostraDados = new StringBuffer();
			
			mostraDados.append("\nCpf: " + cl.getCpf() + "\n\n" + "Nome: " + cl.getNome() + "\n\n" + "Telefone: "
					+ cl.getTelefone() + "\n\n" + "Data Nascimento: " + cl.getDtNascimento() + "\n\n" + "Sexo: " + cl.getSexo()
					+ "\n\n" + "Email: " + cl.getEmail());
			taMeusDadosInfo.setText(mostraDados.toString());
		}
		if (!mDao.verificaTipoUsuario(u)) {
			Empresa emp = mDao.buscaDadosUsuario(u, false);
			StringBuffer mostraDados = new StringBuffer();
			mostraDados.append("\n\nCnpj: " + emp.getCnpj() + "\n\n" + "Razão Social: " + emp.getRazaoSocial() + "\n\n"
					+ "Apelido: " + emp.getApelido() + "\n\n" + "Telefone: " + emp.getTelefone() + "\n\n" + "Email: "
					+ emp.getEmail());
			taMeusDadosInfo.setText(mostraDados.toString());
		}
	}

	public boolean verificaTipoUsuario() throws ClassNotFoundException, SQLException {
		MeusDadosDao mDao = new MeusDadosDao();
		if (mDao.verificaTipoUsuario(u)) {
			return true;
		}else{
			return false;
		}
	}

}
