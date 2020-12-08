
<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="buyer.acme-item-request.label.ticker" path="ticker" width="25%"/>
	<acme:list-column code="buyer.acme-item-request.label.status" path="status" width="25%"/>
	<acme:list-column code="buyer.acme-item-request.label.acme-item" path="acmeItem.title" width="25%"/>
</acme:list>