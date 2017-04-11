LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := passdata
LOCAL_SRC_FILES := passdata.c

LOCAL_LDLIBS += -llog  #引入log.so库

include $(BUILD_SHARED_LIBRARY)
