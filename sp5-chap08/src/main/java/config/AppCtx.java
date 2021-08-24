package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;

@Configuration
public class AppCtx {
	
	@Bean(destroyMethod = "close") // close method 는 커넥션 풀의 connection 을 닫음
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver"); //jdbc 드라이버 클래스 정의
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8"); //jdbc url 지정
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2); // 커넥션 풀 초기화시 커넥션 개수 지정
		ds.setMaxActive(10); // 커넥션 풀에서 가져올수 있는 최대 커넥션 개수 지정
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000*3);
		ds.setTimeBetweenEvictionRunsMillis(10*1000);
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}

}
