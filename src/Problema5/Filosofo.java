package Problema5;


import java.util.Random;

public class Filosofo implements Runnable{
    private final int id;
    private final Tenedor TenedorIzquierdo;
    private final Tenedor TenedorDerecho;
    volatile boolean estaLleno = false;
    private Random randomGenerator = new Random();
    private int numTurnosDeComida = 0;

    
    public Filosofo(int id, Tenedor TenedorIzquierdo, Tenedor TenedorDerecho) {
      this.id = id;
      this.TenedorIzquierdo = TenedorIzquierdo;
      this.TenedorDerecho = TenedorDerecho;
    }

    @Override
    public void run() {

      try {
        while (!estaLleno) {
          pensar();
          if (TenedorIzquierdo.recogerTenedor(this, "izquierdo")) {
            if (TenedorDerecho.recogerTenedor(this, "derecho")) {
              comer();
              TenedorDerecho.dejarTenedor(this, "derecho");
            }
            TenedorIzquierdo.dejarTenedor(this, "izquierdo");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void pensar() throws InterruptedException {
      System.out.println(this + " esta pensando");
      Thread.sleep(randomGenerator.nextInt(1000));
    }

    private void comer() throws InterruptedException {
      System.out.println(this + " esta comiendo.");
      numTurnosDeComida++;
      Thread.sleep(randomGenerator.nextInt(1000));
    }

    public int getNumTurnosDeComida() {
      return numTurnosDeComida;
    }

    @Override
    public String toString() {
      return "Filosofo-" + id;
    }

    public Tenedor getTenedorIzquierdo() {
        return TenedorIzquierdo;
    }

    public Tenedor getTenedorDerecho() {
        return TenedorDerecho;
    }

    public int getId() {
        return id;
    }
}
    
    
