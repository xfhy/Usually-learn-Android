LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := passdata
LOCAL_SRC_FILES := passdata.c

include $(BUILD_SHARED_LIBRARY)
