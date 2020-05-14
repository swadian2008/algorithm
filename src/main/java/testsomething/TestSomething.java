package testsomething;

/**
 * @author swadian
 * @date 2020/05/13 10:54
 * @Version 1.0
 */
public class TestSomething extends Thread{
    @Override
    public void run(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System. out .print( "run" );
    }
    static class Animal{
        public void move(){
            System.out.println("动物可以移动");
        }
    }
    static class Dog extends Animal{
        public void move(){
            System.out.println("狗可以跑和走");
        }
        public void bark(){
            System.out.println("狗可以吠叫");
        }
    }

    public static void main(String[] args) {
        Animal a = new Animal();
        Animal b = new Dog();
        a.move();  // 动物
        b.move();  // 狗
       // b.bark();
    }

}
