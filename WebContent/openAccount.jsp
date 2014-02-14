<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mcv"	uri="http://myfaces.apache.org/commons/validators"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="in.darkstars.helper.message"
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
		<table class="table">
			<tr>
				<td><h:outputText value="Customer Id" /></td>
				<td><h:inputText id="customerId" value="#{account.customerId}"
						required="true" requiredMessage="#{validationMsg.required }" > <mcv:validateRegExpr pattern="[0-9]+" message="#{validationMsg.customerId }" /></h:inputText></td>
				<td><h:message for="customerId" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText value="Preferred City" /></td>
				<td><h:selectOneMenu value="#{account.preferredCity}">
						<t:selectItems value="#{account.preferredCityList }" var="city"
							itemLabel="#{city.name}" itemValue="#{city.acronym }" />
					</h:selectOneMenu></td>
			</tr>
			<tr>
				<td><h:outputText value="Account Type" /></td>
				<td><h:selectOneMenu value="#{account.accountType}">
						<f:selectItem itemValue="RS" itemLabel="Regular Savings" />
						<f:selectItem itemValue="SS" itemLabel="Salary Savings" />
					</h:selectOneMenu></td>
			</tr>
			<tr>
				<td><h:outputText  value="Opening-Date" /></td>
				<td><t:inputDate id="openingDate"  value="#{account.openingDate}" type="date" required="true" requiredMessage="#{validationMsg.required}" /></td>
				<td><h:message for="openingDate" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText value="Initial Deposit" /></td>
				<td><h:inputText id="initialDeposit" required="true" requiredMessage="#{validationMsg.required }" value="#{account.initialDeposit}" ><mcv:validateRegExpr pattern="[0-9]*.[0-9]*" message="#{validationMsg.initialDeposit }" /></h:inputText></td>
				<td><h:message for="initialDeposit" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:commandButton type="submit" action="#{account.open}"
						value="Open" /></td>
				<td><h:commandButton  type="reset" immediate="true" value="Reset" action="#{account.reset }"/></td>
			</tr>
		</table>
	</h:form>
	<%@include file="footer.jsp"%>
</body>
	</html>
</f:view>