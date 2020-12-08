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
	<acme:form-textbox code="authenticated.acme-item.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.acme-item.label.creation-date" path="creationDate"/>
	<acme:form-moment code="authenticated.acme-item.label.update-date" path="updateDate"/>
	<acme:form-textbox code="authenticated.acme-item.label.title" path="title"/>
	<acme:form-textbox code="authenticated.acme-item.label.category" path="category"/>
	<acme:form-textarea code="authenticated.acme-item.label.description" path="description"/>
	<acme:form-money code="authenticated.acme-item.label.price" path="price"/>
	<acme:form-url code="authenticated.acme-item.label.photo" path="photo"/>
	<acme:form-textbox code="authenticated.acme-item.label.additional-information" path="additionalInformation"/>
	<acme:form-textbox code="authenticated.acme-item.label.is-new" path="isNew"/>
	
  	<acme:form-return code="authenticated.acme-item.button.return"/>
  	<acme:form-submit test="${command == 'show'}" code="authenticated.acme-item.specification-sheets" method="get" action="/authenticated/specification-sheet/list?AcmeItemId=${AcmeItemId}"/>
  	<acme:form-submit test="${isBuyer}" code="authenticated.acme-item.request" method="get" action="/buyer/acme-item-request/create?AcmeItemId=${id}"/>
  	
	
</acme:form>
