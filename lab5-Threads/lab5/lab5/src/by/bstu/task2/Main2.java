package by.bstu.task2;

import java.util.LinkedList;
import java.util.Queue;



public class Main2 {
    public static void main(String[] args) {
        int n = 3;
        int m = 10;
        int t = 2;

        SkiRental skiRental = new SkiRental(n);

        for (int i = 0; i < 7; i++) {
            Thread workerThread = new Thread(new Client(skiRental));
            workerThread.start();
        }

        for (int i = 7; i < m; i++) {
            Thread clientThread = new Thread(new Pensioner(skiRental));
            clientThread.start();
        }
    }

    public static class SkiRental {
        private int availableSkis;
        private Queue<String> clientQueue;
        private Queue<String> pensionerQueue;

        public SkiRental(int n) {
            availableSkis = n;
            clientQueue = new LinkedList<>();
            pensionerQueue = new LinkedList<>();
        }

        public synchronized void rentSki(String client, boolean isPensioner) {
            if (availableSkis > 0) {
                availableSkis--;
                System.out.println(client + " получил лыжи");
            } else {
                if (isPensioner) {
                    pensionerQueue.add(client);
                } else {
                    clientQueue.add(client);
                }
                System.out.println(client + " поставлен в очередь");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void returnSki() {
            availableSkis++;
            if (!pensionerQueue.isEmpty()) {
                String nextPensioner = pensionerQueue.poll();
                System.out.println(nextPensioner + " получил лыжи");
                notify();
            } else if (!clientQueue.isEmpty()) {
                String nextClient = clientQueue.poll();
                System.out.println(nextClient + " получил лыжи");
                notify();
            }
        }
    }

    public static class Client implements Runnable {
        private static int counter = 1;
        private String name;
        private SkiRental skiRental;

        public Client(SkiRental skiRental) {
            name = "Клиент " + counter++;
            this.skiRental = skiRental;
        }

        @Override
        public void run() {
            skiRental.rentSki(name, false);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " вернул лыжи");
            skiRental.returnSki();
        }
    }

    public static class Pensioner implements Runnable {
        private static int counter = 1;
        private String name;
        private SkiRental skiRental;

        public Pensioner(SkiRental skiRental) {
            name = "Пенсионер " + counter++;
            this.skiRental = skiRental;
        }

        @Override
        public void run() {
            skiRental.rentSki(name, true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " вернул лыжи");
            skiRental.returnSki();
        }
    }
}