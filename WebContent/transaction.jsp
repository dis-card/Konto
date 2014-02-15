<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mcv"
	uri="http://myfaces.apache.org/commons/validators"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="in.darkstars.helper.message"
		var="validationMsg" />
	<html>
<head>
<%@include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction</title>
<head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<p class="subHeading">Transaction Form</p>
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
		<table class="table">
			<tr>
				<td><h:outputText value="Customer Id" /></td>
				<td><h:inputText id="customerId"
						value="#{transaction.customerId}" required="true"
						requiredMessage="#{validationMsg.required }">
						<mcv:validateRegExpr pattern="^\s*\d+\s*$"
							message="#{validationMsg.customerId }" />
					</h:inputText></td>
				<td><h:message for="customerId" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:outputText value="Account Type" /></td>
				<td><h:selectOneMenu value="#{transaction.accountType}">
						<f:selectItem itemValue="RS" itemLabel="Regular Savings" />
						<f:selectItem itemValue="SS" itemLabel="Salary Savings" />
					</h:selectOneMenu></td>
			</tr>
			<tr>
				<td><h:outputText value="Transaction Type" /></td>
				<td><h:selectOneMenu value="#{transaction.transactionType}">
						<f:selectItem itemValue="D" itemLabel="Deposit" />
						<f:selectItem itemValue="W" itemLabel="Withdrawal" />
					</h:selectOneMenu></td>
			</tr>
			<tr>
				<td><h:outputText value="Amount" /></td>
				<td><h:inputText id="amount" value="#{transaction.amount}"
						required="true" requiredMessage="#{validationMsg.required }">
						<mcv:validateRegExpr pattern="^\s*\d+\s*$"
							message="#{validationMsg.amount}" />
					</h:inputText></td>
				<td><h:message for="amount" styleClass="errorMsg" /></td>
			</tr>
			<tr>
				<td><h:commandButton type="submit"
						action="#{transaction.performTransaction}" value="Perform" /></td>
				<td><h:commandButton  type="reset" value="Reset" /></td>
			</tr>
		</table>
	</h:form>
	<%@include file="footer.jsp"%>
</body>
	</html>
</f:view>