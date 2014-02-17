<%--
	Author	:-	Vikash
	Purpose	:-	"Opening Account" page. Via this page customer open an account with the bank.
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.apache.org/commons/validators"
	prefix="mcv"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="in.darkstars.konto.helper.message"
		var="validationMsg" />
	<html>
<head>
<%@include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open Account</title>
<head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<p class="subHeading">Savings Account Creation</p>
	<h:form>
		<p class="msg">
			<c:if test="${account.accountId > 0}">
				<c:out value="${account.msg }" />
				<c:out value="${account.accountId}" />
			</c:if>
		</p>
		<p class="errorMsg">
			<c:if test="${account.errorMsg != null }">
				<c:out value="${account.errorMsg }" />
			</c:if>
		</p>
		<h:panelGrid styleClass="table" columns="3">
			<h:outputText value="Customer Id" />
			<h:inputText id="customerId" value="#{account.customerId}"
				required="true" requiredMessage="#{validationMsg.required }">
				<mcv:validateRegExpr pattern="^\s*\d+\s*$"
					message="#{validationMsg.customerId }" />
			</h:inputText>
			<h:message for="customerId" styleClass="errorMsg" />

			<h:outputText value="Preferred City" />
			<h:selectOneMenu id="preferredCity" value="#{account.preferredCity}"
				required="true" requiredMessage="#{validationMsg.required}">
				<t:selectItems value="#{account.preferredCityList }" var="city"
					itemLabel="#{city.name}" itemValue="#{city.acronym }" />
			</h:selectOneMenu>
			<h:message for="preferredCity" styleClass="errorMsg" />

			<h:outputText value="Account Type" />
			<h:selectOneMenu id="accountType" value="#{account.accountType}" required="true" requiredMessage="#{validationMsg.required }">
				<f:selectItem itemValue="RS" itemLabel="Regular Savings" />
				<f:selectItem itemValue="SS" itemLabel="Salary Savings" />
				<mcv:validateRegExpr pattern="RS|SS"	message="#{validationMsg.accountType }" />
			</h:selectOneMenu>
			<h:message for="accountType" styleClass="errorMsg" />

			<h:outputText value="Opening-Date" />
			<t:inputDate id="openingDate" disabled="true"
				value="#{account.openingDate}" type="date" required="true"
				requiredMessage="#{validationMsg.required}" />
			<h:message for="openingDate" styleClass="errorMsg" />

			<h:outputText value="Initial Deposit" />
			<h:inputText id="initialDeposit" required="true"
				requiredMessage="#{validationMsg.required }"
				value="#{account.initialDeposit}">
				<mcv:validateRegExpr pattern="^\s*[0-9]+\s*$"
					message="#{validationMsg.initialDeposit}" />
			</h:inputText>
			<h:message for="initialDeposit" styleClass="errorMsg" />

			<h:commandButton type="submit" action="#{account.open}" value="Open" />
			<h:commandButton type="reset" value="Reset" />
		</h:panelGrid>
	</h:form>
	<%@include file="footer.jsp"%>
</body>
	</html>
</f:view>