<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<nav>
	<ul>
		<li><h:outputLink value="./index.jsp">
				<h:outputText value="Home" />
			</h:outputLink></li>
		<li><h:outputLink value="./registration.jsp">
				<h:outputText value="Customer Registration" />
			</h:outputLink></li>
		<li><h:outputLink value="./openAccount.jsp">
				<h:outputText value="Open Account" />
			</h:outputLink></li>
		<li><h:outputLink value="./transaction.jsp" >
				<h:outputText value="Transaction" />
			</h:outputLink></li>
	</ul>
</nav>