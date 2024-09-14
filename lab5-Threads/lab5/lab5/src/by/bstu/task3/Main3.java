package by.bstu.task3;

import java.util.concurrent.Semaphore;

public class Main3 {
    public static void main(String[] args) throws InterruptedException {

        final int MOVING = 3;
        final int LEFT = 10;
        final int RIGHT = 12;

        Semaphore sem = new Semaphore(1, true);
        RoadRepair roadRepair = new RoadRepair(MOVING);
        new Thread(new CarsThread(sem,"left", roadRepair,LEFT)).start();
        new Thread( new CarsThread(sem,"right", roadRepair,RIGHT)).start();
    }

    public static class RoadRepair {
        int bandwidth;
        public RoadRepair(int bandwidth){
            this.bandwidth = bandwidth;
        }
        public void CarDrive(String side){

        }
    }

    public static class CarsThread implements Runnable{
        int carsCount;
        RoadRepair roadRepair;
        Semaphore semaphore;
        String roadSide;
        public CarsThread(Semaphore sem, String side, RoadRepair roadRepair, int carsCount){
            this.semaphore = sem;
            this.roadSide = side;
            this.roadRepair = roadRepair;
            this.carsCount = carsCount;
        }
        @Override
        public void run() {
            while(true) {
                try {
                    semaphore.acquire();
                    System.out.println("Движение с полосы " + this.roadSide);

                    for (int i = 0; i < roadRepair.bandwidth; i++) {
                        if(carsCount == 0){
                            System.out.println("На полосе " + this.roadSide + " не осталось автомобилей");
                            semaphore.release();
                            return;
                        }
                        System.out.println("Автомобиль " + carsCount + " начал движение");
                        Thread.sleep(500);
                        carsCount--;
                        System.out.println("Автомобиль " + (carsCount + 1) + " закончил движение");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                semaphore.release();
            }
        }
    }
}