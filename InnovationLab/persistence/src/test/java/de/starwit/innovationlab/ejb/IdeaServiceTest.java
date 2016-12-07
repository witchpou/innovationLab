package de.starwit.innovationlab.ejb;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.runner.RunWith;

import de.starwit.innovationlab.entity.IdeaEntity;

@RunWith(Arquillian.class)
public class IdeaServiceTest extends AbstractServiceTest<IdeaService, IdeaEntity> {
	
	@Override
	public void setService(IdeaService service) {
		this.service = service;
	}

	@Override
	public void testCreate() {
		entity = new IdeaEntity();
		entity = getService().create(entity);
		ID = entity.getId();
		Assert.assertNotNull(entity.getId());
		Assert.fail("Not yet implemented");
		
	}

	@Override
	public void testUpdate() {
		entity = getService().findById(ID);
		entity = getService().update(entity);
		Assert.fail("Not yet implemented");
	}

}