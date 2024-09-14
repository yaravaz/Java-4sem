package by.bstu.task1;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int COUNT_OF_OPERATORS = 3;
        final int COUNT_OF_CLIENTS = 7;

        CallCenter callCenter = new CallCenter(COUNT_OF_OPERATORS);

        for (int i = 0; i < COUNT_OF_CLIENTS; i++) {
            new Client("callCenter.Client " + (i + 1), callCenter).start();

        }
    }
    public static class Client extends Thread{
        private final String name;
        private boolean serviced = false;
        private CallCenter callCenter;

        public Client(String name, CallCenter callCenter) {
            super(name);
            this.name = name;
            this.callCenter = callCenter;
        }

        @Override
        public void run() {
            try {
                callCenter.enqueue(this);

                synchronized (this) {
                    while (!serviced) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void notifyServiced() {
            serviced = true;
            notify();
        }
    }

    public static class CallCenter {
        private Queue<Client> waitingQueue;
        private Operator[] operators;

        public CallCenter(int operatorsCount) {
            this.waitingQueue = new LinkedList<>();
            this.operators = new Operator[operatorsCount];

            for (int i = 0; i < operators.length; i++) {
                (operators[i] = new Operator("Operator " + (i + 1))).start();
            }
        }

        public synchronized void enqueue(Client client) {
            waitingQueue.offer(client);
            notifyAll();
        }

        public synchronized Client dequeue() throws InterruptedException {
            while (waitingQueue.isEmpty()) {
                wait();
            }
            return waitingQueue.poll();
        }

        private class Operator extends Thread {
            public Operator(String name) {
                super(name);
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        Client client = dequeue();
                        System.out.println(getName() + " starts servicing " + client.getName());

                        Thread.sleep((long) (Math.random() * 2000));

                        System.out.println(getName() + " finished servicing " + client.getName());
                        client.notifyServiced();
                    } catch (InterruptedException e) {}
                    if(waitingQueue.isEmpty()){
                        return;
                    }
                }
            }
        }
    }

}