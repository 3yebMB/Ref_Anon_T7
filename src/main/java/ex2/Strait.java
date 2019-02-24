package ex2;

public class Strait extends Stage {
    private int lenght;
    public Strait(int lenght) {
        this.length = lenght;
        this.description = "Пролив " + length + " миль";
    }
    @Override
    public void go(Ship c) {
        try {
            try {
                System.out.println(c.getName() + " ждёт освобождения : " + description);
                Ship.smp.acquire();
                System.out.println(c.getName() + " начал : " + description);
//                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Ship.smp.release();
                System.out.println(c.getName() + " закончил : " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}