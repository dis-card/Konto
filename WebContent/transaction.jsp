<%--
	Author	:-	Vikash
	Purpose :-	"Transactions" are made via this page.
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
	<f:loadBundle basename="in.darkstars.konto.helper.message"
		var="lMsg" />
	<html>
<head>
<%@include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction</title>
<head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<h:outputLabel  styleClass="subHeading" value="#{lMsg.transactionForm }" />
	<h:form>
		<p class="msg">
			<c:if test="${transaction.msg != null }">
				<c:out value="${transaction.msg }" />
			</c:if>
		</p>
		<p class="errorMsg">
			<c:if test="${transaction.errorMsg != null }">
				<c:out value="${transaction.errorMsg }" />
			</c:if>
		</p>
		<h:panelGrid styleClass="table" columns="3">

			<h:outputText value="#{lMsg.customerId }" />
			<h:inputText id="customerId" value="#{transaction.customerId}"
				required="true" requiredMessage="#{lMsg.required }">
				<mcv:validateRegExpr pattern="^\s*\d+\s*$"
					message="#{lMsg['error.customerId']}" />
			</h:inputText>
			<h:message for="customerId" styleClass="errorMsg" />

			<h:outputText value="#{lMsg.accountType }" />
			<h:selectOneMenu id="accountType" value="#{transaction.accountType}" required="true" requiredMessage="#{validationMsg.required }">
				<f:selectItem itemValue="RS" itemLabel="Regular Savings" />
				<f:selectItem itemValue="SS" itemLabel="Salary Savings" />
				<mcv:validateRegExpr pattern="RS|SS"	message="#{lMsg['error.accountType']}" />
			</h:selectOneMenu>
			<h:message for="accountType" styleClass="errorMsg" />

			<h:outputText value="#{lMsg.transactionType}" />
			<h:selectOneMenu id="transactionType" value="#{transaction.transactionType}" required="true" requiredMessage="#{lMsg.required }" >
				<f:selectItem itemValue="D" itemLabel="Deposit" />
				<f:selectItem itemValue="W" itemLabel="Withdrawal" />
				<mcv:validateRegExpr pattern="D|W"	message="#{lMsg['error.transactionType'] }" />
			</h:selectOneMenu>
			<h:message for="transactionType" styleClass="errorMsg" />

			<h:outputText value="#{lMsg.amount }" />
			<h:inputText id="amount" value="#{transaction.amount}"
				required="true" requiredMessage="#{lMsg.required }">
				<mcv:validateRegExpr pattern="^\s*\d+\s*$"
					message="#{lMsg['error.amount']}" />
			</h:inputText>
			<h:message for="amount" styleClass="errorMsg" />

			<h:commandButton type="submit"
				action="#{transaction.performTransaction}" value="#{lMsg.perform }" />
			<h:commandButton immediate="true" value="#{lMsg.reset }" action="#{transaction.reset}"/>
		</h:panelGrid>
	</h:form>
	<%@include file="footer.jsp"%>
</body>
	</html>
</f:view>