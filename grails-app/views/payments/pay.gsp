<%@ page import="com.pay.Account" %>

<html>
    <head>
        <title>Pay Some Person</title>
        <meta name="layout" content="main" />
    </head>
    <body>
		<div>		
			<g:form  name="myForm" action="pay">
			<h2>Pay</h2>
			
			<br/>
			<b>From:</b> <g:select name="from"
						noSelection="${['null':'Select One...']}"
			          from="${Account.list()}"
			          optionKey="name"
			          optionValue="name"/> 
			          
			<br/> 
			<b>To: </b> <g:select name="to"
						noSelection="${['null':'Select One...']}"
			          from="${Account.list()}"
			          optionKey="name"
			          optionValue="name"/>
			
			<br/>
			<b>Amount:</b> <input name="amount" type="text" value="" />
			<button> <g:submitToRemote update="updateMe" value= "submit request"/> </button>
			</g:form>
			
			<br/>
			<div id="updateMe">		
			<b>
			<div> 
			<g:if test="${result == true}"> STATUS - Transaction was successful </g:if>
			<g:if test="${result == false}"> STATUS -  Transaction failed, please check whether funds are available to transfer </g:if>
			</div>
			</b> 
			
			<br />
			<g:render template="/payments/transactions" />
			</div>


        </div>
    </body>
</html>
