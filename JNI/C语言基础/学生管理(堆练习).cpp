#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
/*
2017��4��6��22:25:18
ѧ������ 
*/ 
int main(void)
{
	
	int count = 0;
	printf("������ѧ������:");
	scanf("%d",&count); 
	int* pointer = (int *)malloc(sizeof(int)*count);   //��̬���� 
	int i=0;
	for(i=0; i<count; i++){
		printf("�������%d��ѧ����ѧ��:",i+1); 
		scanf("%d",(pointer+i));  //���� 
	}
	for(i=0; i<count; i++) {
		printf("��%d��ѧ����ѧ����%d \n",i+1,*(pointer+i));
	} 
	
	int newcount = 0;
	printf("��������������:");
	scanf("%d",&newcount); 
	
	//��̬����   ���malloc������ڴ�������㹻�Ŀռ�,���ں��������㹻��Ŀռ�
	//�������û��,��������һ���㹻��������ط��Ŀռ�,����֮ǰ��ֵ���ƹ���. 
	pointer = (int *)realloc(pointer,sizeof(int)*(count+newcount));
	
	for(i=count; i<count+newcount; i++){
		printf("�������%d��ѧ����ѧ��:",i+1); 
		scanf("%d",(pointer+i));  //���� 
	}
	for(i=count; i<count+newcount; i++) {
		printf("��%d��ѧ����ѧ����%d \n",i+1,*(pointer+i));
	} 
	
	
    system("pause");
    return 0;
}

