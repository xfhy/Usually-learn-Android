#include<stdio.h>
#include<stdlib.h>

/*
2017��4��5��20:49:58
 ͨ��ָ�뷵�ض��ֵ 
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

