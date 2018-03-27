#include <jni.h>
#include <opencv2/opencv.hpp>
#include <bits/stdc++.h>
#define  LOG_TAG    "JNI_PART"

using namespace cv;
using namespace std;

extern "C" {
jstring Java_com_ntanougat_arcamera_ui_activity_ShotActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */)
{
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
void Java_com_ntanougat_arcamera_ui_activity_ShotActivity_nativeProcessFrame(JNIEnv *env,
                                                                        jobject instance,
                                                                        jlong addrGray,
                                                                        jlong addrRgba) {

    Mat &mGr = *(Mat *) addrGray;
    Mat &mRgb = *(Mat *) addrRgba;
    vector<KeyPoint> v;

/*    LOGD("%d image size %d",mGr.rows,mGr.cols);
    resize(mGr,mGr,Size(mGr.cols*480/mGr.rows,480));
    LOGD("%d image new-size %d",mGr.rows,mGr.cols);*/

    Ptr <FeatureDetector> detector = FastFeatureDetector::create(50);
    detector->detect(mGr, v);
    for (unsigned int i = 0; i < v.size(); i++) {
        const KeyPoint &kp = v[i];
        circle(mRgb, Point(kp.pt.x, kp.pt.y), 10, Scalar(0, 255, 255, 255));
    }

}
}