package main.java.section6;

public class Synchronization {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

//        incrementingThread.start();
//        incrementingThread.join();
//        decrementingThread.start();
//        decrementingThread.join();

        incrementingThread.start();
        decrementingThread.start();
        incrementingThread.join();
        decrementingThread.join();


        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;

        Object lock = new Object();

        public /*synchronized*/ void increment() {
            synchronized (this.lock) {
                items++;
            }
        }

        public /*synchronized*/ void decrement() {
            synchronized (this.lock) {
                items--;
            }
        }

        public /*synchronized*/ int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}