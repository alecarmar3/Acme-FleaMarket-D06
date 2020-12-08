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
	<acme:form-textbox code="authenticated.specification-sheet.label.indexer" path="indexer"/>
	<acme:form-textbox code="authenticated.specification-sheet.label.title" path="sheetTitle"/>
	<acme:form-textarea code="authenticated.specification-sheet.label.description" path="sheetDescription"/>
	<acme:form-url code="authenticated.specification-sheet.label.photo" path="photo"/>
	<acme:form-textbox code="authenticated.specification-sheet.label.acme-item" path="acmeItem.title"/>
	
  	<acme:form-return code="authenticated.specification-sheet.button.return"/>  	
	
</acme:form>
