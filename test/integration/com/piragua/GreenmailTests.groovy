package com.piragua

import grails.test.*
import com.icegreen.greenmail.util.*

class GreenmailTests extends GroovyTestCase {
	
	def mailService
	def greenMail
	
	
	    protected void setUp() {
	        super.setUp()
	    }
	
	    protected void tearDown() {
	//        super.tearDown()
			greenMail.deleteAllMessages()
	    }

	   void testSendMail() {
	        Map mail = [message:'hello world', from:'from@piragua.com', to:'to@piragua.com', subject:'subject']
	
	        mailService.sendMail {
	            to mail.to
	            from mail.from
	            subject mail.subject
	            body mail.message
	        }
	
	        assertEquals(1, greenMail.getReceivedMessages().length)
	
	        def message = greenMail.getReceivedMessages()[0]
	
	        assertEquals(mail.message, GreenMailUtil.getBody(message))
	        assertEquals(mail.from, GreenMailUtil.getAddressList(message.from))
	        assertEquals(mail.subject, message.subject)
	    }
}
