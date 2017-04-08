#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	printf("Hello World!\n");  //"\n"换行符 
	
	system("javac Hello.java");   //编译执行java Hello World源码 
	system("java Hello"); 
	
	//system("notepad");  //运行windows的记事本    会阻塞,直到关闭记事本  
	system("pause");   //system执行Windows的系统命令 
}  
