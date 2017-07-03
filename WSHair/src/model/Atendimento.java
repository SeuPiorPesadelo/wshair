package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import com.google.gson.Gson;

//Entidade
@Entity
public class Atendimento {

	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	private long id;
	@OneToOne
	private Cliente cliente;
	@ManyToMany
	@JoinTable(name = "atendimentos_funcionarios")
	private List<Funcionario> funcionarios;
	@ManyToMany
	@JoinTable(name = "atendimentos_servicos")
	private List<Servico> servicos;
	private Boolean pago;

	public Atendimento() {
	}
	
	public Atendimento(Cliente cliente, List<Funcionario> funcionarios, List<Servico> servicos, boolean pago) {
		super();
		this.cliente = cliente;
		this.funcionarios = funcionarios;
		this.servicos = servicos;
		this.pago = pago;
	}

	public long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", cliente=" + cliente + ", funcionarios=" + funcionarios + ", servicos="
				+ servicos + ", pago=" + pago + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((pago == null) ? 0 : pago.hashCode());
		result = prime * result + ((servicos == null) ? 0 : servicos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (funcionarios == null) {
			if (other.funcionarios != null)
				return false;
		} else if (!funcionarios.equals(other.funcionarios))
			return false;
		if (id != other.id)
			return false;
		if (pago == null) {
			if (other.pago != null)
				return false;
		} else if (!pago.equals(other.pago))
			return false;
		if (servicos == null) {
			if (other.servicos != null)
				return false;
		} else if (!servicos.equals(other.servicos))
			return false;
		return true;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	
	public String toJson(){
		return new Gson().toJson(this);
	}
}
