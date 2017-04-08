#include<stdio.h>
#include<stdlib.h>

/*
2017年4月5日10:15:17
C的基本数据类型 
char, int, float, double, long, short, signed, unsigned, void 
*/ 

int main(void)
{
	printf("char占%d个字节\n",sizeof(char));
	printf("short占%d个字节\n",sizeof(short));
	printf("int占%d个字节\n",sizeof(int));
	printf("long占%d个字节\n",sizeof(long));
	printf("float占%d个字节\n",sizeof(float));
	printf("double占%d个字节\n",sizeof(double));
	
    system("pause");
    return 0;
}

