package de.starwit.innovationlab.ejb.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.starwit.innovationlab.ejb.IdeaService;
import de.starwit.innovationlab.entity.IdeaEntity;
import de.starwit.innovationlab.entity.RatingEntity;

@Stateless(name = "IdeaService")
public class IdeaServiceImpl extends AbstractServiceImpl<IdeaEntity> implements IdeaService {
	
	private static final long serialVersionUID = 1L;
	
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
		} catch(NoResultException e) {
			return new byte[]{};
		}
	}

	@Override
	public void setImageByIdeaId(Long id, InputStream imageStream) {
		
		if(imageStream == null || id == null) {
			return;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		  byte[] byteChunk = new byte[1024]; // Or whatever size you want to read in at a time.
		  int n;

		  try {
			while ( (n = imageStream.read(byteChunk)) > 0 ) {
			    baos.write(byteChunk, 0, n);
			  }
			
			byte[] ba = baos.toByteArray();
			byte[] encoded = Base64.getEncoder().encode(ba);
			Query q = getEntityManager().createQuery("UPDATE IdeaEntity idea SET idea.image = :image WHERE idea.id = :id ");
			q.setParameter("image", encoded);
			q.setParameter("id", id);
			q.executeUpdate();
			getEntityManager().flush();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			try {
				baos.close();
				imageStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}



    
