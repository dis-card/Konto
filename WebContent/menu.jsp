<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<nav>
	<ul>
		<li><h:outputLink value="/Konto/faces/index.jsp">
				<h:outputText value="Home" />
			</h:outputLink></li>
		<li><h:outputLink value="/Konto/faces/registration.jsp">
				<h:outputText value="Customer Registration" />
			</h:outputLink></li>
		<li><h:outputLink value="/Konto/faces/openAccount.jsp">
				<h:outputText value="Open Account" />
			</h:outputLink></li>
		<li><h:outputLink value="/Konto/faces/transaction.jsp">
				<h:outputText value="Transaction" />
			</h:outputLink></li>
	</ul>
</nav>