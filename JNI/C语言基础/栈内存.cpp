#include<stdio.h>
#include<stdlib.h>

/*
2017��4��6��20:14:05
ջ�ڴ�   ϵͳͳһ����ͳһ���� 
��̬�ڴ����,ջ�ڴ��С�̶���,�ڴ��ַ�������� 
*/ 

int* getData() {
	int array[] = {1,2,3,4,5};
	printf("getData�е�array��ַ��:  %#x \n",array);   //�����ַ��������Ǹ���ַһ���� 
	return array;
} 

int* getData2() {
	//int array[] = {9,8,7,6,5,4,3,2,1};    �������������,��ô���滹�����543 
	int array[] = {5,4,3,2,1};
	printf("getData2�е�array��ַ��: %#x \n",array);
	return array;
}

int main(void)
{
	int* pointer = getData();  //��ȡ����������ʱ������׵�ַ 
	getData2();  //���ڸ���������ջ�ڴ��е�,��getData�������˻�û�л���,Ȼ��getData2ֱ��������, �͸����� 
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	printf("%d,%d,%d\n",*(pointer+0),*(pointer+1),*(pointer+2));
	
    system("pause");
    return 0;
}

