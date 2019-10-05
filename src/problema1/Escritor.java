package problema1;

import static problema1.Principal.writeLock;

/**
 * Sistemas Operativos 
 * @author Sebastian Leandro Luna Reinosa
 * @since 17/09/2019
 */
public class Escritor implements Runnable {

	public void run() {
		try {
			writeLock.acquire();
			System.out.println("Hilo " + Thread.currentThread().getName() + " Entra a cola de escritura");
			Thread.sleep(2500);
			System.out.println("Hilo " + Thread.currentThread().getName() + " Ha terminado de escribir");
			writeLock.release();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
