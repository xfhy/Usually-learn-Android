
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MyMath {

	public static boolean isTrue(String str)
	{
		for(int i=0; i<str.length(); i++)
		{
			if(i%2 != 0)
			{
				if(str.charAt(i)!='+' && str.charAt(i)!='-' && str.charAt(i)!='*'){
					return false;
				}
			}
		}
		return true;
	} 
	
	public static void main(String[] args) {
		
		String s = new String();
		List<String> numberList = new LinkedList<>();
		Scanner scanner = new Scanner(System.in);
		s = scanner.nextLine();
		char lastChar = s.charAt(s.length()-1);
		if( lastChar == '+' || lastChar == '-' || lastChar == '*'){
			System.err.println("表达式不对");
			System.exit(0);
		}
		
		if(isTrue(s)){
			
			//压栈到     一个放符号    一个放数字
			//先计算乘法,放到List中
			
			//放入List中,顺序放入,取出时判断是=-*,   如果是*则乘好了再压栈回去
			
			for (int i = 0; i < s.length(); i++) {
				numberList.add(s.charAt(i)+"");
			}
			
			int length = numberList.size();
			for (int i = length-1; i >= 0 ; i--) {
				if(numberList.get(i).equals("*") && i!=0 && i!=(length-1) ){
					int a = Integer.parseInt(numberList.get(i-1));
					int b = Integer.parseInt(numberList.get(i+1));
					int result = a*b;
					numberList.set(i-1, result+"");
					numberList.remove(i+1);
					numberList.remove(i);
				}
			}
			
			length = numberList.size();
			for (int i = 0; i < length; i++) {
				
				//System.out.println(numberList);
				
				if(i!=0 && i!=(length-1) && numberList.get(i).equals("-")){
					int a = Integer.parseInt(numberList.get(i-1));
					int b = Integer.parseInt(numberList.get(i+1));
					int result = a-b;
					numberList.set(i-1, result+"");
					numberList.remove(i);
					numberList.remove(i);
					length -= 2;
				} else if(i!=0 && i!=(length-1) && numberList.get(i).equals("+")){
					int a = Integer.parseInt(numberList.get(i-1));
					int b = Integer.parseInt(numberList.get(i+1));
					int result = a+b;
					numberList.set(i-1, result+"");
					numberList.remove(i);
					numberList.remove(i);
					length -= 2;
				}
				
			}
			
		}
		
		System.out.println(numberList.get(0));
		
	}

}