package service;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import domain.Food;
import domain.FoodList;
import domain.FoodManager;

/**
 * This is the jax-rs service
 * @author ppang
 *
 */
@Path("/foods")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Stateless
public class FoodRestService {
	@Context
	private UriInfo uriInfo;
	
	@POST
	public Response create(Food food) {
		if (food == null) {
			throw new BadRequestException();
		}
		FoodManager.add(food);
		URI uri = uriInfo.getAbsolutePathBuilder().path(food.getName()).build();
		return Response.created(uri).build();
	}
	
	@PUT
	public Response update(Food food) {
		Food original = FoodManager.update(food);
		if (original==null) {
			throw new NotFoundException();
		}
		return Response.ok(food).build();
	}
	
	@GET
	@Path("{name}")
	public Response get(@PathParam("name") String name) {
		Food food = FoodManager.find(name);
		if (food == null) {
			throw new NotFoundException();
		}
		return Response.ok(food).build();
	}
	
	@GET
	public Response getAll() {
		FoodList foodList = FoodManager.getFoods();
		GenericEntity<List<Food>> list = new GenericEntity<List<Food>>(foodList) {};
		return Response.ok(list).build();
	}
	
	@DELETE
	@Path("{name}")
	public Response delete(@PathParam("name") String name) {
		FoodManager.delete(name);
		return Response.noContent().build();
	}
}
