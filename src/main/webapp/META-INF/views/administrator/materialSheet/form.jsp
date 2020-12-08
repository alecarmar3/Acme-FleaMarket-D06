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
	<acme:form-textbox code="administrator.material-sheet.label.title" path="title"/>
	<acme:form-textarea code="administrator.material-sheet.label.description" path="description"/>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.material-sheet.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="administrator.material-sheet.label.provider-name" path="providerName"/>
	<acme:form-url code="administrator.material-sheet.label.home-page" path="homePage"/>
	<acme:form-integer code="administrator.material-sheet.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.material-sheet.button.update" action="/administrator/material-sheet/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.material-sheet.button.delete" action="/administrator/material-sheet/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.material-sheet.button.create" action="/administrator/material-sheet/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.material-sheet.button.update" action="/administrator/material-sheet/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.material-sheet.button.delete" action="/administrator/material-sheet/delete"/>
	
  	<acme:form-return code="administrator.material-sheet.button.return"/>
  	
	
</acme:form>
