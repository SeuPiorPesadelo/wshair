package resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dao.AtendimentoDAO;
import model.Atendimento;

@Path("atendimento")
public class AtendimentoResource {

	private static AtendimentoDAO atendimentoDao = new AtendimentoDAO();
	private static Gson gson = new Gson();

	@GET
	@Path("atendimentos")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response test() {
		List<Atendimento> serv = atendimentoDao.getList();
		return Response.ok(serv).build();
	}

	@Path("atendimentos/delete/{id}")
	@DELETE
	public Response removeAtendimento(@PathParam("id") long id) {
		atendimentoDao.remover(id);
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adiciona(String conteudo) {
		Atendimento s = gson.fromJson(conteudo, Atendimento.class);
		atendimentoDao.salvar(s);
		return Response.status(200).build();
	}

	@GET
	@Path("atendimentos/{id}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getUmAtendimento(@PathParam("id") long id) {
		Atendimento s = atendimentoDao.encontrar(id);
		System.out.println(s.getServicos());
		return Response.ok(s).build();
	}

	@Path("{id}")
	@PUT
	public Response alteraAtendimento(String conteudo) {
		Atendimento s1 = gson.fromJson(conteudo, Atendimento.class);
		Atendimento s = atendimentoDao.encontrar(s1.getId());
		s.setCliente(s1.getCliente());
		s.setFuncionarios(s1.getFuncionarios());
		s.setServicos(s1.getServicos());
		atendimentoDao.atualizar(s);
		return Response.ok(s).build();
	}
}
