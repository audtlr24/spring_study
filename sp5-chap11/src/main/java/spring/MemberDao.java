package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?", //query 문을 날리고
				new MemberRowMapper(), // maprow()를 override 에서 정의한 RowMapper 를 통해 results 를 받음
				email);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER",
				new MemberRowMapper());
		return results;
	}
	public int count() {
		Integer counter = jdbcTemplate.queryForObject( // queryForObject 는 쿼리결과가 한 행일때, List 가 아닌 RowMapper object 하나로 반환해줌
				"select count(*) from MEMBER",
				Integer.class); // 결과가 object 일수도 있지만 어떤 칼럼일때는 integer, double 과 같이도 받아올수 있음
		return counter;
	}
	public void update(Member member) {//// insert,update,delete 쿼리는 update를 이용. update 는 변경된 행의 갯수를 리턴
		jdbcTemplate.update( 
				"update MEMBER set NAME = ?,PASSWORD = ? where EMAIL =?",
				member.getName(),member.getPassword(),member.getEmail());
	}
	public void insert(final Member member) { //member 의 data 를 insert 하고 결과인 key 값을 setId 를 통해 member 객체에 할당
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)",
						new String[] {"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		},keyHolder); // update method 의 두번째 인자로 keyHolder를 전달
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
		
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where REGDATE between ? and ?"+
				"order by REGDATE desc",
				new MemberRowMapper(),
				from,to);
		return results;
	}
}

