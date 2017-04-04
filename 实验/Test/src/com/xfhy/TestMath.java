package com.xfhy;

import java.util.Scanner;

/**
 * 2017年3月9日
 * 
 * xfhy
 * 
 * TODO
 */
public class TestMath {

	public static void main(String[] args) {
		int n = 0;
		int height[] = new int[20];
		int weight[] = new int[20];
		int index = 0;
		double standard = 0.0;
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNextInt()) {
			n = scanner.nextInt();
			scanner = new Scanner(System.in);
			while(n>0){
				n--;
				String line = scanner.nextLine();
				String[] split = line.split(" ");
				height[index] = Integer.parseInt(split[0]);
				weight[index] = Integer.parseInt(split[1]);
				index++;
				scanner = new Scanner(System.in);
			}
			
			
			for(int i=0; i< index; i++){
				standard = (height[i]-100)*0.9;    //标准体重
				double temp = Math.abs(weight[i]/2-standard);
				if( temp < standard*0.1 ){
					System.out.println("you are wan mei!");
				} else if(temp > standard*0.1){
					System.out.println("you are tai pang le!");
				} else {
					System.out.println("you are tai shou le!");
				}
			}
			
		}
	}

}
