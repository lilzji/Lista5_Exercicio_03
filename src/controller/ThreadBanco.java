package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	static int saldoConta = 1000;
	Semaphore mutex;

	public ThreadBanco(Semaphore mutex) {
		this.mutex = mutex;
	}

	public void run() {
		int id = (int) Math.round(Math.random());
		if (id == 0) {
			saque();
		} else {
			deposito();
		}
	}

	public void saque() {
		try {
			mutex.acquire();
			transacaoSaque();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

	private void transacaoSaque() {
		int valorDebitado = (int) ((Math.random() * 96) + 5);
		saldoConta -= valorDebitado;
		System.err.println("O valor debitado na conta foi " + valorDebitado + " e o saldo agora eh de " + saldoConta);
	}

	public void deposito() {
		try {
			mutex.acquire();
			transacaoDeposito();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

	private void transacaoDeposito() {
		int valorCreditado = (int) ((Math.random() * 96) + 5);
		saldoConta += valorCreditado;
		System.out.println("O valor creditado na conta foi " + valorCreditado + " e o saldo agora eh de " + saldoConta);
	}

}
