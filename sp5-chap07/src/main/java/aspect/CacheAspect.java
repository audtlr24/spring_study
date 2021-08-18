package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CacheAspect {
	
	private Map<Long,Object> cache = new HashMap<>();
	
	@Pointcut("execution(public * chap07..*(long))")
	public void cacheTarget(){}
	
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		Long num = (Long) joinPoint.getArgs()[0]; // 첫번째 인자를 가져와서
		if(cache.containsKey(num)) { // 해당 인자가 key 로 하는 것이 cache 에 있으면 해당 값을 리턴
			System.out.printf("CacheAspect: Cache에서 구함 [%d]\n", num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed(); // cache에 존재하지 않으면 proxy 통해서 Pointcut 의 객체를 실행
		cache.put(num, result); // cache에 결과를 넣음
		System.out.printf("CacheAspect: Cache에 추가 [%d]\n", num);
		return result;
	}
	

}
