#include<jni.h>
#include<string.h>

jstring Java_com_mhci4_mhci4_MainActivity_printMessage(JNIEnv* env, jobject obj)
{
    return (*env)->NewStringUTF(env,"The quick brown fox jump over the lazy dog.");
}