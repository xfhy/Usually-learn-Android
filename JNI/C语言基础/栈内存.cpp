#include<stdio.h>
#include<stdlib.h>

/*
2017年4月6日20:14:05
栈内存   系统统一分配统一回收 
静态内存分配,栈内存大小固定的,内存地址是连续的 
*/ 

int* getData() {
	int array[] = {1,2,3,4,5};
	printf("getData中的array地址是:  %#x \n",array);   //这个地址和下面的那个地址一样的 
	return array;
} 

int* getData2() {
	//int array[] = {9,8,7,6,5,4,3,2,1};    如果这里是这样,那么下面还是输出543 
	int array[] = {5,4,3,2,1};
	printf("getData2中的array地址是: %#x \n",array);
	return array;
}

int main(void)
{
	int* pointer = getData();  //获取到函数内零时数组的首地址 
	getData2();  //由于该数组是在栈内存中的,在getData中申请了还没有回收,然后getData2直接拿来用, 就覆盖了 
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	
    system("pause");
    return 0;
}

