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
	<acme:form-textbox code="anonymous.advertisement.label.title" path="title"/>
	<acme:form-url code="anonymous.advertisement.label.picture" path="picture"/>
	<acme:form-moment code="anonymous.advertisement.label.creation-date" path="creationDate"/>
	<acme:form-moment code="anonymous.advertisement.label.displayed-until" path="displayedUntil"/>
	<acme:form-textarea code="anonymous.advertisement.label.text-piece" path="textPiece"/>
	<acme:form-double code="anonymous.advertisement.label.small-volume-discount" path="smallVolumeDiscount"/>
	<acme:form-double code="anonymous.advertisement.label.average-volume-discount" path="averageVolumeDiscount"/>
	<acme:form-double code="anonymous.advertisement.label.large-volume-discount" path="largeVolumeDiscount"/>
	<acme:form-integer code="anonymous.advertisement.label.small-interval" path="smallInterval"/>
	<acme:form-integer code="anonymous.advertisement.label.average-interval" path="averageInterval"/>
	<acme:form-integer code="anonymous.advertisement.label.large-interval" path="largeInterval"/>
	
  	<acme:form-return code="anonymous.advertisement.button.return"/>
  	
	
</acme:form>
