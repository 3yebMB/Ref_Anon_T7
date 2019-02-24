package ex2;

public class Mile extends Stage {
    public Mile(int length) {
        this.length = length;
        this.description = "Морской путь " + length + " миль";
    }
    @Override
    public void go(Ship c) {
        System.out.println(c.getName() + " начал : " + description);
        System.out.println(c.getName() + " закончил : " + description);
//        try {
//
////            Thread.sleep(length / c.getSpeed() * 1000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}