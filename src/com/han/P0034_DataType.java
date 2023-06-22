package com.han;

public class P0034_DataType {
	public static void main(String[] args){
/*	    int a = 10;  //true
	    long b = 10L;  //  不能没有l因为java的整数常量（具体值）
	    //默认为int型，声明long型常量须后加'l'或'L';
	    double c = 100.23;  //true
	    float d = 100.32F;  //  同上一样，java的浮点型常量的默认
	    //为double型，声明float型常量须后加'f'或'F';
*/	    
	    //十进制数形式：如：5.12 512.0f .512 (必须有小数点）
        double num1=.123;
        System.out.println(num1);//等价于0.123
        
        //科学计数法形式:如：5.12e2 [5.12 * 10 的 2 次方 ] 5.12E-2 []
        System.out.println(5.12e2);//512.0
        System.out.println(5.12E-2);//0.0512
        
        //通常情况下，应该使用 double 型，因为它比 float 型更精确。
		//[举例说明]double num9 = 2.1234567851;float num10 = 2.1234567851F;
		double num9 = 2.1234567851;
		float num10 = 2.1234567851F;
		System.out.println(num9);
		System.out.println(num10);

		
		
		//浮点数使用陷阱: 2.7 和 8.1 / 3 比较
		//看看一段代码
		double num11 = 2.7;
		double num12 = 8.1/3; //8.1 / 3; //2.7
		System.out.println(num11);//2.7
		System.out.println(num12);//接近 2.7 的一个小数，而不
        //得到一个重要的使用点: 当我们对运算结果是小数的进行相等判断是，要小心
        //应该是以两个数的差值的绝对值，在某个精度范围类判断
       
        if( num11 == num12) {
		System.out.println("num11 == num12 相等");
		}
		
		//正确的写法 , 两者相减后小于某个值
		if(Math.abs(num11 - num12) < 0.000001 ) {
		System.out.println("差值非常小，到我的规定精度，认为相等...");
        }
        boolean pass = true;
        if(pass) {
            System.out.println("ture");
        } else {
        	System.out.println("false");
        }
    
        
	}
}