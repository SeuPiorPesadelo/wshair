package dao;

import model.Funcionario;

public class FuncionarioDAO extends GenericDao<Funcionario, Long>{

	public FuncionarioDAO(){
		super(Funcionario.class);
	}
}
