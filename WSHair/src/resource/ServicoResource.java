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

import dao.ServicoDAO;
import model.Servico;

@Path("servico")
public class ServicoResource {

	private static ServicoDAO servicoDao = new ServicoDAO();
	private static Gson gson = new Gson();

	@GET
	@Path("servicos")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response test() {
		List<Servico> serv = servicoDao.getList();
		System.out.println("GET Objectos from Jax-RS");
		System.out.println("");
		return Response.ok(serv).build();
	}

	@Path("servicos/delete/{id}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id) {
		System.out.println("DELETE Object ID: " + id + " Jax-RS ");
		System.out.println("");
		servicoDao.remover(id);
		return Response.ok().build();
	}

	@POST // consume o objeto enviado pro back-end
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adiciona(String conteudo) {
		Servico s = gson.fromJson(conteudo, Servico.class);
		servicoDao.salvar(s);
		System.out.println("POST Object Jax-RS");
		System.out.println("");
		return Response.status(200).build();
	}

	@GET
	@Path("servicos/{id}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getUmServico(@PathParam("id") long id) {
		Servico s = servicoDao.encontrar(id);
		return Response.ok(s).build();
	}

	@Path("{id}")
	@PUT
	public Response alteraProduto(String conteudo) {
		System.out.println("Atualizou " + conteudo);
		Servico s1 = gson.fromJson(conteudo, Servico.class);
		Servico s = servicoDao.encontrar(s1.getId());
		s.setNome(s1.getNome());
		s.setPreco(s1.getPreco());
		servicoDao.atualizar(s);
		return Response.ok(s).build();
	}

}
