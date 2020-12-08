
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.roles.Buyer;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "ticker asc"), @Index(columnList = "buyer_id"), @Index(columnList = "acme_item_id")
})
public class AcmeItemRequest extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$", message = "{default.error.ticker-pattern}")
	@NotBlank
	private String					ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date					creationDate;

	@NotNull
	private AcmeItemRequestStatus	status;

	@NotNull
	@Min(1)
	private Integer					quantity;

	private String					notes;

	private String					justification;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Buyer					buyer;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private AcmeItem				acmeItem;

}
