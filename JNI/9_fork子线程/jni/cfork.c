#include <jni.h>
#include <stdio.h>
#include <android/log.h>
#define LOG_TAG "xfhy"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

int ppid;   //parent pid父进程的pid

JNIEXPORT void JNICALL Java_com_xfhy_jnicfork_MainActivity_cfork(JNIEnv *env,
		jobject obj) {

	//fork()  有3种可能的返回值<0   ==0   >0

	FILE* file;

	int pid = fork();
	if (pid > 0) {   //父进程   在父进程中，fork返回新创建子进程的进程ID；
		//在父进程中执行一些耗时操作会阻塞java中的主线程(UI线程)
		LOGD("pid == %d ", pid);
	} else if (pid == 0) {   //子进程   在子进程中，fork返回0；
		//这是一个新的进程,和java代码没关系,这是新建的进程,不会阻塞主线程
		//如果该Android程序结束(主进程结束了),但是子进程还没有执行完,那么子进程会接着继续执行
		//linux中杀死进程:kill pid
		/*while (1) {
			ppid = getppid();   //获取父进程的编号
			LOGD("ppid =%d", ppid);
			if (ppid == 1) {
				file = fopen("/data/data/com.xfhy.jnicfork", "r");
				if (file == NULL) {
					//打开网页 调用am命令
					execlp("am", "am", "start", "--user", "0", "-a",
							"android.intent.action.VIEW", "-d",
							"http://www.baidu.com", (char *) NULL);
				} else {
					execlp("am", "am", "start", "--user", "0", "-n",
							"com.xfhy.jnicfork/com.xfhy.jnicfork.MainActivity",
							(char *) NULL);

				}
				//break;
			}*/

			LOGD("我是子进程");
			sleep(1);
		}
		LOGD("pid == 0");
	} else {  //失败了   如果出现错误，fork返回一个负值；
		LOGD("pid < 0");
	}
}
