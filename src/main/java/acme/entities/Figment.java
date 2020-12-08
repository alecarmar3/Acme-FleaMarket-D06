
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Figment extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	private String				inventorName;

	@NotBlank
	private String				textPiece;

	@NotNull
	@Valid
	private Money				priceMin;

	@NotNull
	@Valid
	private Money				priceMax;
}
