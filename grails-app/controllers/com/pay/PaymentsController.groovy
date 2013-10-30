package com.pay

class PaymentsController {
	static defaultAction = "pay"
	
	def pay = {
		if (params.from != null) {   //if params.from is not available then we won't know who the transactions history look up is for or who to make the payment from
			log.error " params $params"
			def fromAccount = Account.findByName(params.from)
			log.error " transactions for $fromAccount.name $fromAccount.transactions"
			
			if(params.to != null){ //We need to know who to make the payment to
			def toAccount = Account.findByName(params.to)
			log.error " paying to person $toAccount.name"
			def result = fromAccount.makePayment(params.amount, toAccount)  //payment is made and the boolean return value captured
			if (result){
				
				//emails are sent to both parties
				sendMail {
					to toAccount.email
					subject "You have recieved funds"
					body ' You have recieved GBP' + params.amount + ' from ' + fromAccount.email
				  }
				sendMail {
					to fromAccount.email
					subject "You have sent funds"
					body ' You have sent GBP' + params.amount + ' to ' + toAccount.email
				  }
			}
			//return a model for a transaction
			return [ paidFrom:fromAccount, result:result ]
			}
			//return a model for a transactions history lookup for the "pay.gsp"
			return [ paidFrom : fromAccount]
		}
	}
	
	def transactions = {
		if (params.from != null) {
			log.error "params $params"
			def fromAccount = Account.findByName(params.from)
			// return a model for a transactions history lookup from the "transacitons.gsp" 
			model:[ paidFrom : fromAccount]
		}
	}

}
