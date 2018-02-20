package com.blogspot.danieldeveloper.teste;

import javax.persistence.Persistence;

public class MainTeste {

	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("FinanceiroPU");

	}

}
