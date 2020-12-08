<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.advertisement.list" action="/anonymous/advertisement/list"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="https://www.mpkelley.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.material-sheet.list" action="/anonymous/material-sheet/list"/>
			<acme:menu-suboption code="master.menu.anonymous.news.list" action="/anonymous/news/list"/>
			<acme:menu-suboption code="master.menu.anonymous.tool-sheet.list" action="/anonymous/tool-sheet/list"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.advertisement.list" action="/authenticated/advertisement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.figment.list" action="/authenticated/figment/list"/>
			<acme:menu-suboption code="master.menu.authenticated.acme-item.list" action="/authenticated/acme-item/list"/>
			<acme:menu-suboption code="master.menu.authenticated.material-sheet.list" action="/authenticated/material-sheet/list"/>
			<acme:menu-suboption code="master.menu.authenticated.news.list" action="/authenticated/news/list"/>
			<acme:menu-suboption code="master.menu.authenticated.suggestion.list" action="/authenticated/suggestion/list"/>
			<acme:menu-suboption code="master.menu.authenticated.tool-sheet.list" action="/authenticated/tool-sheet/list"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.supplier" access="hasRole('Supplier')">
			<acme:menu-suboption code="master.menu.supplier.acme-item.create" action="/supplier/acme-item/create"/>
			<acme:menu-suboption code="master.menu.supplier.acme-item.list-mine" action="/supplier/acme-item/list-mine"/>
			<acme:menu-suboption code="master.menu.supplier.acme-item-request.list-to-mine" action="/supplier/acme-item-request/list-to-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.buyer" access="hasRole('Buyer')">
			<acme:menu-suboption code="master.menu.buyer.acme-item-request.list-mine" action="/buyer/acme-item-request/list-mine"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.advertisement.list" action="/administrator/advertisement/list"/>
			<acme:menu-suboption code="master.menu.administrator.advertisement.create" action="/administrator/advertisement/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.figment.list" action="/administrator/figment/list"/>
			<acme:menu-suboption code="master.menu.administrator.figment.create" action="/administrator/figment/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.material-sheet.list" action="/administrator/material-sheet/list"/>
			<acme:menu-suboption code="master.menu.administrator.material-sheet.create" action="/administrator/material-sheet/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.news.list" action="/administrator/news/list"/>
			<acme:menu-suboption code="master.menu.administrator.news.create" action="/administrator/news/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.suggestion.list" action="/administrator/suggestion/list"/>
			<acme:menu-suboption code="master.menu.administrator.suggestion.create" action="/administrator/suggestion/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.tool-sheet.list" action="/administrator/tool-sheet/list"/>
			<acme:menu-suboption code="master.menu.administrator.tool-sheet.create" action="/administrator/tool-sheet/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-supplier" action="/authenticated/supplier/create" access="!hasRole('Supplier')"/>
			<acme:menu-suboption code="master.menu.user-account.supplier" action="/authenticated/supplier/update" access="hasRole('Supplier')"/>
			<acme:menu-suboption code="master.menu.user-account.become-buyer" action="/authenticated/buyer/create" access="!hasRole('Buyer')"/>
			<acme:menu-suboption code="master.menu.user-account.buyer" action="/authenticated/buyer/update" access="hasRole('Buyer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

