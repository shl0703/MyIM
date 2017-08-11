package com.example.shl.mylib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.shl.mylib.R;
import com.example.shl.mylib.view.LoadingDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    private static LoadingDialog loadingDialog;

    public static void showLoadingDialog(Activity act) {
        loadingDialog = new LoadingDialog(act, R.style.MyDialog);
        loadingDialog.show();
    }

    public static void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public static void showLoadingDialog(Fragment fragment) {
        showLoadingDialog(fragment.getActivity());
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    /**
     * 按照8,888.00的格式格式化价格
     */
    public static String parsePrice(double price) {
        String str = null;
        DecimalFormat format = new DecimalFormat("##,###,###,###,###,###,###.00");
        return format.format(price);
    }

    /**
     * 按照8,888.00的格式格式化价格
     */
    public static String parseWeight(double weight) {
        String str = null;
        DecimalFormat format = new DecimalFormat("##,###,###,###,###,###,###.0");
        return format.format(weight);
    }

    /**
     * <p>
     * 将录像时长转化为列表显示模式，比如"01:01:30"
     * </p>
     *
     * @param diffSeconds
     * @return
     * @author hanlifeng 2014-6-16 下午4:01:04
     */
    public static String convToUIDuration(int diffSeconds) {
        int min = diffSeconds / 60;
        String minStr = "";
        int sec = diffSeconds % 60;
        String secStr = "";
        String hStr = "";

        if (min >= 59) {
            int hour = min / 60;
            int temp = min % 60;
            if (hour < 10) {
                if (hour > 0) {
                    hStr = "0" + hour;
                } else {
                    hStr = "00";
                }
            } else {
                hStr = "" + hour;
            }
            if (temp < 10) {
                if (temp > 0) {
                    minStr = "0" + temp;
                } else {
                    minStr = "00";
                }
            } else {
                minStr = "" + temp;
            }
            if (sec < 10) {
                if (sec > 0) {
                    secStr = "0" + sec;
                } else {
                    secStr = "00";
                }
            } else {
                secStr = "" + sec;
            }
            return hStr + ":" + minStr + ":" + secStr;
        } else {
            hStr = "00";
            if (min < 10) {
                if (min > 0) {
                    minStr = "0" + min;
                } else {
                    minStr = "00";
                }
            } else {
                minStr = "" + min;
            }
            if (sec < 10) {
                if (sec > 0) {
                    secStr = "0" + sec;
                } else {
                    secStr = "00";
                }
            } else {
                secStr = "" + sec;
            }
            return hStr + ":" + minStr + ":" + secStr;
        }
    }

    /**
     * <p>
     * 将时间转化为月和日格式，比如 6月17号
     * </p>
     *
     * @param queryDate
     * @return
     * @author hanlifeng 2014-6-17 下午1:34:23
     */
    public static String formatDate(Date queryDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(queryDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        return year + "-" + month + "-" + day;
    }

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

//    /**
//     * 创建组件倒影的方法
//     */
//    public static Bitmap createReflection(View view, int viewWidth, int viewHeight) {
//        int shadowHeight = AutoUtils.getPercentWidthSize(96);
//        Bitmap original = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
//        view.draw(new Canvas(original));
//        original = Bitmap.createBitmap(original, 0, viewHeight - shadowHeight, viewWidth, shadowHeight);
////        final int reflectionHeight = (int) (original.getHeight() * percentage);
//        Bitmap bitmapWithReflection = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmapWithReflection);
//
////        // original image
////        canvas.drawBitmap(original, 0, 0, null);
//        // gap drawing
////        final Paint transparentPaint = new Paint();
////        transparentPaint.setARGB(0, 255, 255, 255);
////        canvas.drawRect(0, original.getHeight(), original.getWidth(), original.getHeight() + gap, transparentPaint);
//        // reflection
//        final Matrix matrix = new Matrix();
//        matrix.preScale(1, -1);
//        canvas.drawBitmap(Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, false), 0, 0, null);
//        // reflection shading
//        final Paint fadePaint = new Paint();
//        fadePaint.setShader(new LinearGradient(0, original.getHeight() / 3, 0, original.getHeight(), 0x50ffffff, 0x00ffffff, Shader.TileMode.CLAMP));
//        fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        canvas.drawRect(0, 0, original.getWidth(), bitmapWithReflection.getHeight(), fadePaint);
//
//        original.recycle();
//        return bitmapWithReflection;
//    }

    /**
     * 将Drawable转换为Bitmap的方法
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static String getDeviceMacAddress() {
        return getMacAddress();
    }

    private static String getMacAddress() {
        String strMacAddr = "";
        byte[] b;
        try {
            NetworkInterface NIC = NetworkInterface.getByName("eth0");
            if (NIC == null) {
                NIC = NetworkInterface.getByName("wlan0");
            }
            if (NIC == null) {
                return "";
            }
            b = NIC.getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return strMacAddr;
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            LogUtils.e("WifiPreference" + ex.toString());
        }
        return null;
    }

    public static void sendKeyEvent(final Context context, final int KeyCode) {
        new Thread() {     //不可在主线程中调用
            public void run() {
                try {
                    boolean inFront = !CommonUtils.isApplicationBroughtToBackground(context);
                    if (inFront) {
                        Instrumentation inst = new Instrumentation();
                        inst.sendKeyDownUpSync(KeyCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    /**
     * 判断是否安装
     */
    public static boolean isAppInstalled(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断设备是否支持多点触控
     *
     * @param context
     * @return
     */
    public static boolean isSupportMultiTouch(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean isSupportMultiTouch = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN);
        return isSupportMultiTouch;
    }

//    // 检查是否有新版本
//    public static boolean checkRemoteIsNewVersion(Context context, String remoteVersionName) {
//        String currentVersionName = getVersionName(context);
//        return !currentVersionName.equalsIgnoreCase(remoteVersionName);
//    }

    // 检查是否有新版本
    public static boolean checkRemoteIsNewVersion(Context context, int remoteVersionCode) {
        int currentVersionCode = getVersionCode(context);
        return remoteVersionCode > currentVersionCode ? true : false;
    }

    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            String currentVersionName = pm.getPackageInfo(context.getPackageName(), 0).versionName;
            return currentVersionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            int versionCode = pm.getPackageInfo(context.getPackageName(), 0).versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Bitmap takeScreenShot(Activity act) {
        Bitmap screenCaptBmp = null;
        try {
            View decorView = act.getWindow().getDecorView();
            Bitmap screenBm = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(),
                    Bitmap.Config.RGB_565);
            Canvas c = new Canvas(screenBm);
            decorView.draw(c);
            Bitmap thumb = ThumbnailUtils.extractThumbnail(screenBm, screenBm.getWidth() / 4,
                    screenBm.getHeight() / 4);
            screenBm.recycle();
            decorView.destroyDrawingCache();
            screenCaptBmp = Blur.doBlur(thumb, 32, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            screenCaptBmp = null;
        }
        return screenCaptBmp;
    }

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String List2String(List list) throws IOException {
        // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(list);
        // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
        String listString = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        // 关闭objectOutputStream
        objectOutputStream.close();
        return listString;
    }

    @SuppressWarnings("unchecked")
    public static List String2List(String ListString) throws StreamCorruptedException, IOException,
            ClassNotFoundException {
        byte[] mobileBytes = Base64.decode(ListString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        List list = (List) objectInputStream.readObject();
        objectInputStream.close();
        return list;
    }

    public static String Drawable2String(Drawable drawable) throws IOException {
        Bitmap bitmap = drawableToBitmap(drawable);
//        BitmapDrawable bd = (BitmapDrawable) drawable;
//        Bitmap bitmap = bd.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
        String imageBase64 = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
        return imageBase64;
    }

    public static Drawable loadDrawable(String temp) {
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        return Drawable.createFromStream(bais, "");
    }

    // 验证手机号是否正确ֻ
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static void hideSoftInput(Activity act, View view) {
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            //强制隐藏键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
