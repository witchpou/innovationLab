package de.starwit.innovationlab.ejb;

import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.Local;
import de.starwit.innovationlab.entity.IdeaEntity;
import de.starwit.innovationlab.entity.RatingEntity;

@Local
public interface IdeaService extends Serializable, AbstractService<IdeaEntity> {
	
	void rate(RatingEntity entity);

	byte[] getImageByIdeaId(Long id);
	
	void setImageByIdeaId(Long id, InputStream imageStream);
}
    
