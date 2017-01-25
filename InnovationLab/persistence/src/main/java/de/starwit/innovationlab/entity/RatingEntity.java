package de.starwit.innovationlab.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="RATING")
public class RatingEntity extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer rating;

}
