package entity;

import java.util.Date;

public class Cliente {
	
	private int codCliente;
	private String nome;
	private String cpf;
	private Date datNascimento;
	private String senha;
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDatNascimento() {
		return datNascimento;
	}
	public void setDatNascimento(Date datNascimento) {
		this.datNascimento = datNascimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", nome=" + nome + ", cpf=" + cpf + ", datNascimento="
				+ datNascimento + "]";
	}
	
}