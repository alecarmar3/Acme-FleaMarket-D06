
package acme.entities;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "acme_item_id")
})
public class SpecificationSheet extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotNull
	@Min(0)
	private Integer				indexer;

	@NotBlank
	private String				sheetTitle;

	@NotBlank
	private String				sheetDescription;

	@URL
	private String				photo;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private AcmeItem			acmeItem;

}
