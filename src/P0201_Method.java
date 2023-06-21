public class P0201_Method {
    public static void main(String[] args) {
        Person01 p1 = new Person01();
        p1.person01Age = 10;
        p1.Print001(p1);
        p1.speak();
    }
}



class Person01 {
    String person01Name;
    int person01Age;

    public void speak() {
        System.out.println("我是一个好人");
    }
    public void Print001(Person01 i) {
        System.out.println("Age = " + i.person01Age);
        System.out.println("Name = " + i.person01Name);
    }
}
