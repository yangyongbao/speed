package org.speed.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream );
	    	
			SqlSession session = sqlSessionFactory.openSession();
			
			UserMapper userMapper = session.getMapper(UserMapper.class);
			
			List<User> userList = userMapper.queryUserList();
			
			System.out.println(userList.toString());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	
        System.out.println( "Hello World!" );
    }
}
