#include<stdio.h>
#include<stdlib.h>

/*
2017��4��5��13:16:28

%d  -  int
%ld �C long int
%lld - long long
%hd �C ������    short int
%c  - char
%f -  float
%lf �C double
%u �C �޷�����
%x �C ʮ��������� int ����long int ����short int
%o -  �˽������
%s �C �ַ���

*/

int main(void)
{
	char c = 'a';    //char��1���ֽ�,���ܱ�ʾ����Ŷ 
	short s = 123;
	int i = 123456;
	long l = 12345678;
	float f = 3.1415;
	double d = 3.1415926;
	
	printf("c = %c \n",c);
	printf("s = %hd \n",s);
	printf("i = %d \n",i);
	printf("l = %ld \n",l);
	printf("f = %.4f \n",f);     //�������λ��.x   %f   
	printf("d = %.7lf \n",d);    //�����������Ŷ 
	
	unsigned int ui = 321545;   //�޷����� 
	printf("ui = %u \n",ui);
	
	
	printf("%#x \n",i);   //16����
	printf("%#o \n",i);   //8���� 
	
	char aArray[] = {'a','b','c','d','\0'}; //�����ַ���  �ַ�����ĩβ�ǵüӽ����� 
	char bArray[] = "�����ַ���Ŷ";    //���ַ�ʽ����д������,���Ա�ʾ���� 
	printf("aArray = %s \n",aArray);
	printf("bArray = %s \n",bArray);
	
    system("pause");
    return 0;
}

