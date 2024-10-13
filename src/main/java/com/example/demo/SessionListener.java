package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

import static com.example.demo.GlobalApp.USERS;

@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {
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
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        increment();
        log.info("Sessione iniziata");
        log.info("{} utenti online.", getValue());
        log.info("sessione iniziata ---------------------------------");
        this.sessionCounter(httpSessionEvent);
        USERS = new AtomicInteger(getValue());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        decrement();
        log.info("Sessione terminata");
        log.info("{} utenti online.", getValue());
        log.info("sessione terminata ---------------------------------");
    }
    public void sessionCounter(HttpSessionEvent httpSessionEvent){
    	httpSessionEvent.getSession().getServletContext().setAttribute("users", this.getValue());
    }
}