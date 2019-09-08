package problema2;
import java.util.concurrent.*;

public class Buffer {
	
	private int[] buf;
	private int i = 0, j = 0;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore datos = new Semaphore(0, true);
	private Semaphore empty; 
	
	public Buffer(int tama) {
		
		buf = new int[tama];
		empty = new Semaphore(buf.length, true);
		
	}
	
	public void put(int dato) throws InterruptedException {
		
		empty.acquire();
		mutex.acquire();
		buf[i] = dato;
		System.out.println("Productor produce: " + dato);
		i = (i + 1) % buf.length;
		mutex.release();
		empty.release();
		
	}
	
	public int extract() throws InterruptedException {
		
		empty.acquire();
		mutex.acquire();
		int aux = j;
		j = (j + 1) % buf.length;
		System.out.println("Consumidor consume: " + buf[aux]);
		mutex.release();
		empty.release();
		return buf[aux];
	}

}
