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
	<acme:form-textbox code="administrator.suggestion.label.title" path="title"/>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.suggestion.label.creation-date" path="creationDate" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.suggestion.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-textarea code="administrator.suggestion.label.text-piece" path="textPiece"/>
	<acme:form-textbox code="administrator.suggestion.label.email" path="email"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.suggestion.button.update" action="/administrator/suggestion/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.suggestion.button.delete" action="/administrator/suggestion/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.suggestion.button.create" action="/administrator/suggestion/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.suggestion.button.update" action="/administrator/suggestion/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.suggestion.button.delete" action="/administrator/suggestion/delete"/>
	
  	<acme:form-return code="administrator.suggestion.button.return"/>
  	
	
</acme:form>
