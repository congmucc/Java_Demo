public class P0213_MethodParameter01 {
    public static void main(String[] args) {
//测试
        B b = new B();
        Person1 p = new Person1();  //因为new是引用的 故可进引用传递
        p.name4 = "jack";
        p.age4 = 10;
        b.test200(p);
        System.out.println("main 的 p.age=" + p.age4);//10000
    }

}

class Person1 {
    String name4;
    int age4;
}
class B {
    public void test200(Person1 p) {
//          p.age4 = 10000; //修改对象属性
//思考
        p = new Person1();
        p.name4 = "tom";
        p.age4 = 99;
//思考
//p = null;
    }
}
