#include<stdio.h>
#include<stdlib.h>

/*
2017��4��5��17:23:22
C�����뺯�� 
*/ 

int main(void)
{
	
	int count = 0;
	printf("������༶����:");
	scanf("%d",&count);   //&��ȡ��ַ 
	printf("�༶����:%d\n",count); 
	
	char aArray[4];    
	printf("������༶����:"); 
	scanf("%s",&aArray);    //C�����ǲ�����±�Խ���,��ʹ���볬��4���ַ�,Ҳ�ܼ������,�� ��浽��һ����ַȥ 
	printf("�༶����:%s\n",aArray);
	
	//�������İ༶����̫��,��Ὣ�����count�ĵ�ַ���ǵ�,��count��ֵҲ�ᷢ���仯. 
	printf("�༶����:%d,�༶����:%s\n",count,aArray);
	
	printf("count�ĵ�ַ�� %d \n",&count);    //����ʱcount�ĵ�ַ��6487628     aArray�ĵ�ַ�� 6487616
	printf("aArray�ĵ�ַ�� %d \n",&aArray);
	
    system("pause");
    return 0;
}

