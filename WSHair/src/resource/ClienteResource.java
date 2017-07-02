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
import dao.ClienteDAO;
import model.Cliente;

@Path("cliente")
public class ClienteResource {

	private static ClienteDAO clienteDao = new ClienteDAO();
	private static Gson gson = new Gson();

	@GET
	@Path("clientes")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response test() {
		List<Cliente> clientes = clienteDao.getList();
		return Response.ok(clientes).build();
	}

	@Path("clientes/delete/{id}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id) {
		clienteDao.remover(id);
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adiciona(String conteudo) {
		Cliente s = gson.fromJson(conteudo, Cliente.class);
		clienteDao.salvar(s);
		return Response.status(200).build();
	}

	@GET
	@Path("cliente/{id}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getUmServico(@PathParam("id") long id) {
		Cliente s = clienteDao.encontrar(id);
		return Response.ok(s).build();
	}

	@Path("{id}")
	@PUT
	public Response alteraProduto(String conteudo) {
		Cliente s1 = gson.fromJson(conteudo, Cliente.class);
		Cliente s = clienteDao.encontrar(s1.getId());
		s.setNome(s1.getNome());
		s.setTelefone(s1.getTelefone());
		clienteDao.atualizar(s);
		return Response.ok(s).build();
	}
}
