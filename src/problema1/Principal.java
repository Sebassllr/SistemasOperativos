package problema1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {

	public static Semaphore readLock = new Semaphore(1);
	public static Semaphore writeLock = new Semaphore(1);
	static AtomicInteger readCount = new AtomicInteger(0);

	public static void main(String[] args) {
		Lector read = new Lector();
		Escritor write = new Escritor();
		Thread t1 = new Thread(read);
		t1.setName("1");
		Thread t2 = new Thread(read);
		t2.setName("2");
		Thread t3 = new Thread(write);
		t3.setName("3");
		Thread t4 = new Thread(read);
		t4.setName("4");
		t1.start();
		t3.start();
		t2.start();
		t4.start();
	}
}
