#include<stdio.h>
#include<stdlib.h>

/*
2017��4��7��10:27:33
C�ṹ��   ����java��class struct������C�Ľṹ��
�ṹ��Ĵ�С���ڵ��ڽṹ����ÿһ������ռ�ֽ����ĺ�
�ṹ��Ĵ�С�������Ǹ�������ռ�ֽ�����������
C�ṹ���в��ܶ��庯��,���ǿ��Զ��庯��ָ��

����ָ��Ķ���   ����ֵ(*����ָ���������)(����ֵ);
-> ������������ 
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
	printf("stu�Ĵ�С%d\n",sizeof(stu)); 
	stu.studyPointer = &study;   //������ָ�븳
	stu.studyPointer();    //���ú��� 
	
	struct Student* stuPointer = &stu;
	(*stuPointer).sex = 'm'; 
	stuPointer->score = 98;
	printf("(*stuPointer).sex = %c \n",stu.sex);
	printf("stuPointer->score = %d \n",stu.score);
	
    system("pause");
    return 0;
}

