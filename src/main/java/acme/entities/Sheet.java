
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class Sheet extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	private String				providerName;

	@NotBlank
	@URL
	private String				homePage;

	@NotNull
	@Min(0)
	@Max(5)
	private Integer				stars;

}
