#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int i = 123;
	printf("i�ĵ�ַ��%#x\n",&i); 
	
	int *pointer = &i;
	printf("pointer��ֵ��%#x\n",pointer);
	printf("*pointer��ֵ��%d\n",*pointer);
	
	*pointer = 456;
	printf("�޸ĺ�*pointer��ֵ��%d\n",*pointer);
	printf("�޸ĺ�i��ֵ��%d\n",i);
	
    system("pause");
    return 0;
}

