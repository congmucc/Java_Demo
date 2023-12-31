package com.han;

public class P0052_AutoConvertDetail {
	public static void main(String[] args){
	    //细节 1： 有多种类型的数据混合运算时，
		//系统首先自动将所有数据转换成容量最大的那种数据类型，然后再进行计算
		int n1 = 10; //ok
		//float d1 = n1 + 1.1;//错误 n1 + 1.1 => 结果类型是 double
		//double d1 = n1 + 1.1;//对 n1 + 1.1 => 结果类型是 double
		float d1 = n1 + 1.1F;//对 n1 + 1.1 => 结果类型是 float
		//细节 2: 当我们把精度(容量)大 的数据类型赋值给精度(容量)小 的数据类型时，
		//就会报错，反之就会进行自动类型转换。
		//
		//int n2 = 1.1;//错误 double -> int
		//细节 3: (byte, short) 和 char 之间不会相互自动转换
		//当把具体数赋给 byte 时，(1)先判断该数是否在 byte 范围内，如果是就可以
		byte b1 = 10; //对 , -128-127
		// int n2 = 1; //n2 是 int
		// byte b2 = n2; //错误，原因： 如果是变量赋值，判断类型
		//
		// char c1 = b1; //错误， 原因 byte 不能自动转成 char
		// //细节 4: byte，short，char 他们三者可以计算，在计算时首先转换为 int 类型
		byte b2 = 1;
		byte b3 = 2;
		short s1 = 1;
		//short s2 = b2 + s1;//错, b2 + s1 => int
		int s2 = b2 + s1;//对, b2 + s1 => int
		//byte b4 = b2 + b3; //错误: b2 + b3 => int
		//
		//boolean 不参与转换
		boolean pass = true;
		//int num100 = pass;// boolean 不参与类型的自动转
  }
}