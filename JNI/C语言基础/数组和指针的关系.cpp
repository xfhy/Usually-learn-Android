#include<stdio.h>
#include<stdlib.h>

/*
2017��4��6��18:55:33
 
*/ 

int main(void)
{
	char array[] = {'a','b','c','d','\0'};  
	 for(int i=0; i<4; i++) 
	 {
	 	printf("array[%d]�ĵ�ַ%#x\n",i,&array[i]);
	 } 
	char* pointer = array;   //��������ּ���������׵�ַ 
	printf("pointerΪ%#x \n",pointer); 
	printf("array[0]�ĵ�ַ%#x \n",&array[0]); 
	
	printf("(*pointer+0)��ֵ��%c \n",*(pointer+0));    
	printf("(*pointer+1)��ֵ��%c \n",*(pointer+1));
	printf("(*pointer+2)��ֵ��%c \n",*(pointer+2));
	printf("(*pointer+3)��ֵ��%c \n",*(pointer+3));
	
	int array2[] = {1,2,3,4};
	int* pointer2 = array2;   //��������ּ���������׵�ַ 
	
	//ָ�����ݵ�ǰ��ָ��������ǰ�ƶ�����λ,������int*   ,���Ի��Զ���ǰ��4λ 
	printf("(*pointer2+0)��ֵ��%d \n",*(pointer2+0));    
	printf("(*pointer2+1)��ֵ��%d \n",*(pointer2+1));
	printf("(*pointer2+2)��ֵ��%d \n",*(pointer2+2));
	printf("(*pointer2+3)��ֵ��%d \n",*(pointer2+3));
	
    system("pause");
    return 0;
}

