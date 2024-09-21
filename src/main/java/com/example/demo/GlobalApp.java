package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class GlobalApp {
	/* Variabili globali dell'applicazione */
	public static String nomeSito ="The blog name!";	
	public static AtomicInteger users = new AtomicInteger(0);	
}
