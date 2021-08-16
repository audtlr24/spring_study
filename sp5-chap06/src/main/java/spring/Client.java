package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean { // 각각은 Bean 의 초기화 및 소멸 인터페이스
	
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception{ //Bean 초기화 method 를 override 해서 구현
		System.out.println("Client.afterPropertiesSet() 실행");
	}
	
	public void send() {
		System.out.println("Client Send() to"+host);
	}

	@Override
	public void destroy() throws Exception { // Bean 소멸과정에서의 Method 구현
		System.out.println("Client.destroy() 실행");
		
	}

}
