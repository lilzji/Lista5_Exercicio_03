package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Main {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		for(int i = 0; i < 20; i++) {
			Thread threadBanco = new ThreadBanco(mutex);
			threadBanco.start();
		}
	}

}
