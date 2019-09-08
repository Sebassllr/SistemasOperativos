package Problema5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal {
    private static final int NUM_FILOSOFOS = 5;
    private static final int MILISEGUNDOS = 1000 * 10;
    
    
    public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = null;
    Filosofo[] filosofos = null;
    try {
      filosofos = new Filosofo[NUM_FILOSOFOS];
      Tenedor[] Tenedors = new Tenedor[NUM_FILOSOFOS];
      
      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        Tenedors[i] = new Tenedor(i);
      }
      
      executorService = Executors.newFixedThreadPool(NUM_FILOSOFOS);

      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        filosofos[i] = new Filosofo(i, Tenedors[i], Tenedors[(i + 1) % NUM_FILOSOFOS]);
          System.out.println("El filosofo " + filosofos[i].getId() + " tiene como Tenedores: ");
          System.out.println("   Tenedor-" + filosofos[i].getTenedorDerecho().getId() + " <-- derecho");
          System.out.println("   Tenedor-" + filosofos[i].getTenedorIzquierdo().getId() + " <-- izquierdo");
      }
      
        System.out.println("");
      
      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        executorService.execute(filosofos[i]);
      }
      
      Thread.sleep(MILISEGUNDOS);
     
      for (Filosofo philosopher : filosofos) {
        philosopher.estaLleno = true;
      }

    } finally {
      executorService.shutdown();

      while (!executorService.isTerminated()) {
        Thread.sleep(1000);
      }
      
        System.out.println("");
      
      for (Filosofo filosofo : filosofos) {
        System.out.println("El " + filosofo + "  tuvo " 
                + filosofo.getNumTurnosDeComida() + " turnos para comer.");
      }
    }
  }
}
