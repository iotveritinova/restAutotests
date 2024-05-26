package ThreadLocaTrainingGround;

public class ThreadDemoRun {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();

        Thread t1 = new Thread(threadDemo);
        Thread t2 = new Thread(threadDemo);
        Thread t3 = new Thread(threadDemo);

        t1.start();
        t2.start();
        t3.start();

    }
}
