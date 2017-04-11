#include <jni.h>
#include <stdlib.h>
#include <stdio.h>

#include <android/log.h>
	#define LOG_TAG "System.out"
	#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
int ppid;
JNIEXPORT void JNICALL Java_com_itheima_cforkdemo_MainActivity_cfork
  (JNIEnv * env, jobject obj){
	int pid = fork();
	//fork�ɹ��ķֲ��һ���ӽ��� �᷵�ص�ǰ���̵�id ����ֻ������������fork�ɹ�
	//���ӽ���������fork �᷵��0 ���ǲ����ٷֲ���µĽ���
	//fork�ķ���ֵ��������  >0  == 0 <0
	FILE* file;
	if(pid>0){
		LOGD("pid = %d",pid);

	}else if(pid == 0){
		//�õ������̵Ľ��̱��

		LOGD("pid == 0");
		while(1){
			ppid = getppid();
			//��������̵Ľ��̱��Ϊ1 ˵�������̱�ɱ����
			if(ppid == 1){
				LOGD("ppid =%d",ppid);
				file = fopen("/data/data/com.itheima.cforkdemo","r");
				if(file == NULL){
					//����ҳ ����am����
					execlp("am", "am", "start", "--user","0","-a", "android.intent.action.VIEW", "-d", "http://www.baidu.com", (char *) NULL);
				}else{
					execlp("am", "am", "start", "--user","0", "-n" , "com.itheima.cforkdemo/com.itheima.cforkdemo.MainActivity",(char *) NULL);

				}
				//break;
			}
					LOGD("sub process is running");
					sleep(2);
		}
	}else{
		LOGD("pid<0 ");
	}

}
