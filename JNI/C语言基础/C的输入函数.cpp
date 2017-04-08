#include<stdio.h>
#include<stdlib.h>

/*
2017年4月5日17:23:22
C的输入函数 
*/ 

int main(void)
{
	
	int count = 0;
	printf("请输入班级人数:");
	scanf("%d",&count);   //&是取地址 
	printf("班级人数:%d\n",count); 
	
	char aArray[4];    
	printf("请输入班级名称:"); 
	scanf("%s",&aArray);    //C语言是不检测下标越界的,即使输入超过4个字符,也能继续存的,它 会存到下一个地址去 
	printf("班级名称:%s\n",aArray);
	
	//如果上面的班级名称太长,则会将后面的count的地址覆盖掉,则count的值也会发生变化. 
	printf("班级人数:%d,班级名称:%s\n",count,aArray);
	
	printf("count的地址是 %d \n",&count);    //测试时count的地址是6487628     aArray的地址是 6487616
	printf("aArray的地址是 %d \n",&aArray);
	
    system("pause");
    return 0;
}

