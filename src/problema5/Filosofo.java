package problema5;

/**
 * Sistemas Operativos 
 * @author Sebastian Leandro Luna Reinosa
 * @since 17/09/2019
 */
public class Filosofo implements Runnable {

	private Object tenedorIzquierdo;
	private Object tenedorDerecho;

	public Filosofo(Object leftFork, Object rightFork) {
		this.tenedorIzquierdo = leftFork;
		this.tenedorDerecho = rightFork;
	}

	public void run() {
		try {
			while (true) {
				doAction(System.nanoTime() + ": Pensando");
				synchronized (tenedorIzquierdo) {
					doAction(System.nanoTime() + ": Tomó el tenedor izquierdo");
					synchronized (tenedorDerecho) {
						doAction(System.nanoTime() + ": Tomó el tenedor derecho - comiendo");

						doAction(System.nanoTime() + ": Bajó el tenedor derecho");
					}

					doAction(System.nanoTime() + ": Bajó el tenedor izquierdo - pensando");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}

	private void doAction(String action) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + action);
		Thread.sleep(((int) (Math.random() * 500)));
	}
}
