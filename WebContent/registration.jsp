<%--

	Author	:-	Vikash
	Purpose	:-	"Customer Registration page, through this page customers will be registered to the bank."

 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mcv"	uri="http://myfaces.apache.org/commons/validators"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<f:loadBundle basename="in.darkstars.konto.helper.message" var="lMsg" />
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
		<h:panelGrid id="registrationPanelGrid" styleClass="table" columns="3">
				<h:outputText value="#{lMsg.firstName}" />
				<h:inputText  id="firstName" value="#{customer.firstName}" required="true" requiredMessage="#{lMsg.required }" > <mcv:validateRegExpr pattern="^\s*[A-Z][a-z]*\s*$" message="#{lMsg['error.firstName'] }" /></h:inputText>
				<h:message for="firstName" styleClass="errorMsg" />
				
				<h:outputText  value="#{lMsg.lastName }" />
				<h:inputText id="lastName"  value="#{customer.lastName}" required="true" requiredMessage="#{lMsg.required }" ><mcv:validateRegExpr pattern="^\s*[A-Z][a-z]*\s*$" message="#{lMsg['error.lastName'] }" /></h:inputText>
				<h:message for="lastName" styleClass="errorMsg" />
				
				<h:outputText  value="#{lMsg.dateOfBirth }" />
				<t:inputDate id="dateOfBirth"  popupCalendar="true" value="#{customer.dateOfBirth}" required="true" requiredMessage="#{lMsg.required }"/>
				<h:message for="dateOfBirth" styleClass="errorMsg" />
				
				<h:outputText styleClass="subHeading" value="#{lMsg.address}" />
				<h:outputText />
				<h:outputText />
				
				<h:outputText  value="#{lMsg.streetName}" />
				<h:inputText id="streetName" value="#{customer.streetName}" required="true" requiredMessage="#{lMsg.required }" ><mcv:validateRegExpr pattern="^\s*[A-Z][a-z]+(\s*[A-Z][a-z]+)*\s*$" message="#{lMsg['error.streetName']}" /></h:inputText>
				<h:message for="streetName" styleClass="errorMsg" />
				
				<h:outputText  value="#{lMsg.streetNumber }" />
				<h:inputText id="streetNumber" value="#{customer.streetNumber}" required="true" requiredMessage="#{lMsg.required}"><mcv:validateRegExpr pattern="^\s*[0-9]+\s*$" message="#{lMsg['error.streetNumber']}" /></h:inputText>
				<h:message for="streetNumber" styleClass="errorMsg" />
				
				<h:outputText  value="#{lMsg.city }" />
				<h:inputText id="city" value="#{customer.city }" required="true" requiredMessage="#{lMsg.required}" ><mcv:validateRegExpr pattern="^\s*[A-Z][a-z]+(\s*[A-Z][a-z]+)*\s*$" message="#{lMsg['error.city']}" /></h:inputText>
				<h:message for="city" styleClass="errorMsg" />
			
				<h:outputText  value="#{lMsg.state }" />
				<h:inputText id="state" value="#{customer.state}" required="true" requiredMessage="#{lMsg.required}" ><mcv:validateRegExpr pattern="^\s*[A-Z][a-z]+(\s*[A-Z][a-z]+)*\s*$" message="#{lMsg['error.state']}" /></h:inputText>
				<h:message for="state" styleClass="errorMsg" />
				
				<h:outputText  value="#{lMsg.pin}" />
				<h:inputText id="pin" value="#{customer.pin}" required="true" requiredMessage="#{lMsg.required}" ><mcv:validateRegExpr pattern="^\s*[0-9]{6}\s*$" message="#{lMsg['error.pin']}" /></h:inputText>
				<h:message for="pin" styleClass="errorMsg" />
				
				<h:commandButton type="submit"
						action="#{customer.register}" value="#{lMsg.register }" />
				<h:commandButton immediate="true" value="#{lMsg.reset }" action="#{customer.reset}" />
		</h:panelGrid>
	</h:form>
	<%@include file="footer.jsp"%>
</body>
	</html>
</f:view>