package util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import co.tysa.AttendenceActivity;
import co.tysa.BookActivity;
import co.tysa.ExamActivity;
import co.tysa.FeesActivity;
import co.tysa.GrowthActivity;
import co.tysa.HolidaysActivity;
import co.tysa.NewsActivity;
import co.tysa.NoticeActivity;
import co.tysa.NotificationActivity;
import co.tysa.ProfileActivity;
import co.tysa.Quiz_subjectActivity;
import co.tysa.ResultActivity;
import co.tysa.SchoolProfileActivity;
import co.tysa.PosteActivity;
import co.tysa.TimetableActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Config.ConstValue;


public class CommonClass {
    Activity activity;
    public SharedPreferences settings;

    public CommonClass(Activity activity) {
        this.activity = activity;
        settings = activity.getSharedPreferences(ConstValue.PREF_NAME, 0);
    }

    public void setSession(String key, String value) {
        settings.edit().putString(key, value).commit();
    }

    public String getSession(String key) {
        return settings.getString(key, "");
    }

    public boolean is_user_login() {
        String key = getSession(ConstValue.COMMON_KEY);
        if (key == null || key.equalsIgnoreCase("")) {
            return false;
        } else {
            return true;
        }
    }

    public void open_screen(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(activity, ProfileActivity.class);
                break;
            case 1:
                intent = new Intent(activity, AttendenceActivity.class);
                break;
            case 2:
                intent = new Intent(activity, ExamActivity.class);
                break;
            case 3:
                intent = new Intent(activity, ResultActivity.class);
                break;
            case 4:
                intent = new Intent(activity, PosteActivity.class);
                break;
            case 5:
                intent = new Intent(activity, GrowthActivity.class);
                break;
            case 6:
                intent = new Intent(activity, HolidaysActivity.class);
                break;
            case 7:
                intent = new Intent(activity, NewsActivity.class);
                break;
            case 8:
                intent = new Intent(activity, NoticeActivity.class);
                break;
            case 9:
                intent = new Intent(activity, SchoolProfileActivity.class);
                break;
            case 10:
                intent = new Intent(activity, TimetableActivity.class);
                break;
            case 11:
                intent = new Intent(activity, Quiz_subjectActivity.class);
                break;
            case 12:
                intent = new Intent(activity, FeesActivity.class);
                break;
            case 13:
                intent = new Intent(activity, BookActivity.class);
                break;
            case 14:
                intent = new Intent(activity, NotificationActivity.class);
                break;

        }
        if (intent != null) {
            activity.startActivity(intent);
        }
    }

    public static String changeDateTimeFormate(String convertDateTime, int convertType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", ConstValue.LOCALE);

        try {
            Date date = simpleDateFormat.parse(convertDateTime);
            switch (convertType) {
                case 1:
                    return new SimpleDateFormat("dd", ConstValue.LOCALE).format(date);
                case 2:
                    return new SimpleDateFormat("MM", ConstValue.LOCALE).format(date);
                case 3:
                    return new SimpleDateFormat("yyyy", ConstValue.LOCALE).format(date);
                case 4:
                    return new SimpleDateFormat("MMMM", ConstValue.LOCALE).format(date);
                case 5:
                    return new SimpleDateFormat("dd-MM-yyyy", ConstValue.LOCALE).format(date);
                case 6:
                    return new SimpleDateFormat("dd-MM-yyyy hh:mm a", ConstValue.LOCALE).format(date);
                default:
                    return convertDateTime;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return convertDateTime;
        }
    }

    public static String changeDateFormate(String convertDate, int convertType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", ConstValue.LOCALE);

        try {
            Date date = simpleDateFormat.parse(convertDate);
            switch (convertType) {
                case 1:
                    return new SimpleDateFormat("dd", ConstValue.LOCALE).format(date);
                case 2:
                    return new SimpleDateFormat("MM", ConstValue.LOCALE).format(date);
                case 3:
                    return new SimpleDateFormat("yyyy", ConstValue.LOCALE).format(date);
                case 4:
                    return new SimpleDateFormat("dd-MM-yyyy", ConstValue.LOCALE).format(date);
                case 5:
                    return new SimpleDateFormat("MMM", ConstValue.LOCALE).format(date);
                default:
                    return convertDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return convertDate;
        }
    }

    public static Date getTodayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", ConstValue.LOCALE);
        try {
            return format.parse(format.format(Calendar.getInstance(ConstValue.LOCALE).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convetDateToString(Date convetDate, int convertType) {
        switch (convertType) {
            case 1:
                return new SimpleDateFormat("MMMM", ConstValue.LOCALE).format(convetDate);
            case 2:
                return new SimpleDateFormat("MM", ConstValue.LOCALE).format(convetDate);
            case 3:
                return new SimpleDateFormat("EEE", ConstValue.LOCALE).format(convetDate);
            default:
                return "";
        }
    }

    public static Date convetStringToDate(String convetDate, int convertType) {
        SimpleDateFormat simpleDateFormat;
        try {
            switch (convertType) {
                case 1:
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", ConstValue.LOCALE);
                    return simpleDateFormat.parse(convetDate);
                default:
                    return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
