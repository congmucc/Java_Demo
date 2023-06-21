package com.p0317;


// 多态的应用
// 多态参数
// 方法定义的形参类型为父类类型，实参类型允许为子类类型
//         应用实例1:前面的主人喂动物  p308
//         定义员工类Employee,包含姓名和月工资[private],以及计算年工资getAnnual
//         的方法。普通员工和经理继承了员工，经理类多了奖金bonus属性和管理manage方
//         法，普通员工类多了work方法，普通员工和经理类要求分别重写getAnnual方法
//         测试类中添加一个方法showEmpAnnual(Employee e),实现获取任何员工对象的
//         年工资,并在main方法中调用该方法[e.getAnnual(]
//         测试类中添加一个方法，testWork,如果是普通员工， 则调用work方法，如果是经
//         理，则调用manage方法//眼高手低..


public class PloyParameter {
    public static void main(String[] args) {
        Worker tom = new Worker("tom", 2500);
        Manager milan = new Manager("milan", 5000, 200000);
        PloyParameter ployParameter = new PloyParameter();
        ployParameter.showEmpAnnual(tom);
        ployParameter.showEmpAnnual(milan);

        ployParameter.testWork(tom);
        ployParameter.testWork(milan);

    }

    //showEmpAnnual(Employee e)
    //实现获取任何员工对象的年工资,并在main方法中调用该方法 [e.getAnnual()]
    public void showEmpAnnual(Employee e) {
        System.out.println(e.getAnnual());//动态绑定机制.
    }
    //添加一个方法，testWork,如果是普通员工，则调用work方法，如果是经理，则调用manage方法
    public void testWork(Employee e) {
        if(e instanceof  Worker) {
            ((Worker) e).work();//有向下转型操作
        } else if(e instanceof Manager) {
            ((Manager) e).manage();//有向下转型操作
        } else {
            System.out.println("不做处理...");
        }
    }
}
