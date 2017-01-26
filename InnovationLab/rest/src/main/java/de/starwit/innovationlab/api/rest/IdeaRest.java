package de.starwit.innovationlab.api.rest;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.starwit.innovationlab.api.rest.response.EntityResponse;
import de.starwit.innovationlab.api.rest.response.ResponseCode;
import de.starwit.innovationlab.api.rest.response.ResponseMetadata;
import de.starwit.innovationlab.ejb.IdeaService;
import de.starwit.innovationlab.entity.IdeaEntity;
import de.starwit.innovationlab.entity.RatingEntity;

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
	
	//Update
	@Path("/rate/")
	@POST
	public EntityResponse<RatingEntity> rate(RatingEntity rating) {
		EntityResponse<RatingEntity> response = new EntityResponse<RatingEntity>();
		ResponseMetadata responseMetadata = new ResponseMetadata();
		
		try {
			getService().rate(rating);
			responseMetadata.setResponseCode(ResponseCode.OK);
			responseMetadata.setMessage("Das Rating wurde durchgeführt.");
		} catch (Exception e) {
			responseMetadata.setResponseCode(ResponseCode.ERROR);
			responseMetadata.setMessage("Das Rating konnte nicht durchgeführt werden: " + e.getMessage());
		}

		response.setMetadata(responseMetadata);
		return response;
	}
}