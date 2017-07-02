package dao;

import model.Servico;

public class ServicoDAO extends GenericDao<Servico, Long>{

	public ServicoDAO() {
		super(Servico.class);
	}
}
