package ThreadLocaTrainingGround;

import java.util.concurrent.ThreadLocalRandom;

class RandomNumbers extends Thread {

    public void run() {
        try {
            int bound = 100;
            int result = ThreadLocalRandom.current().nextInt(bound);
            System.out.println("Thread " + Thread.currentThread().getId() + " generated " + result);
        }
        catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            RandomNumbers randomNumbers = new RandomNumbers();
            randomNumbers.start();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime));
    }
}