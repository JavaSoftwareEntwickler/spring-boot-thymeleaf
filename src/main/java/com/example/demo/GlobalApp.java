package com.example.demo;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GlobalApp {
	/* Variabili globali dell'applicazione */
	public static String NOME_SITO_WEB_KEY = "nomeSitoWeb";
	public static String NOME_SITO_WEB_VALUE ="The blog name!";
	public static AtomicInteger USERS = new AtomicInteger(0);
}
