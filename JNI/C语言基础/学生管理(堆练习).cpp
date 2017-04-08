#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
/*
2017年4月6日22:25:18
学生管理 
*/ 
int main(void)
{
	
	int count = 0;
	printf("请输入学生人数:");
	scanf("%d",&count); 
	int* pointer = (int *)malloc(sizeof(int)*count);   //动态数组 
	int i=0;
	for(i=0; i<count; i++){
		printf("请输入第%d个学生的学号:",i+1); 
		scanf("%d",(pointer+i));  //数组 
	}
	for(i=0; i<count; i++) {
		printf("第%d个学生的学号是%d \n",i+1,*(pointer+i));
	} 
	
	int newcount = 0;
	printf("请输入插班生人数:");
	scanf("%d",&newcount); 
	
	//动态申请   如果malloc申请的内存后面有足够的空间,则在后面申请足够大的空间
	//如果后面没有,则重新找一个足够大的其他地方的空间,并将之前的值复制过来. 
	pointer = (int *)realloc(pointer,sizeof(int)*(count+newcount));
	
	for(i=count; i<count+newcount; i++){
		printf("请输入第%d个学生的学号:",i+1); 
		scanf("%d",(pointer+i));  //数组 
	}
	for(i=count; i<count+newcount; i++) {
		printf("第%d个学生的学号是%d \n",i+1,*(pointer+i));
	} 
	
	
    system("pause");
    return 0;
}

