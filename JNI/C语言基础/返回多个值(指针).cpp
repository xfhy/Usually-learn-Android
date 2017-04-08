#include<stdio.h>
#include<stdlib.h>

/*
2017年4月5日20:49:58
 通过指针返回多个值 
*/ 

func(int *a,int *b)
{
	*a += 2;
	*b += 4; 
} 

int main(void)
{
	int i = 1;
	int j = 2;
	func(&i,&j);
	printf("i = %d,j = %d \n",i,j);
    system("pause");
    return 0;
}

