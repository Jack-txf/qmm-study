/**
 * @author Williams_Tian
 * @CreateDate 2024/9/3
 */
public class TestMain {
    public static void main(String[] args) {
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // ok();
        for (int i = 0; i < 10; i++) {
            System.out.println("main");
        }
        System.out.println("======================");

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("qweqew");
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("123456");
            }
        }).start();
    }

    public static void ok() {
        int i = 1 / 0;
        System.out.println("aaaa");
    }
}
