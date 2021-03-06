package net.slipp.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import net.slipp.domain.users.User;

public class UserDao extends JdbcDaoSupport {

	@PostConstruct
	public void initailize() {

		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("slipp.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		System.out.println("==== dao ===");

	}

	public User findById(String userId) {
		String sql = "select * from USERS where userId = ?";
		
		RowMapper<User> rowMapper = new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("email"), rs.getString("name")
						);
			}
		};
		
		try{
			return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		
		
	}

	public void create(User user) {
		
		String sql = "insert into USERS values(?,?,?,?)";
		getJdbcTemplate().update(sql, user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
		
	}

	public void update(User user) {
		String sql = "update USERS set password=?, name=?, email=? where userId = ?";
		getJdbcTemplate().update(sql, user.getPassword(),user.getName(),user.getEmail(), user.getUserId());
	}

}
