package de.starwit.innovationlab.ejb;

import java.io.InputStream;

import javax.ejb.Local;

import de.starwit.innovationlab.entity.AbstractEntity;
import de.starwit.innovationlab.exception.ImportException;

@Local
public interface DataImportExportService {
	
	void importAll();

	void importEntityData(Class<? extends AbstractEntity> entityClass,
			InputStream in) throws ImportException;
}
