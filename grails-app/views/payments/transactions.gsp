<%@ page import="com.pay.Account" %>

<html>
    <head>
        <title>See transactions</title>
        <meta name="layout" content="main" />
    </head>
    <body>
		<div>
		<h2> Transactions </h2>
		
		<br/>
		<g:form  name="myForm2" action="transactions">
		<b>Person:</b> <g:select name="from"
					noSelection="${['null':'Select One...']}"
		          from="${Account.list()}"
		          optionKey="name"
		          optionValue="name"/> 
		          
		<br/>
		<b><button> <g:submitToRemote update="updateMe" value= "submit request"/> </button></b>
		</g:form>
		
		
		------------------------------------------------------
		<div id="updateMe"> <g:render template="/payments/transactions" /> </div>
    </body>
</html>
