package by.bstu.task4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main4 {
    public static void main(String[] args) throws InterruptedException {

        final int CARS = 10;
        final int FIRST_PARKING = 3 ;
        final int SECOND_PARKING = 4;

        Parking first_parking = new Parking(1, FIRST_PARKING);
        Parking second_parking = new Parking(2, SECOND_PARKING);

        Semaphore first = new Semaphore(first_parking.getSize(), true);
        Semaphore second = new Semaphore(second_parking.getSize(), true);

        ExecutorService service = Executors.newCachedThreadPool();
        for (int number = 1; number <= CARS; ) {
            service.execute(new Car(first_parking, first, number++));
            service.execute(new Car(second_parking, second, number++));
        }
        service.shutdown();
    }

    public static class Parking {
        private final int id;
        private final int size;

        public Parking(int id, int size) {
            this.id = id;
            this.size = size;
        }

        public int getId() {
            return id;
        }

        public int getSize() {
            return size;
        }
    }

    public static class Car implements Runnable {
        private final Semaphore slot;
        private final Parking parking;
        private final int id;

        public Car(Parking parking, Semaphore slot, int id) {
            this.parking = parking;
            this.slot = slot;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                slot.acquire();
                System.out.println("Car " + id + " parked on stand " + parking.getId());
                Thread.sleep(new Random().nextInt(300) + 300);
                slot.release();
                System.out.println("Car " + id + " leaving stand " + parking.getId());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
