package testsomething;

/**
 * @author swadian
 * @date 2020/05/14 11:45
 * @Version 1.0
 */
class BaseClass {
    public BaseClass() {}
    {
        System.out.println("I’m BaseClass class"); // 代码块
    }
    static {
        System.out.println("static BaseClass");   // 静态代码块
    }
}
public class Base extends BaseClass {
    public Base() {}
    {
        System.out.println("I’m Base class");  // 子代码块
    }
    static {
        System.out.println("static Base");  // 子静态代码块
    }
    public static void main(String[] args) {
        new Base();
    }
}
