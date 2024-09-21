package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class SessionListener implements HttpSessionListener
{
	private static Logger logger = LoggerFactory.getLogger(SessionListener.class);
	
	// AtomicInteger = Variabile Multithreading 
	public AtomicInteger counter = new AtomicInteger(0);
	public int getValue() {
        return counter.get();
    }
    public void increment() {
        while(true) {
            int existingValue = getValue();
            int newValue = existingValue + 1;
            if(counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
    public void decrement() {
        while(true) {
            int existingValue = getValue();
            int newValue = existingValue - 1;
            if(counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
	@Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
		 increment();
		 
    	 logger.info("Sessione iniziata");
    	 System.out.println(getValue() +" utenti online.");
    	 System.out.println("sessione iniziata ---------------------------------");
    	 this.sessionCounter(httpSessionEvent);
    	 GlobalApp.users= new AtomicInteger(getValue());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
     decrement();
     logger.info("Sessione terminata");
  	 System.out.println(getValue() +" utenti online.");
  	 System.out.println("sessione terminata ---------------------------------");
    }
    public void sessionCounter(HttpSessionEvent httpSessionEvent){
    	httpSessionEvent.getSession().getServletContext().setAttribute("users", this.getValue());
    }
}