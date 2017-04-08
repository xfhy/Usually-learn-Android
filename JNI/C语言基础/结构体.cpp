#include<stdio.h>
#include<stdlib.h>

/*
2017年4月7日10:27:33
C结构体   类似java的class struct来声明C的结构体
结构体的大小大于等于结构体中每一变量的占字节数的和
结构体的大小是最大的那个变量所占字节数的整数倍
C结构体中不能定义函数,但是可以定义函数指针

函数指针的定义   返回值(*函数指针变量名字)(返回值);
-> 简介引用运算符 
*/

void study() {
	printf("good good stuty!\n");
}

struct Student {
	short int age;  
	int score;
	char sex;
	void (*studyPointer)();
}; 

int main(void)
{
	struct Student stu = {10,100,'c'};
	printf("stu的大小%d\n",sizeof(stu)); 
	stu.studyPointer = &study;   //给函数指针赋
	stu.studyPointer();    //调用函数 
	
	struct Student* stuPointer = &stu;
	(*stuPointer).sex = 'm'; 
	stuPointer->score = 98;
	printf("(*stuPointer).sex = %c \n",stu.sex);
	printf("stuPointer->score = %d \n",stu.score);
	
    system("pause");
    return 0;
}

