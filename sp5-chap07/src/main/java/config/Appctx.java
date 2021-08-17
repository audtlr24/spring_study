package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

@Configuration
@EnableAspectJAutoProxy //Aspect 가 붙은 클래스를 사용하기 위해서는 붙여줘야함
public class Appctx {
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() { //ExeTimeAspect 에서 정의한 Pointcut 에 해당하는 chap07 패키지 임으로, measure() 가 실행됨
		return new RecCalculator();
	}

}
