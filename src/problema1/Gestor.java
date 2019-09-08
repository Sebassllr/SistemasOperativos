package problema1;

import java.util.concurrent.Semaphore;

public class Gestor {
	private int numLec = 0;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore datos = new Semaphore(1, true);
	private boolean escritor = false;
	private int numEscritor = 0;
	
	public synchronized void open(int id) throws InterruptedException {
		while(escritor || numEscritor > 0) {
			wait();
		}
		numLec++;
		System.out.println("Lector " + id + " entra");
	}
	
	public synchronized void close(int id) {
		System.out.println("Lector " + id + " sale");
		numLec--;
		if(numLec == 0) notifyAll();
	}
	
	public synchronized void openE(int id) throws InterruptedException {
		numEscritor++;
		while(escritor || numLec > 0) {
			wait();
		}
		escritor = true;
		System.out.println("Escritor  " + id + " entra");
	}
	
	public synchronized void closeE(int id) {
		numEscritor--;
		System.out.println("Escritor " + id + " sale");
		escritor = false;
		notifyAll();
	}

}
