package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // Aspect annotation class 정의
public class ExeTimeAspect {
	
	@Pointcut("execution(public * chap07..*(..))") //aspect 를 적용할 대상을 정의 하는 부분. chap07 패키지와 그 하위 패키지의 public method로 설정
	private void publicTarget() {
	}
	
	@Around("publicTarget()") // Around Advice 설정. Pointcut 에 해당하고 @Around 가 붙은 public Bean 객체는 measure()를 적용
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{ //joinPoint는 프록시 대상 객체 호출 관련
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed(); // 여기서 대상 객체의 메서드를 실행
			return result;
		}finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature(); // 호출한 메서드의 시그니쳐 정보를 가져옴
			System.out.printf("%s.%s(%s) 실행시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(), //getTarget 은 대상 객체 정보
					sig.getName(),Arrays.toString(joinPoint.getArgs()), //getArgs 는 인자들 정보
					(finish-start));
		}
	}

}
