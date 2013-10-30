<%@ page import="com.pay.Account" %>

<html>
    <head>
        <title>See transactions</title>
        <meta name="layout" content="main" />
    </head>
    <body>
		<div>
			<h2> Transactions details </h2>
			
			<br/>
			<g:if test="${paidFrom != null}"> 
				<div>Account: ${paidFrom.name} <br /> Balance: ${paidFrom.balance} </div>
				<div> 
					<ul>
					<g:each in="${paidFrom.transactions}" var="transaction"> 	
							<li> ${transaction} </li>	
					</g:each>
					</ul>
				</div>
			</g:if>        
		</div>
    </body>
</html>
