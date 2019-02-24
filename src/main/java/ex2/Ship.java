package ex2;

import java.util.concurrent.Semaphore;

public class Ship implements Runnable {
    static Semaphore smp = new Semaphore(Main.ships.length/2);
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Route route;
//    private int speed;
    private String name;

    public String getName() {
        return name;
    }
//    public int getSpeed() {
//        return speed;
//    }

    public Ship(Route route) {
        this.route = route;
//        this.speed = speed;
        CARS_COUNT++;
        this.name = "Корабль №" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            Main.aboo.set(true);
            Main.cdl2.countDown();
            Main.cdl2.await();
            if (Main.aboo.compareAndSet(true, false))
                System.out.println("Перевозка грзов началась.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < route.getStages().size(); i++) {
            route.getStages().get(i).go(this);
        }
        Main.cdl.countDown();
    }
}