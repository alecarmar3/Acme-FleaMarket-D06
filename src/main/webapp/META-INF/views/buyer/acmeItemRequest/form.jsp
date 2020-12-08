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
	<acme:form-textbox code="buyer.acme-item-request.label.ticker" path="ticker"/>
	<jstl:if test="${command!='create'}">
	<acme:form-moment code="buyer.acme-item-request.label.creation-date" readonly="true" path="creationDate"/>
	<acme:form-textbox code="buyer.acme-item-request.label.status" readonly="true" path="status"/>
	<acme:form-textarea code="buyer.acme-item-request.label.justification" readonly="true" path="justification"/>
	</jstl:if>
	<acme:form-integer code="buyer.acme-item-request.label.quantity" path="quantity"/>
	<acme:form-textarea code="buyer.acme-item-request.label.notes" path="notes"/>
	<jstl:if test="${command!='create'}">
	<acme:form-textbox code="buyer.acme-item-request.label.buyer" readonly="true" path="buyer.userAccount.username"/>
	<acme:form-textbox code="buyer.acme-item-request.label.acme-item" readonly="true" path="acmeItem.title"/>
	</jstl:if>
	
  	<acme:form-return code="buyer.acme-item-request.button.return"/>
  	<acme:form-submit code="buyer.acme-item-request.form.button.create" test="${command=='create'}" action="/buyer/acme-item-request/create?AcmeItemId=${acmeItem.id}"/>
</acme:form>
