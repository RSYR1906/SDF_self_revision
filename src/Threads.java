
public class Threads {

    public static void main(String[] args) throws InterruptedException {

        Thread main = Thread.currentThread();
        main.setName("Main");
        main.setPriority(10);

        System.out.println(main.getName());
        System.out.println(main.getPriority());

        for (int i = 5; i > 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);         //thread hold for 1s before printing next line
        }

        SecondThread second = new SecondThread();
        second.start(); // use start() instead of run() so that the thread begins and is alive
                        // if use run(), thread is not alive but the method run() will execute

        System.out.println(second.isAlive());
    }
}