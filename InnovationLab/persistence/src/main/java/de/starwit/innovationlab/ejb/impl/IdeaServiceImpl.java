package de.starwit.innovationlab.ejb.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import de.starwit.innovationlab.ejb.IdeaService;
import de.starwit.innovationlab.entity.IdeaEntity;
import de.starwit.innovationlab.entity.RatingEntity;

@Stateless(name = "IdeaService")
public class IdeaServiceImpl extends AbstractServiceImpl<IdeaEntity> implements IdeaService {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(IdeaServiceImpl.class);

	@Override
	public void rate(RatingEntity entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
	}

	@Override
	public byte[] getImageByIdeaId(Long id) {
		Query q = getEntityManager().createQuery("select idea.image from IdeaEntity idea where idea.id = :id");
		q.setParameter("id", id);
		try {
			return (byte[]) q.getSingleResult();
		} catch (NoResultException e) {
			LOG.info("No result of type IdeaEntity found for id " + id);
			return new byte[] {};
		}
	}

	@Override
	public void setImageByIdeaId(Long id, InputStream imageStream) {

		if (imageStream == null || id == null) {
			return;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] byteChunk = new byte[1024]; 
		int n;

		try {
			while ((n = imageStream.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}
			byte[] ba = baos.toByteArray();
			byte[] encoded = Base64.getEncoder().encode(ba);
			Query q = getEntityManager().createQuery("UPDATE IdeaEntity idea SET idea.image = :image WHERE idea.id = :id ");
			q.setParameter("image", encoded);
			q.setParameter("id", id);
			q.executeUpdate();
			getEntityManager().flush();
		
		/*In this special case, not any Exception thrown here should got to the REST-layer. 
		 * So, all kinds of exceptions are catched. Don't copy this code without a good reason.*/
		} catch (Exception e) {
			LOG.error("Saving the image (IdeaEntity.image) for IdeaEntity.id = " + id + "failed.", e);
		} finally {
			try {
				baos.close();
				imageStream.close();
			} catch (IOException e) {
				LOG.error("Saving the image (IdeaEntity.image) for IdeaEntity.id = " + id + "failed.", e);
			}
		}

	}

}
