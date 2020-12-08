
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Supplier;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "finalMode"), @Index(columnList = "supplier_id"), @Index(columnList = "ticker")
})
public class AcmeItem extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$", message = "{default.error.ticker-pattern}")
	@NotBlank
	private String				ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	private String				title;

	@NotBlank
	private String				category;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				price;

	@URL
	private String				photo;

	@URL
	private String				additionalInformation;

	@NotNull
	private Boolean				finalMode;

	@NotNull
	private Boolean				isNew;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Supplier			supplier;

}
