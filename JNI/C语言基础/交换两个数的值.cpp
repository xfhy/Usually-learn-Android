#include<stdio.h>
#include<stdlib.h>

/*
2017年4月5日20:40:44
值传递和引用传递   值传递和引用传递的实际上   都是数值   只不过引用传递传递的是地址值
如果想通过一个子函数来修改main函数中的变量,一定要用引用传递 
*/

swap(int* i, int* j)
{
	int temp = *i;
	*i = *j;
	*j = temp;
} 

int main(void)
{
	int i = 123;
	int j = 456;
	swap(&i,&j);
	printf("i=%d,j=%d \n",i,j);
    system("pause");
    return 0;
}

