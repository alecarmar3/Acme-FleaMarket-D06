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
	<jstl:if test="${!finalMode}">
	<acme:form-textbox code="supplier.acme-item.label.ticker" path="ticker"/>
	<jstl:if test="${command == 'show'}">
	<acme:form-moment code="supplier.acme-item.label.creation-date" path="creationDate" readonly="true"/>
	<acme:form-moment code="supplier.acme-item.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="supplier.acme-item.label.title" path="title"/>
	<acme:form-textbox code="supplier.acme-item.label.category" path="category"/>
	<acme:form-textarea code="supplier.acme-item.label.description" path="description"/>
	<acme:form-money code="supplier.acme-item.label.price" path="price"/>
	<acme:form-url code="supplier.acme-item.label.photo" path="photo"/>
	<acme:form-textbox code="supplier.acme-item.label.additional-information" path="additionalInformation"/>
	<jstl:if test="${command == 'show' or command == 'update-final-mode'}">
	<acme:form-textbox code="supplier.acme-item.label.final-mode" path="finalMode" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item.label.is-new" path="isNew" readonly="true"/>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${finalMode}">
	<acme:form-textbox code="supplier.acme-item.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="supplier.acme-item.label.creation-date" path="creationDate" readonly="true"/>
	<acme:form-moment code="supplier.acme-item.label.update-date" path="updateDate" readonly="true"/>	
	<acme:form-textbox code="supplier.acme-item.label.title" path="title" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item.label.category" path="category" readonly="true"/>
	<acme:form-textarea code="supplier.acme-item.label.description" path="description" readonly="true"/>
	<acme:form-money code="supplier.acme-item.label.price" path="price" readonly="true"/>
	<acme:form-url code="supplier.acme-item.label.photo" path="photo" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item.label.additional-information" path="additionalInformation" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item.label.final-mode" path="finalMode" readonly="true"/>
	<acme:form-textbox code="supplier.acme-item.label.is-new" path="isNew" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${!finalMode}">
	<acme:form-submit test="${command == 'show'}" code="supplier.acme-item.specification-sheet.button.create" method="get" action="/supplier/specification-sheet/create?AcmeItemId=${id}"/>
  	<acme:form-submit test="${command == 'show'}" code="supplier.acme-item.button.saveInFinalMode" action="/supplier/acme-item/update-final-mode"/>
	<acme:form-submit test="${command == 'show'}" code="supplier.acme-item.button.update" action="/supplier/acme-item/update"/>
	<acme:form-submit test="${command == 'update'}" code="supplier.acme-item.button.update" action="/supplier/acme-item/update"/>
	<acme:form-submit test="${command == 'update-final-mode'}" code="supplier.acme-item.button.update" action="/supplier/acme-item/update"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="supplier.acme-item.button.create" action="/supplier/acme-item/create"/>
	<jstl:if test="${isNotRequested}">
	<acme:form-submit test="${command == 'show'}" code="supplier.acme-item.button.delete" action="/supplier/acme-item/delete"/>
	</jstl:if>
	<acme:form-submit test="${command == 'show'}" code="supplier.acme-item.specification-sheets" method="get" action="/supplier/specification-sheet/list?AcmeItemId=${AcmeItemId}"/>
	<acme:form-submit test="${command == 'delete'}" code="supplier.acme-item.button.delete" action="/supplier/acme-item/delete"/>
  	<acme:form-return code="supplier.acme-item.button.return"/>
  	
	
</acme:form>
