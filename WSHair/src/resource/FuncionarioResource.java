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
import dao.FuncionarioDAO;
import model.Funcionario;

@Path("funcionario")
public class FuncionarioResource {

	private static FuncionarioDAO funcionarioDao = new FuncionarioDAO();
	private static Gson gson = new Gson();
	
	@GET
	@Path("funcionarios")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response test() {
		List<Funcionario> serv = funcionarioDao.getList();
		return Response.ok(serv).build();
	}

	@Path("funcionarios/delete/{id}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id) {
		funcionarioDao.remover(id);
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adiciona(String conteudo) {
		Funcionario s = gson.fromJson(conteudo, Funcionario.class);
		funcionarioDao.salvar(s);
		return Response.status(200).build();
	}

	@GET
	@Path("funcionarios/{id}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getUmFuncionario(@PathParam("id") long id) {
		Funcionario s = funcionarioDao.encontrar(id);
		System.out.println(s.getServicos());
		return Response.ok(s).build();
	}

	@Path("{id}")
	@PUT
	public Response alteraProduto(String conteudo) {
		Funcionario s1 = gson.fromJson(conteudo, Funcionario.class);
		Funcionario s = funcionarioDao.encontrar(s1.getId());
		s.setNome(s1.getNome());
		s.setServicos(s1.getServicos());
		funcionarioDao.atualizar(s);
		return Response.ok(s).build();
	}

	
}
