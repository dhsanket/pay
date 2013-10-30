import com.pay.*

class BootStrap {

    def init = { servletContext ->
		
		if (!Account.count()) {
			createData()
		}
    }
    def destroy = {
    }
	
	private void createData() {
 
		Account Joe = new Account(name:"Joe", transactions: [], email:"Joe@g.com")
		Account Patty = new Account(name:"Patty",  transactions: [], email:"Patty@g.com")
		Joe.save(flush:true)
		Patty.save(flush:true)
//		log.errors "$Joe.validate()"
//		log.errors "$Patty.validate()"
		if( !Joe.save() ) {
			Joe.errors.each {
				 println it
			}
		 }
	}
}
