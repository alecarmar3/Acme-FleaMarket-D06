<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.buyer.label.email" path="email"/>
	<acme:form-textbox code="authenticated.buyer.label.phone-number" path="phoneNumber"/>
	<acme:form-textbox code="authenticated.buyer.label.delivery-address" path="deliveryAddress"/>
	<acme:form-textbox code="authenticated.buyer.label.credit-card-number" path="creditCardNumber"/>
	<acme:form-textbox code="authenticated.buyer.label.holder-name" path="holderName"/>
	<acme:form-textbox code="authenticated.buyer.label.brand" path="brand"/>
	<acme:form-textbox code="authenticated.buyer.label.expiration-date" path="expirationDate"/>
	<acme:form-textbox code="authenticated.buyer.label.cvv" path="cvv"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.buyer.button.create" action="/authenticated/buyer/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.buyer.button.update" action="/authenticated/buyer/update"/>
	<acme:form-return code="authenticated.buyer.button.return"/>
</acme:form>
