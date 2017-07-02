package dao;

import model.Atendimento;

public class AtendimentoDAO extends GenericDao<Atendimento, Long> {
	
	public AtendimentoDAO(){
		super(Atendimento.class);
	}
}
