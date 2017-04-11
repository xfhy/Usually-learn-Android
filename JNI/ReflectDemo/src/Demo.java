import java.lang.reflect.Method;


/**
 * 2017年4月8日
 * 
 * xfhy
 * 
 * TODO
 */
public class Demo {

	public static void main(String[] args) {
		
		//1.获取字节码对象
		Class util = Utils.class;
		try {
			//2.获取Method对象    方法名,参数类型
			Method method = util.getMethod("test", String.class);
			//3.通过字节码对象创建一个Obejct 
			Object obj = util.newInstance();
			//4.通过对象调用方法     对带有指定参数的指定对象调用由此 Method 对象表示的底层方法
			method.invoke(obj, "hello");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
