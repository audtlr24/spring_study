package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.Appctx;

public class MainAspect {
	
	public static void main (String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Appctx.class);
		
		Calculator cal = ctx.getBean("calculator",Calculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = "+fiveFact);
		System.out.println(cal.getClass().getName()); // 객체가 RecCalculator 가 아닌 proxy 가 나옴. AOP 결과
		ctx.close();
	}
	
}
