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
	<acme:form-textbox code="authenticated.supplier.label.company" path="company"/>
	<acme:form-url code="authenticated.supplier.label.home-page" path="homePage"/>
	<acme:form-textbox code="authenticated.supplier.label.acme-item-category" path="acmeItemCategory"/>
	<acme:form-textarea code="authenticated.supplier.label.description" path="description"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.supplier.button.create" action="/authenticated/supplier/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.supplier.button.update" action="/authenticated/supplier/update"/>
	<acme:form-return code="authenticated.supplier.button.return"/>
</acme:form>
