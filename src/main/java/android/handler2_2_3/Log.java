package android.handler2_2_3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author itz.
 * @date 2022/11/9 17:50
 * @desc
 */
public class Log {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String getDateStr() {
        return DATE_FORMAT.format(new Date());
    }

    public static void e(String tag, String msg) {
        System.err.println(getDateStr() + " " + tag + ":" + msg);
    }

    public static void d(String tag, String msg) {
        System.out.println(getDateStr() + " " + tag + ":" + msg);
    }

    public static void w(String tag, String msg) {
        System.out.println(getDateStr() + " " + tag + ":" + msg);
    }

    public static void w(String tag, String msg, Throwable e) {
        System.out.println(getDateStr() + " " + tag + ":" + msg + ":e=$e");
    }

    public static void wtf(String tag, String msg, Throwable e) {
        System.out.println(getDateStr() + " " + tag + ":" + msg + ":e=$e");
    }

    public static void v(String tag, String msg) {
        System.out.println(getDateStr() + " " + tag + ":" + msg);
    }
}
