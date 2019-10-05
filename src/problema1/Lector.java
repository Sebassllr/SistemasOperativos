package problema1;

import static problema1.Principal.readCount;
import static problema1.Principal.readLock;
import static problema1.Principal.writeLock;

/**
 * Sistemas Operativos 
 * @author Sebastian Leandro Luna Reinosa
 * @since 17/09/2019
 */
public class Lector implements Runnable {

	public void run() {
		try {
			readLock.acquire();
			readCount.getAndIncrement();
			if (readCount.equals(new Integer(1)))
				writeLock.acquire();

			readLock.release();

			System.out.println("Hilo " + Thread.currentThread().getName() + " Entra a cola de lectura ");
			Thread.sleep(1500);
			System.out.println("Hilo " + Thread.currentThread().getName() + " ha terminado de leer");

			readLock.acquire();
			readCount.getAndDecrement();
			if (readCount.equals(new Integer(0)))
				writeLock.release();

			readLock.release();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
