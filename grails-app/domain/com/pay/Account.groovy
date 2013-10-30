package com.pay

class Account {
    double balance = 200
	String name
	String email
	static hasMany = [ transactions: String ]
	
    static constraints = {
		name nullable: true
		email nullable: true
		transactions nullable: true
		}
	
	public boolean makePayment (String amountText, Account paidTo ) {
		boolean transactionConfirmation
		double amount = amountText.toDouble()
		log.error " Amount for payment as double primitive $amount"
		if (this.balance >= amount ) {
		
		this.balance = this.balance - amount
		paidTo.balance = paidTo.balance + amount
		
		def transactionFrom =  amount + " was paid to " + paidTo.name		
		this.addToTransactions(transactionFrom.toString())
		
		String transactionTo = amount + " was paid from " + this.name
		paidTo.addToTransactions(transactionTo.toString())
	
		this.save(flush:true)
		paidTo.save(flush:true)
		
		transactionConfirmation = true
		} else return transactionConfirmation = false
	} 

}
