package org.speed.javassist;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

/**
 * <pre>
 * 
 * 	2222,222.222
 * 
 * </pre>
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	
    	try {
    		proxy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        System.out.println( "Hello World!" );
    }
    
    
    /**
     * 动态生成一个类
     * @throws Exception
     */
    public static void  createClassAndInvoken() throws Exception
    {
    	ClassPool cp = ClassPool.getDefault();
        CtClass gclazz = cp.makeClass("org.speed.javassist.GeneratedClass");
        CtMethod gmethod = CtMethod.make("public void sayHello() { System.out.println(\"Hello Javaassist\"); }", gclazz);
        gclazz.addMethod(gmethod);
        Class<?> gc = gclazz.toClass();
        gclazz.detach();
        Object ginst = gc.newInstance();
        Method gm = gc.getMethod("sayHello");
        gm.invoke(ginst);
	
    }
    
    /**
     * 修改类
     * @throws Exception
     */
    public static void  editClassAndInvoken() throws Exception {
    	ClassPool cp = ClassPool.getDefault();
        CtClass clazz = cp.get("org.speed.javassist.TestClass");
        CtMethod method = clazz.getDeclaredMethod("compute");
        method.insertAfter("System.out.println(\"compute called with param: \" + java.util.Arrays.toString($args) + \", return: \" + $_);");
        
        String result = clazz.toString();
        System.out.println(result);
        
        
        clazz.toClass();
        clazz.detach();
 
        UserService test = new UserService();
        test.queryUsers(5);
    }
    
    
    /**
     * 生成代理
     * @throws Exception
     */
    public static void  proxy() throws Exception {
    	
    	ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(UserService.class);
        
        factory.setFilter(new MethodFilter() {
            public boolean isHandled(Method m) {
                if (m.getName().equals("queryUsers")) {
                    return true;
                }
                return false;
            }
        });
        
        Class<?> clazz = factory.createClass();
        UserService object = (UserService) clazz.newInstance();
        ((Proxy) object).setHandler(new MethodHandler() {
        	
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                long start = System.currentTimeMillis();
                try {
                	
                	System.out.println("=====>proxy before:" + proceed.getName());
                	
                    Object result = proceed.invoke(self, args);//目标方法
                    
                	System.out.println("=====>proxy after:" + proceed.getName());

                    return result;
                    
                } catch (Exception e) {
                    throw e;
                } finally {
                    long taken = System.currentTimeMillis() - start;
                    System.out.println("call method: " + thisMethod.getName() + " take: " + taken + "ms");
                }
            }
        });
 
        object.queryUsers(2);
    }

    
}
