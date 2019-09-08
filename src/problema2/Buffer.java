package problema2;

import java.util.LinkedList;

public class Buffer {

	LinkedList<Integer> list = new LinkedList<Integer>();
	int capacity = 2;

	// Function called by producer thread
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
				// consumer thread waits while list
				// is empty
				while (list.size() == 0)
					wait();

				// to retrive the ifrst job in the list
				int val = list.removeFirst();

				System.out.println("El consumidor consumió - " + val);

				// Wake up producer thread
				notify();

				// and sleep
				Thread.sleep(1000);
			}
		}
	}
}
