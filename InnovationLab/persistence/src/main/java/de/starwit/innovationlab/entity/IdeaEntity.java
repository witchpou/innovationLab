package de.starwit.innovationlab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement
@Entity
@Table(name="IDEA")
public class IdeaEntity extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	//domain attributes
	
	@NotBlank
	@Size(min = 1, max = 150)
	private String headline;
	
	@NotBlank
	@Size(min = 1, max = 260)
	private String description;
	
	@Size(min = 1, max = 260)
	private String notes;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer rating;
	
	

	@Column(name="HEADLINE", nullable = false, length=150)
	public String getHeadline() {
		return headline;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name="DESCRIPTION", nullable = false, length=260)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="NOTES", length=260)
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name="RATING", nullable = false)
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}