package ThreadLocaTrainingGround;

class ThreadDemo implements Runnable {

    int counter;
    ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<>();

    public void run() {
        counter++;

        /*if(threadLocalCounter.get() != null) {
            threadLocalCounter.set(threadLocalCounter.get() + 1);
        } else {
            threadLocalCounter.set(0);
        }

         */
        if(threadLocalCounter.get() != null) {
            threadLocalCounter.set(threadLocalCounter.get() + 1);
        } else {
            if (counter % 2 == 0) {
                threadLocalCounter.remove();
            } else {
                threadLocalCounter.set(0);
            }
        }
        printCounters();
    }

    public void printCounters(){
        System.out.println("Counter: " + counter);
        System.out.println("threadLocalCounter: " + threadLocalCounter.get());
    }
}