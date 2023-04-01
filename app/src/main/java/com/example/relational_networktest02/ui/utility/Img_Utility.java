package com.example.relational_networktest02.ui.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 使用该工具库前先将上下文传入
 */
public class Img_Utility {
    static Context context;
    public Img_Utility(Context context) {
        this.context=context;
    }
    // 启动相册
    public static void pickImageFromGallery(int REQUEST_PICK_IMAGE) {
        if (context instanceof Activity) {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            ((Activity) context).startActivityForResult(intent, REQUEST_PICK_IMAGE);
        } else {
            // 在这里处理错误，因为 context 不是 Activity 类的实例。
        }
    }
    /**
     * 打开指定路径的图片
     *
     * @param path
     * @return
     */
    public static Bitmap openFile(String path) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            //暂时不需要保存到指定位置，一般是固定头像用处，但这个项目采用了将图片路径获取后保存到数据库，
            // 再从数据库中获取路径。
            //            saveImage(bitmap, "abc");
//            也不需要裁剪，所以我们直接返回bitmap；

//            Head_address.setImageBitmap(ImageCrop(bitmap, true));
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存图片
     *
     * @param bmp
     * @param string
     */
    public static void saveImage(Bitmap bmp, String string) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "personal_bitmap");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = string + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪为正方形
     *
     * @param bitmap
     * @param isRecycled
     * @return
     */
    public static Bitmap ImageCrop(Bitmap bitmap, boolean isRecycled) {

        if (bitmap == null) {
            return null;
        }

        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = Math.min(w, h);// 裁切后所取的正方形区域边长

        int retX = w > h ? (w - h) / 2 : 0;// 基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;

        Bitmap bmp = Bitmap.createBitmap(bitmap, retX, retY, wh, wh, null,
                false);
        if (isRecycled && bitmap != null && !bitmap.equals(bmp)
                && !bitmap.isRecycled()) {
            bitmap.recycle();
        }

        return bmp;
    }
    // 根据Uri获取真实的图片路径
    public static String getRealPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
}
