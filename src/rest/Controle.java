package rest;

import java.util.List;

import org.apache.log4j.Logger;

import entity.Cliente;
import persistence.ClienteDao;


public class Controle {

	private ClienteDao clienteDao = new ClienteDao();
	private static final Logger log = Logger.getLogger(Controle.class);


	public List<Cliente> consultaClientes(){

		List<Cliente> lst = clienteDao.consultaCliente();

		return lst;

	}

	public String insereCliente(Cliente cliente) {

		Integer codResultado = clienteDao.insereCliente(cliente);
		String txtResultado = null;

		if(codResultado==null)
			txtResultado = "Falha na conexão com banco de dados";
		else if(codResultado==1)
			txtResultado = "Cliente cadastrado com sucesso.";
		else if(codResultado==0)
			txtResultado = "Erro na execução da procedure.";
		else if(codResultado==1062)
			txtResultado = "Erro. Esse cpf já existe na base dados.";
		else
			txtResultado = "Erro na inserção do cliente, favor entrar em contato com os desenvolvedores.";

		return txtResultado;
	}
	
	public String atualizaCliente(Cliente cliente) {

		Integer codResultado = clienteDao.atualizaCliente(cliente);
		String txtResultado = null;

		if(codResultado==null)
			txtResultado = "Falha na conexão com banco de dados";
		else if(codResultado==1)
			txtResultado = "Cliente alterado com sucesso.";
		else if(codResultado==0)
			txtResultado = "Erro na execução da procedure.";
		else
			txtResultado = "Erro na alteração do cliente, favor entrar em contato com os desenvolvedores.";

		return txtResultado;
	}
	
	public String deletaCliente(int id) {

		Integer codResultado = clienteDao.deletaCliente(id);
		String txtResultado = null;

		if(codResultado==null)
			txtResultado = "Falha na conexão com banco de dados";
		else if(codResultado==1)
			txtResultado = "Cliente deletado com sucesso.";
		else if(codResultado==0)
			txtResultado = "Erro na execução da procedure.";
		else
			txtResultado = "Erro na deleção do cliente, favor entrar em contato com os desenvolvedores.";

		return txtResultado;
	}
}