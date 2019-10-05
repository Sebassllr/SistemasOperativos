package problema5;

/**
 * Sistemas Operativos 
 * @author Sebastian Leandro Luna Reinosa
 * @since 17/09/2019
 */
public class Principal {
	
	public static void main(String[] args) throws InterruptedException {
        Filosofo[] fs = new Filosofo[5];
        final int filosofosLength = fs.length;
        Object[] forks = new Object[filosofosLength];
 
        for (int i = 0; i < filosofosLength; i++) {
            forks[i] = new Object();
        }
 
        for (int i = 0; i < filosofosLength; i++) {
            Object tenedorIzquierdo = forks[i];
            Object tenedorDerecho = forks[(i + 1) % filosofosLength];
 
            
            if (i == filosofosLength - 1) {
                fs[i] = new Filosofo(tenedorDerecho, tenedorIzquierdo); 
            } else {
            	fs[i] = new Filosofo(tenedorIzquierdo, tenedorDerecho);
            }
            
            //fs[i] = new Filosofo(leftFork, rightFork);
             
            Thread t = new Thread(fs[i], "Filósofo " + (i + 1));
            t.start();
        }
	}
}
