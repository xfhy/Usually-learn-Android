LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := call_back
LOCAL_SRC_FILES := call_back.c

LOCAL_LDLIBS += -llog    #需要在C语言中打印log日志的话,需要在这里写一句

include $(BUILD_SHARED_LIBRARY)
