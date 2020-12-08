/*
 * Provider.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "user_account_id")
})
public class Buyer extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Email
	private String				email;

	@Pattern(regexp = "([+(\\d]{1})(([\\d+() -.]){5,16})([+(\\d]{1})", message = "{default.buyer.error.phone-number-pattern}")
	@NotBlank
	private String				phoneNumber;

	@NotBlank
	private String				deliveryAddress;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	private String				holderName;

	@NotBlank
	private String				brand;

	@Pattern(regexp = "^\\d{2}\\/\\d{2}$", message = "{default.buyer.error.expiration-date-pattern}")
	@NotBlank
	private String				expirationDate;

	@Pattern(regexp = "^[0-9]{3}$", message = "{default.buyer.error.cvv-pattern}")
	@NotBlank
	private String				cvv;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
