package main.java.section2;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new NewThread();

        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + Thread.currentThread().getName());
        }
    }
}
