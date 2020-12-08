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
	<acme:form-textbox code="administrator.figment.label.title" path="title"/>
	<acme:form-textbox code="administrator.figment.label.inventor-name" path="inventorName"/>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.figment.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="administrator.figment.label.text-piece" path="textPiece"/>
	<acme:form-integer code="administrator.figment.label.price-min" path="priceMin"/>
	<acme:form-integer code="administrator.figment.label.price-max" path="priceMax"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.figment.button.update" action="/administrator/figment/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.figment.button.delete" action="/administrator/figment/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.figment.button.create" action="/administrator/figment/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.figment.button.update" action="/administrator/figment/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.figment.button.delete" action="/administrator/figment/delete"/>
	
  	<acme:form-return code="administrator.figment.button.return"/>
  	
	
</acme:form>
