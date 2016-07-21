package wdy.com.activitydemo01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_GET_CAMERA_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //拨打电话
    public void phoneCall(){
        Uri uri = Uri.parse("tel:10086");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    //发送短信
    public void sendMsg(){
        // 给10086发送内容为“Hello”的短信
        Uri uri = Uri.parse("smsto:10086");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "Hello");
        startActivity(intent);
    }

    //发送彩信（相当于发送带附件的短信）
    public void sendExtMsg(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra("sms_body", "Hello");
        Uri uri = Uri.parse("content://media/external/images/media/23");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/png");
        startActivity(intent);
    }

    //打开浏览器
    public void openBrowser(){
        // 打开Google主页
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    //多媒体播放
    public void openVideo(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file:///sdcard/foo.mp3");
        intent.setDataAndType(uri, "audio/mp3");
        startActivity(intent);
    }

    //获取SD卡下所有音频文件,然后播放第一首=-=
    public void playMusic(){
        Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    //打开摄像头拍照
    public void openCammar(){
        // 打开拍照程序
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
        // 取出照片数据
        Bundle extras = intent.getExtras();
        Bitmap bitmap = (Bitmap) extras.get("data");
    }

    //调用系统相机应用程序，并存储拍下来的照片
    public void openCammar2(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       long time = Calendar.getInstance().getTimeInMillis();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                .getExternalStorageDirectory().getAbsolutePath()+"/tucue", time + ".jpg")));
        startActivityForResult(intent, ACTIVITY_GET_CAMERA_IMAGE);
    }

    //获取并剪切图片
    public void getClipImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", "true"); // 开启剪切
        intent.putExtra("aspectX", 1); // 剪切的宽高比为1：2
        intent.putExtra("aspectY", 2);
        intent.putExtra("outputX", 20); // 保存图片的宽和高
        intent.putExtra("outputY", 40);
        intent.putExtra("output", Uri.fromFile(new File("/mnt/sdcard/temp"))); // 保存路径
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        startActivityForResult(intent, 0);
    }

    public void getClipImage2(){
        // 剪切特定图片
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setClassName("com.android.camera", "com.android.camera.CropImage");
        intent.setData(Uri.fromFile(new File("/mnt/sdcard/temp")));
        intent.putExtra("outputX", 1); // 剪切的宽高比为1：2
        intent.putExtra("outputY", 2);
        intent.putExtra("aspectX", 20); // 保存图片的宽和高
        intent.putExtra("aspectY", 40);
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("output", Uri.parse("file:///mnt/sdcard/temp"));
        startActivityForResult(intent, 0);
    }

    //进入手机设置界面
    public void openSystemSetting(){
        // 进入无线网络设置界面（其它可以举一反三）
        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        startActivityForResult(intent, 0);
    }

    //安装apk
    public void installAPK(){
        Uri installUri = Uri.fromParts("package", "xxx", null);
        Intent returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
        startActivity(returnIt);
    }

    //卸载apk
    public void uninstallAPK(){
        Uri uri = Uri.fromParts("package", "包名", null);
        Intent it = new Intent(Intent.ACTION_DELETE, uri);
        startActivity(it);
    }

    /*
    public void openContact(){
        //16.进入联系人页面:
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(People.CONTENT_URI);
        startActivity(intent);

        //17.查看指定联系人:

        Uri personUri = ContentUris.withAppendedId(People.CONTENT_URI, info.id);//info.id联系人ID
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(personUri);
        startActivity(intent);
    }
    */



}
