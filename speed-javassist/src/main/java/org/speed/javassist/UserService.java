package org.speed.javassist;

public class UserService {

	public int queryUsers(int param) {
        
		System.out.println("queryUsers");
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param + 1000;
    }

}
