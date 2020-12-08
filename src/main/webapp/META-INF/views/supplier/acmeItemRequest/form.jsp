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
	<acme:form-textbox code="supplier.acme-item-request.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="supplier.acme-item-request.label.creation-date" path="creationDate" readonly="true"/>
	<acme:form-integer code="supplier.acme-item-request.label.quantity" path="quantity" readonly="true"/>
	<acme:form-textarea code="supplier.acme-item-request.label.notes" path="notes" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item-request.label.status" path="status" readonly="true"/>
	<acme:form-textarea code="supplier.acme-item-request.label.justification"  path="justification"/>
	<acme:form-textbox code="supplier.acme-item-request.label.buyer" path="buyer.userAccount.username" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item-request.label.acme-item" path="acmeItem.title" readonly="true"/>
	
	
	<acme:form-submit code="supplier.acme-item-request.button.accept" test="${status=='PENDING'}" action="/supplier/acme-item-request/update-accept?id=${id}"/>
  	<acme:form-submit code="supplier.acme-item-request.button.reject" test="${status=='PENDING'}" action="/supplier/acme-item-request/update-reject?id=${id}"/>
  	<acme:form-return code="supplier.acme-item-request.button.return"/>
  	
	
</acme:form>
