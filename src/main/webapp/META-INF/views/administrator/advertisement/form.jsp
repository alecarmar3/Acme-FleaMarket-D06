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
	<acme:form-textbox code="administrator.advertisement.label.title" path="title"/>
	<acme:form-url code="administrator.advertisement.label.picture" path="picture"/>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.advertisement.label.creation-date" path="creationDate" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command !='create' }">
	<acme:form-moment code="administrator.advertisement.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-moment code="administrator.advertisement.label.displayed-until" path="displayedUntil"/>
	<acme:form-textarea code="administrator.advertisement.label.text-piece" path="textPiece"/>
	<acme:form-double code="administrator.advertisement.label.small-volume-discount" path="smallVolumeDiscount"/>
	<acme:form-double code="administrator.advertisement.label.average-volume-discount" path="averageVolumeDiscount"/>
	<acme:form-double code="administrator.advertisement.label.large-volume-discount" path="largeVolumeDiscount"/>
	<acme:form-integer code="administrator.advertisement.label.small-interval" path="smallInterval"/>
	<acme:form-integer code="administrator.advertisement.label.average-interval" path="averageInterval"/>
	<acme:form-integer code="administrator.advertisement.label.large-interval" path="largeInterval"/>
	
	<jstl:if test="${isDisplayed}">
	<acme:form-submit test="${command == 'show'}" code="administrator.advertisement.button.update" action="/administrator/advertisement/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.advertisement.button.delete" action="/administrator/advertisement/delete"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="administrator.advertisement.button.create" action="/administrator/advertisement/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.advertisement.button.update" action="/administrator/advertisement/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.advertisement.button.delete" action="/administrator/advertisement/delete"/>
	
  	<acme:form-return code="administrator.advertisement.button.return"/>
  	
	
</acme:form>
