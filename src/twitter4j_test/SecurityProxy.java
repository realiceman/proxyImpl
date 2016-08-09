package twitter4j_test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SecurityProxy implements InvocationHandler {

	private Object obj;
	
	public SecurityProxy(Object obj) {
		this.obj = obj;
	}

	public static Object newInstance(Object obj){
		return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj
				     .getClass().getInterfaces(), new SecurityProxy(obj));
	}
	
	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		Object result = null;
		try {
			result = m.invoke(obj, args);
		} catch (Exception e) {
			e.getMessage();
		}
		
		return result;
	}

}
