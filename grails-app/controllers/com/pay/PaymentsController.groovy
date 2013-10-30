package com.pay

class PaymentsController {
	static defaultAction = "pay"
	
	def pay = {
		if (params.from != null) {
			log.error " params $params"
			def fromAccount = Account.findByName(params.from)
			log.error " transactions for $fromAccount.name $fromAccount.transactions"
			
			if(params.to != null){
			def toAccount = Account.findByName(params.to)
			log.error " paying to person $toAccount.name"
			def result = fromAccount.makePayment(params.amount, toAccount)
			if (result){
				sendMail {
					to toAccount.email
					subject "You have recieved funds"
					body ' You have recieved ' + params.amount + ' from ' + fromAccount.name
				  }
				sendMail {
					to fromAccount.email
					subject "You have sent funds"
					body ' You have sent ' + params.amount + ' to ' + toAccount.name
				  }
			}
			return [ paidFrom:fromAccount, result:result ]
			}

			return [ paidFrom : fromAccount]
		}
	}
	
	def transactions = {
		if (params.from != null) {
			log.error "params $params"
			def fromAccount = Account.findByName(params.from)
			model:[ paidFrom : fromAccount]
		}
	}

}
