package de.starwit.innovationlab.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
	private String description;
	
	@Size(min = 1, max = 260)
	private String notes;
	
	private Set<RatingEntity> ratings;

	private Date created;
	
	private Long rating;
	
	private byte[] image;
	
	@Column(name="HEADLINE", nullable = false, length=150)
	public String getHeadline() {
		return headline;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name="DESCRIPTION")
	@Lob
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
	public Long getRating() {
		if (getRatings() == null || getRatings().size() == 0) {
			return 0L;
		}
		rating = Math.round(getRatings().stream().mapToLong(RatingEntity::getRatingValue).average().getAsDouble());
		return rating;
	}
	
	public void setRating(Integer rating) {

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
	@OneToMany(mappedBy="idea", fetch=FetchType.EAGER)
	public Set<RatingEntity> getRatings() {
		return ratings;
	}

	public void setRatings(Set<RatingEntity> ratings) {
		this.ratings = ratings;
	}
	
	@Column(name="IMAGE")
	@Lob
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}