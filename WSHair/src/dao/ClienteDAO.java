package dao;

import model.Cliente;

public class ClienteDAO extends GenericDao<Cliente, Long>{

	public ClienteDAO(){
		super(Cliente.class);
	}
}
