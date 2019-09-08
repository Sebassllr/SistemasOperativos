package problema1;

import static problema1.Principal.writeLock;

public class Escritor implements Runnable{
    
	public void run() {
        try {
            writeLock.acquire();
            System.out.println("Hilo "+Thread.currentThread().getName() + " Escribiendo ");
            Thread.sleep(2500);
            System.out.println("Hilo "+Thread.currentThread().getName() + " Ha terminado de escribir");
            writeLock.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
