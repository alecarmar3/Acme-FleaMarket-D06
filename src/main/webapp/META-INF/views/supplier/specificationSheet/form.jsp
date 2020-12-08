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
	<acme:form-textbox code="supplier.specification-sheet.label.indexer" path="indexer"/>
	<acme:form-textbox code="supplier.specification-sheet.label.title" path="sheetTitle"/>
	<acme:form-textarea code="supplier.specification-sheet.label.description" path="sheetDescription"/>
	<acme:form-url code="supplier.specification-sheet.label.photo" path="photo"/>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="supplier.specification-sheet.label.acme-item" path="acmeItem.title" readonly="true"/>
	</jstl:if>
	
  	<acme:form-return code="supplier.specification-sheet.button.return"/>
    <acme:form-submit code="supplier.specification-sheet.form.button.create" test="${command=='create'}" action="/supplier/specification-sheet/create?AcmeItemId=${acmeItem.id}"/>
  	
	
</acme:form>
