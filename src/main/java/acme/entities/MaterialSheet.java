
package acme.entities;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars")
})
public class MaterialSheet extends Sheet {

	// Serialization identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

}
