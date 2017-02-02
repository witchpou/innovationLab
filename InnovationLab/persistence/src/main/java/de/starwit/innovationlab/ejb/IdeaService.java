package de.starwit.innovationlab.ejb;

import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.Local;
import de.starwit.innovationlab.entity.IdeaEntity;
import de.starwit.innovationlab.entity.RatingEntity;

@Local
public interface IdeaService extends Serializable, AbstractService<IdeaEntity> {
	
	void rate(RatingEntity entity);

	/**
	 * Gets the image from database.
	 * @param id
	 * @return
	 */
	byte[] getImageByIdeaId(Long id);
	
	/**
	 * Writes an image to the database.
	 * @param id
	 * @param imageStream
	 */
	void setImageByIdeaId(Long id, InputStream imageStream);
}
    
