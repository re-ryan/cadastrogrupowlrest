package rest;

import java.util.List;

import javax.annotation.security.PermitAll;
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

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Cliente;


@Path("/")
public class RestApi {

	private Controle controle = new Controle();
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();

	private static final Logger log = Logger.getLogger(RestApi.class);
	
	@GET
	@PermitAll
	@Path("/clientes/consulta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getClientes() {
		
		log.info("Buscando Clientes...");
				
		List<Cliente> clientes = controle.consultaClientes();
					
		String json = gson.toJson(clientes);
		
		log.info("Resposta: "+ json);

		return Response.status(200).entity(json).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@PermitAll
	@Path("/clientes/cadastra")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setClientes(String body) {
		
		log.info("Inserindo Cliente...");

		String json = gson.toJson(controle.insereCliente(gson.fromJson(body, Cliente.class)));
		
		log.info("Resposta: "+ json);
		
		return Response.status(200).entity(json).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@PUT
	@PermitAll
	@Path("/clientes/atualiza")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateClientes(String body) {

		log.info("Atualizando Cliente...");

		String json = gson.toJson(controle.atualizaCliente(gson.fromJson(body, Cliente.class)));
		
		log.info("Resposta: "+ json);

		
		return Response.status(200).entity(json).header("Access-Control-Allow-Origin","*").build();
	}
	
	
	@DELETE
	@PermitAll
	@Path("/clientes/deleta/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteClientes(@PathParam("id") int id) {
		
		log.info("Deletando Cliente");

		String json = gson.toJson(controle.deletaCliente(id));
		
		log.info("Resposta: "+ json);
		
		return Response.status(200).entity(json).header("Access-Control-Allow-Origin", "*").build();
	}
	
	
}








