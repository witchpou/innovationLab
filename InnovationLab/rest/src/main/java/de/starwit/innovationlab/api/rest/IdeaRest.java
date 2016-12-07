package de.starwit.innovationlab.api.rest;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.starwit.innovationlab.api.rest.response.EntityResponse;
import de.starwit.innovationlab.ejb.IdeaService;
import de.starwit.innovationlab.entity.IdeaEntity;

@Path("/idea")
@Consumes("application/json")
@Produces("application/json")
public class IdeaRest extends AbstractRest<IdeaEntity> {
	
	@Inject
	protected IdeaService service;
	
	@Override
	protected IdeaService getService() {
		return service;
	}
	
	//Create
	@Path("/")
	@PUT
	public EntityResponse<IdeaEntity> create(IdeaEntity entity) {
		return super.createGeneric(entity);
	}

	//Update
	@Path("/")
	@POST
	public EntityResponse<IdeaEntity> update(IdeaEntity entity) {
		return super.updateGeneric(entity);
	}
}