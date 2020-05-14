package org.speed.base;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Hello world!
 *
 */
public class UserMapper 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	/**
    	InputStream inputStream = Resources.getResourceAsStream("");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream );
    	
    	
		
		SqlSession session = sqlSessionFactory.openSession();
    	
		
		//session.insert("", 1);
		//session.commit();

		
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		
		
        System.out.println( "Hello World!" );**/
    }
}
