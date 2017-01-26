package de.starwit.innovationlab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="RATING")
public class RatingEntity extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer ratingValue;
	
	private IdeaEntity idea;

	@Column(name="RATINGVALUE", nullable = false)
	public Integer getRatingValue() {
		return ratingValue;
	}
	
	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDEA_ID")
	public IdeaEntity getIdea() {
		return idea;
	}

	public void setIdea(IdeaEntity idea) {
		this.idea = idea;
	}

}
