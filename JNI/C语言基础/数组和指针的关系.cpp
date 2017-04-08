#include<stdio.h>
#include<stdlib.h>

/*
2017年4月6日18:55:33
 
*/ 

int main(void)
{
	char array[] = {'a','b','c','d','\0'};  
	 for(int i=0; i<4; i++) 
	 {
	 	printf("array[%d]的地址%#x\n",i,&array[i]);
	 } 
	char* pointer = array;   //数组的名字既是数组的首地址 
	printf("pointer为%#x \n",pointer); 
	printf("array[0]的地址%#x \n",&array[0]); 
	
	printf("(*pointer+0)的值是%c \n",*(pointer+0));    
	printf("(*pointer+1)的值是%c \n",*(pointer+1));
	printf("(*pointer+2)的值是%c \n",*(pointer+2));
	printf("(*pointer+3)的值是%c \n",*(pointer+3));
	
	int array2[] = {1,2,3,4};
	int* pointer2 = array2;   //数组的名字既是数组的首地址 
	
	//指针会根据当前的指针类型向前移动多少位,这里是int*   ,所以会自动向前加4位 
	printf("(*pointer2+0)的值是%d \n",*(pointer2+0));    
	printf("(*pointer2+1)的值是%d \n",*(pointer2+1));
	printf("(*pointer2+2)的值是%d \n",*(pointer2+2));
	printf("(*pointer2+3)的值是%d \n",*(pointer2+3));
	
    system("pause");
    return 0;
}

