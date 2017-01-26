package de.starwit.innovationlab.entity;

import java.beans.Transient;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Lob
	private String description;
	
	@Size(min = 1, max = 260)
	private String notes;
	
	private Integer rating;
	
	private Set<RatingEntity> ratings;

	private Date created;
	
	@Column(name="HEADLINE", nullable = false, length=150)
	public String getHeadline() {
		return headline;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name="DESCRIPTION")
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
	
	@Transient
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED")
	public Date getCreated() {
		return created;
	}	
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@XmlTransient
	@JsonIgnore
	@OneToMany(mappedBy="idea", orphanRemoval=true)
	public Set<RatingEntity> getRatings() {
		return ratings;
	}

	public void setRatings(Set<RatingEntity> ratings) {
		this.ratings = ratings;
	}
	
    @PostLoad
    public void calculateRating() {
    	rating = getRatings().stream().mapToInt(RatingEntity::getRatingValue).sum();
    }
	
}