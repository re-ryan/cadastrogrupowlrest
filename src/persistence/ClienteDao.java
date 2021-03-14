package persistence;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.Cliente;

public class ClienteDao extends Dao{

	private static final Logger log = Logger.getLogger(ClienteDao.class);
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


	public List<Cliente> consultaCliente(){
		
		List<Cliente> lst = new ArrayList<Cliente>();
		Cliente cliente = null;

		String sql = "CALL PR_SELECT_CLIENTE()";

		try {
			open_mysql();
			cs = con.prepareCall(sql);
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				cliente = new Cliente();
				
				cliente.setCodCliente(rs.getInt("COD_CLIENTE"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCpf(rs.getString("NUM_CPF"));
				cliente.setDatNascimento(rs.getDate("DAT_NASCIMENTO"));
				cliente.setSenha(rs.getString("SENHA"));
				
				lst.add(cliente);
			}


		} catch (Exception e) {
			log.info("Erro ao buscar clientes: "+ e.getMessage());
		}
		finally {
			close();
		}

		return lst;

	}

	public Integer insereCliente(Cliente cliente) {

		Integer resultado = null;

		cliente.setSenha(criptografaSenha(cliente.getCpf().substring(0,3), DATE_FORMAT.format(cliente.getDatNascimento()).split("-")[1]));

		String sql = "CALL PR_INSERE_CLIENTE(?,?,?,?,?,?)";

		try {
			open_mysql();
			
			cs = con.prepareCall(sql);
			cs.setString(1, cliente.getNome());
			cs.setString(2, cliente.getCpf());
			cs.setString(3, DATE_FORMAT.format(cliente.getDatNascimento()));
			cs.setString(4, cliente.getSenha());

			cs.execute();

			log.info("Código Retorno: "+ cs.getInt(5));
			log.info("Mensagem Retorno: "+ cs.getString(6));

			resultado  = cs.getInt(5);

		} catch (Exception e) {
			resultado = 0;

			log.info("Erro ao inserir cliente: "+ e.getMessage());
		}
		finally {
			close();
		}

		return resultado;

	}

	public Integer atualizaCliente(Cliente cliente) {

		Integer resultado = null;

		cliente.setSenha(criptografaSenha(cliente.getCpf().substring(0,3), DATE_FORMAT.format(cliente.getDatNascimento()).split("-")[1]));

		String sql = "CALL PR_ATUALIZA_CLIENTE(?,?,?,?,?,?,?)";

		try {
			open_mysql();
			cs = con.prepareCall(sql);
			cs.setInt(1, cliente.getCodCliente());
			cs.setString(2, cliente.getNome());
			cs.setString(3, cliente.getCpf());
			cs.setString(4, DATE_FORMAT.format(cliente.getDatNascimento()));
			cs.setString(5, cliente.getSenha());

			cs.execute();

			log.info("Código Retorno: "+ cs.getInt(6));
			log.info("Mensagem Retorno: "+ cs.getString(7));

			resultado  = cs.getInt(6);

		} catch (Exception e) {
			resultado = 0;

			log.info("Erro ao atualizar cliente: "+ e.getMessage());
		}
		finally {
			close();
		}

		return resultado;

	}

	public Integer deletaCliente(int id) {

		Integer resultado = null;

		String sql = "CALL PR_DELETA_CLIENTE(?,?,?)";

		try {
			open_mysql();

			cs = con.prepareCall(sql);
			cs.setInt(1, id);

			cs.execute();

			log.info("Código Retorno: "+ cs.getInt(2));
			log.info("Mensagem Retorno: "+ cs.getString(3));

			resultado  = cs.getInt(2);

		} catch (Exception e) {
			resultado = 0;
			log.info("Erro ao deletar cliente: "+ e.getMessage());
		}
		finally {
			close();
		}

		return resultado;

	}


	private String criptografaSenha(String cpf, String dat) {

		String senha = cpf+dat;
		String sha1 = "";

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(senha.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			log.info("Erro ao gerar sha1: "+ e.getMessage());
			e.printStackTrace();
		}

		return sha1;

	}



	public static void main(String[] args) {

		ClienteDao clienteDao = new ClienteDao();

		Cliente cliente = new Cliente();
		//cliente.setCodCliente(24);
		cliente.setNome("Ryan");
		cliente.setCpf("14370809797");
		try {
			cliente.setDatNascimento(DATE_FORMAT.parse("1994-08-21"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clienteDao.insereCliente(cliente);
		//clienteDao.atualizaCliente(cliente);
		//clienteDao.deletaCliente(34);


	}



}
