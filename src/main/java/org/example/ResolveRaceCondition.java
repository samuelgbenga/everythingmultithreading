package org.example;


import java.util.*;




class ResolveRaceCondition {

    int randInt;
    Random random = new Random(System.currentTimeMillis());

  private  void printer() {

        int i = 1000000;
        while (i != 0) {
            synchronized(this) {
                if (randInt % 5 == 0) {
                    if (randInt % 5 != 0)
                        System.out.println(randInt);
                }
            }
            i--;
        }
    }

 private   void modifier() {

        int i = 1000000;
        while (i != 0) {
            synchronized(this) {
                randInt = random.nextInt(1000);
                i--;
            }
        }
    }

    private static void runTest() throws InterruptedException {


        final ResolveRaceCondition rc = new ResolveRaceCondition();
        Thread thread1 = new Thread(() -> rc.printer());
        Thread thread2 = new Thread(() -> rc.modifier());


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public static void main(String args[]) throws InterruptedException {
        ResolveRaceCondition.runTest();
    }
}