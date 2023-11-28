package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Endereco;
import application.model.Usuario;
import application.persintence.EnderecoDao;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EnderecoController implements IEnderecoController {
	private TextField tfEnderecosCep;
	private TextField tfEnderecosLoug;
	private TextField tfEnderecosNum;
	private TextField tfEnderecosCompl;
	private TextArea taEnderecosInformacoes;
	private TextArea taEnderecos;
	private TextField tfEnderecosTitulo;
	private Usuario u;
	


	public EnderecoController(TextField tfEnderecosCep, TextField tfEnderecosLoug, TextField tfEnderecosNum,
			TextField tfEnderecosCompl, TextArea taEnderecosInformacoes, TextArea taEnderecos, Usuario u,
			TextField tfEnderecosTitulo) {
		super();
		this.tfEnderecosCep = tfEnderecosCep;
		this.tfEnderecosLoug = tfEnderecosLoug;
		this.tfEnderecosNum = tfEnderecosNum;
		this.tfEnderecosCompl = tfEnderecosCompl;
		this.taEnderecosInformacoes = taEnderecosInformacoes;
		this.taEnderecos = taEnderecos;
		this.u = u;
		this.tfEnderecosTitulo = tfEnderecosTitulo;
	}

	@Override
	public void insereEndereco(Endereco e) throws ClassNotFoundException, SQLException {
		EnderecoDao eDao = new EnderecoDao();
		eDao.insereEndereco(e, u);
		limpaCampos();
		listaEndereco();
	}

	@Override
	public void atualizaEndereco(Endereco e, int numAntigo, String cepAntigo) throws ClassNotFoundException, SQLException {
		EnderecoDao eDao = new EnderecoDao();
		eDao.atualizaEndereco(e, u, numAntigo, cepAntigo);
		limpaCampos();
		listaEndereco();
	}

	@Override
	public void excluiEndereco(Endereco e) throws ClassNotFoundException, SQLException {
		EnderecoDao eDao = new EnderecoDao();
		eDao.excluiEndereco(e, u);
		limpaCampos();
		listaEndereco();
	}

	@Override
	public void buscaEndereco(Endereco e) throws ClassNotFoundException, SQLException {
		EnderecoDao eDao = new EnderecoDao();
		e = eDao.buscaEndereco(e, u);
		
		tfEnderecosCep.setText(e.getCep());
		tfEnderecosCompl.setText(e.getComplemento());
		tfEnderecosLoug.setText(e.getLogradouro());
		tfEnderecosTitulo.setText(e.getTitulo());
		taEnderecosInformacoes.setText(e.getInformacoes());
		tfEnderecosNum.setText(String.valueOf(e.getNumero()));
	}
	

	@Override
	public void listaEndereco() throws ClassNotFoundException, SQLException {
		limpaCampos();
		taEnderecos.setText("");

		EnderecoDao eDao = new EnderecoDao();
		List<Endereco> listaEndereco = eDao.listaEndereco(u);
		StringBuffer buffer = new StringBuffer();
		buffer.append("Titulo\t\tCep\t\tLogradouro\t\tNumero\n");
		for (Endereco e : listaEndereco) {
			buffer.append(
					e.getTitulo() + "\t\t" + e.getCep() + "\t\t" + e.getLogradouro() + "\t\t" + e.getNumero() + "\n");
		}
		taEnderecos.setText(buffer.toString());
	}

	private void limpaCampos() {
		tfEnderecosCep.setText("");
		tfEnderecosCompl.setText("");
		tfEnderecosLoug.setText("");
		tfEnderecosNum.setText("");
		tfEnderecosTitulo.setText("");
		taEnderecosInformacoes.setText("");
	}

	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException {
		if (tfEnderecosCep.getText().isEmpty() || tfEnderecosLoug.getText().isEmpty()
				|| tfEnderecosNum.getText().isEmpty() || tfEnderecosTitulo.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique se nome, titulo, numero e cep estão vazio");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		}
		// Verifica se cep tem 8 digitos
		int tamanhoCep = tfEnderecosCep.getText().length();
		if (tamanhoCep != 8) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Verifique o numero de digitos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cep Invalido");
			alertaCep.show();
			return false;
		}

		// Verifica se Cep so possui valores numericos
		if (!tfEnderecosCep.getText().matches("\\d*")) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, Insira apenas valores númericos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cep Inválido");
			alertaCep.show();
			return false;
		}

		return true;
	}

}
