package problema2;

import java.util.LinkedList;

/**
 * Sistemas Operativos 
 * @author Sebastian Leandro Luna Reinosa
 * @since 17/09/2019
 */
public class Buffer {

	LinkedList<Integer> list = new LinkedList<Integer>();
	int capacity = 2;

	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (this) {
				while (list.size() == capacity)
					wait();
				System.out.println("El productor produjo - " + value);
				list.add(value++);
				notify();
				Thread.sleep(1000);
			}
		}
	}

	public void consume() throws InterruptedException {
		while (true) {
			synchronized (this) {

				while (list.size() == 0)
					wait();

				int val = list.removeFirst();

				System.out.println("El consumidor consumió - " + val);

				notify();

				Thread.sleep(1000);
			}
		}
	}
}
