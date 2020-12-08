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
	<acme:form-textbox code="administrator.news.label.category" path="category"/>
	<acme:form-url code="administrator.news.label.header-picture" path="headerPicture"/>
	<acme:form-textbox code="administrator.news.label.title" path="title"/>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.news.label.creation-date" path="creationDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-moment code="administrator.news.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.news.label.body" path="body"/>
	<acme:form-textarea code="administrator.news.label.news-links" path="newsLinks"/>
	
	<jstl:if test="${command =='create' }">
	<acme:form-checkbox code="administrator.news.label.confirm-creation" path="accept"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.news.button.create" action="/administrator/news/create"/>
	
  	<acme:form-return code="administrator.news.button.return"/>
  	
	
</acme:form>
