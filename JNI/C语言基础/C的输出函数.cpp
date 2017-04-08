#include<stdio.h>
#include<stdlib.h>

/*
2017年4月5日13:16:28

%d  -  int
%ld – long int
%lld - long long
%hd – 短整型    short int
%c  - char
%f -  float
%lf – double
%u – 无符号数
%x – 十六进制输出 int 或者long int 或者short int
%o -  八进制输出
%s – 字符串

*/

int main(void)
{
	char c = 'a';    //char是1个字节,不能表示汉字哦 
	short s = 123;
	int i = 123456;
	long l = 12345678;
	float f = 3.1415;
	double d = 3.1415926;
	
	printf("c = %c \n",c);
	printf("s = %hd \n",s);
	printf("i = %d \n",i);
	printf("l = %ld \n",l);
	printf("f = %.4f \n",f);     //控制输出位数.x   %f   
	printf("d = %.7lf \n",d);    //会四舍五入的哦 
	
	unsigned int ui = 321545;   //无符号数 
	printf("ui = %u \n",ui);
	
	
	printf("%#x \n",i);   //16进制
	printf("%#o \n",i);   //8进制 
	
	char aArray[] = {'a','b','c','d','\0'}; //这是字符串  字符串的末尾记得加结束符 
	char bArray[] = "我是字符串哦";    //这种方式不用写结束符,可以表示汉字 
	printf("aArray = %s \n",aArray);
	printf("bArray = %s \n",bArray);
	
    system("pause");
    return 0;
}

