package com.pay

class Account {
	// sample accounts Joe and Patty are created in the bootStap.groovy file 
    double balance = 200  // every account starts with 200 balance
	String name
	String email
	static hasMany = [ transactions: String ]
	
    static constraints = {
		name nullable: true
		email nullable: true
		transactions nullable: true
		}
	
	public boolean makePayment (String amountText, Account paidTo ) {
		//Boolean allows other classes to figure out status of the transaction
		boolean transactionConfirmation
		
		// amount is recieved as String and then converted to double. Would need more robust validation in case non numerical values are received
		double amount = amountText.toDouble()
		log.error " Amount for payment as double primitive $amount"
		
		//Only if funds are avilable, any payment can be made
		if (this.balance >= amount ) {
		//payment is made
		this.balance = this.balance - amount
		//payment is received
		paidTo.balance = paidTo.balance + amount
		
		//transactions are recorded as String
		def transactionFrom =  amount + " was paid to " + paidTo.name + " --> New balance is " + this.balance		
		this.addToTransactions(transactionFrom.toString())
		
		String transactionTo = amount + " was paid from " + this.name + " --> New balance is " + paidTo.balance
		paidTo.addToTransactions(transactionTo.toString())
	
		this.save(flush:true)
		paidTo.save(flush:true)
		
		//transaction is successful
		transactionConfirmation = true
		} else return transactionConfirmation = false //transaction not successful
	} 

}
