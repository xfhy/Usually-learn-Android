#include<stdio.h>
#include<stdlib.h>

/*
2017��4��5��20:40:44
ֵ���ݺ����ô���   ֵ���ݺ����ô��ݵ�ʵ����   ������ֵ   ֻ�������ô��ݴ��ݵ��ǵ�ֵַ
�����ͨ��һ���Ӻ������޸�main�����еı���,һ��Ҫ�����ô��� 
*/

swap(int* i, int* j)
{
	int temp = *i;
	*i = *j;
	*j = temp;
} 

int main(void)
{
	int i = 123;
	int j = 456;
	swap(&i,&j);
	printf("i=%d,j=%d \n",i,j);
    system("pause");
    return 0;
}

