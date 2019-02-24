package ex2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static final int SHIPS_COUNT = 7;
    public static Ship[] ships = new Ship[SHIPS_COUNT];
    static final CountDownLatch cdl = new CountDownLatch(SHIPS_COUNT);
    static final CountDownLatch cdl2 = new CountDownLatch(SHIPS_COUNT);
    static AtomicBoolean aboo = new AtomicBoolean(false);

    public static void main(String[] args) {
        System.out.println("Корабли заходят в бухту и готовятся принят груз на борт.");
        Route route = new Route(new Mile(60), new Strait(80), new Mile(40));

        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship(route);
            new Thread(ships[i]).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Перевозка всех грузов завершена.");
    }
}