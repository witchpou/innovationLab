package de.starwit.innovationlab.ejb.impl;


import javax.ejb.Stateless;
import de.starwit.innovationlab.ejb.IdeaService;
import de.starwit.innovationlab.entity.IdeaEntity;
import de.starwit.innovationlab.entity.RatingEntity;

@Stateless(name = "IdeaService")
public class IdeaServiceImpl extends AbstractServiceImpl<IdeaEntity> implements IdeaService {
	
	private static final long serialVersionUID = 1L;
	
	public void rate(RatingEntity entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
	}

}



    
