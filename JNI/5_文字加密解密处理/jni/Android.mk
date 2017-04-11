LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := encode
LOCAL_SRC_FILES := encode.c

LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)
