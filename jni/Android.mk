LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := myndklib
LOCAL_SRC_FILES := myndk.c

include $(BUILD_SHARED_LIBRARY)