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
	<acme:form-textbox code="administrator.tool-sheet.label.title" path="title"/>
	<acme:form-textarea code="administrator.tool-sheet.label.description" path="description"/>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.tool-sheet.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="administrator.tool-sheet.label.provider-name" path="providerName"/>
	<acme:form-url code="administrator.tool-sheet.label.home-page" path="homePage"/>
	<acme:form-integer code="administrator.tool-sheet.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-sheet.button.update" action="/administrator/tool-sheet/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-sheet.button.delete" action="/administrator/tool-sheet/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.tool-sheet.button.create" action="/administrator/tool-sheet/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.tool-sheet.button.update" action="/administrator/tool-sheet/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.tool-sheet.button.delete" action="/administrator/tool-sheet/delete"/>
	
  	<acme:form-return code="administrator.tool-sheet.button.return"/>
  	
	
</acme:form>
