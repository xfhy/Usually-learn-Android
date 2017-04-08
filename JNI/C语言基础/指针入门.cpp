#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int i = 123;
	printf("i的地址是%#x\n",&i); 
	
	int *pointer = &i;
	printf("pointer的值是%#x\n",pointer);
	printf("*pointer的值是%d\n",*pointer);
	
	*pointer = 456;
	printf("修改后*pointer的值是%d\n",*pointer);
	printf("修改后i的值是%d\n",i);
	
    system("pause");
    return 0;
}

