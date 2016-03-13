package net.slipp.dao;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import net.slipp.domain.users.User;

public class MyBatisTest {
	
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws Exception{
		String resource = "mybatis-config-test.xml";
		inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	
	
	@Test
	public void test() throws Exception {
		/*
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.findById","javajigi");
			System.out.println("user : "+user);
			//Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
		} finally {
		  session.close();
		}
		*/
		//finally를  해주지 않다고java7에서 아래와같이 해주면 닫아준다.
		try(SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findById","javajigi");
			System.out.println("user : "+user);
			//Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
		} 
		
	}
	
	
	@Test
	public void insert() throws Exception{
		try(SqlSession session = sqlSessionFactory.openSession()) {
			User a = new User("sdfdsfds","11111","fdfdf","1111");
			session.insert("UserMapper.create",a );
			
			
			User user = session.selectOne("UserMapper.findById","sdfdsfds");
			System.out.println("user : "+user);
			
			assertThat(user, is(a));
			//Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
		} 
		
	}
	

}
