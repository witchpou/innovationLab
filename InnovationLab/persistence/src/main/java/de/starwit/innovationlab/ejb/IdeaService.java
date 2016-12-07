package de.starwit.innovationlab.ejb;

import java.io.Serializable;
import javax.ejb.Local;
import de.starwit.innovationlab.entity.IdeaEntity;

@Local
public interface IdeaService extends Serializable, AbstractService<IdeaEntity> {

}


    
