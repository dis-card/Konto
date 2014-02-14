<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mcv"	uri="http://myfaces.apache.org/commons/validators"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<f:loadBundle basename="in.darkstars.helper.message" var="validationMsg" />
	<html>
<head>
<%@include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Registration</title>
<head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<p class="subHeading">Customer Registration Form</p>
	<h:form>
		<p class="msg">
			<c:if test="${customer.customerId > 0}">
				<c:out value="${customer.msg }" />
				<c:out value="${customer.customerId}" />
			</c:if>
		</p>
		<p class="errorMsg">
			<c:if test="${customer.errorMsg != null }">
				<c:out value="${customer.errorMsg }" />
			</c:if>
		</p>
		<table class="table">
			<tr>
				<td><h:outputText value="First Name" /></td>
				<td><h:inputText  id="firstName" value="#{customer.firstName}" required="true" requiredMessage="#{validationMsg.required }" > <mcv:validateRegExpr pattern="[ ]*[A-Z][a-z]*[ ]*" message="#{validationMsg.firstName }" /></h:inputText></td>
				<td><h:message for="firstName" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="Last Name" /> </td>
				<td><h:inputText id="lastName" value="#{customer.lastName}" required="true" requiredMessage="#{validationMsg.required }" ><mcv:validateRegExpr pattern="[ ]*[A-Z][a-z]*[ ]*" message="#{validationMsg.lastName }" /></h:inputText></td>
				<td><h:message for="lastName" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="Date-Of-Birth" /></td>
				<td><t:inputDate id="dateOfBirth"  value="#{customer.dateOfBirth}" type="date" required="true" requiredMessage="#{validationMsg.required}" /></td>
				<td><h:message for="dateOfBirth" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td colspan="2"><h:outputText styleClass="subHeading"
						value="Address" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="Street Name" /></td>
				<td><h:inputText id="streetName" value="#{customer.streetName}" required="true" requiredMessage="#{validationMsg.required }" ><mcv:validateRegExpr pattern="[ ]*[A-Z][a-z]*[ ]*" message="#{validationMsg.streetName}" /></h:inputText></td>
				<td><h:message for="streetName" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="Street Number" /></td>
				<td><h:inputText id="streetNumber" value="#{customer.streetNumber}" required="true" requiredMessage="#{validationMsg.required}"><mcv:validateRegExpr pattern="[ ]*[0-9]+[ ]*" message="#{validationMsg.streetNumber}" /></h:inputText></td>
				<td><h:message for="streetNumber" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="City" /></td>
				<td><h:inputText id="city" value="#{customer.city }" required="true" requiredMessage="#{validationMsg.required}" ><mcv:validateRegExpr pattern="[ ]*[A-Z][a-z]*[ ]*" message="#{validationMsg.city}" /></h:inputText></td>
				<td><h:message for="city" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="State" /></td>
				<td><h:inputText id="state" value="#{customer.state}" required="true" requiredMessage="#{validationMsg.required}" ><mcv:validateRegExpr pattern="[ ]*[A-Z][a-z]*[ ]*" message="#{validationMsg.state}" /></h:inputText></td>
				<td><h:message for="state" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText  value="Pin" /></td>
				<td><h:inputText id="pin" value="#{customer.pin}" required="true" requiredMessage="#{validationMsg.required}" ><mcv:validateRegExpr pattern="[ ]*[0-9][0-9][0-9][0-9][0-9][0-9][ ]*" message="#{validationMsg.pin}" /></h:inputText></td>
				<td><h:message for="pin" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:commandButton type="submit"
						action="#{customer.register}" value="Register" /></td>
				<td><h:commandButton  type="reset" immediate="true" value="Reset" action="#{customer.reset }"/></td>
			</tr>
		</table>
	</h:form>
	<%@include file="footer.jsp"%>
</body>
	</html>
</f:view>