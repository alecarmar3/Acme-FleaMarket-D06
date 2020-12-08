
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class News extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	private String				category;

	@NotBlank
	@URL
	private String				headerPicture;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				body;

	@URL
	private String				newsLinks;

}
