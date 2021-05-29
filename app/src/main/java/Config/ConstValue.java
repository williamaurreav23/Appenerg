package Config;

import java.util.Locale;


public class ConstValue {

    // note Please do not include "/" Slash at end of url
    public static String BASE_URL = "http://68.183.126.140";  // Replace this URL with YOUR Backend URL

    public static String LOGIN_URL = BASE_URL + "/index.php/api/login";
    public static String EMPLOYEE_PROFILE_URL = BASE_URL + "/index.php/api/get_employee_profile";
    public static String EMPLOYEE_PLANO_URL = BASE_URL + "/index.php/api/get_employee_plano";
    public static String EXAMS_URL = BASE_URL + "/index.php/api/get_exams";
    public static String PLANOS_URL = BASE_URL + "/index.php/api/get_planos";
    public static String RESULTS_URL = BASE_URL + "/index.php/api/get_results";
    public static String EVENT_URL = BASE_URL + "/index.php/api/get_school_event";
    public static String POSTES_URL = BASE_URL + "/index.php/api/get_profile_postes";
    public static String PREDIOS_URL = BASE_URL + "/index.php/api/get_profile_predios";
    public static String TOP_STUDENT_URL = BASE_URL + "/index.php/api/get_top_student";
    public static String HOLIDAY_URL = BASE_URL + "/index.php/api/get_holidays";
    public static String NOTICEBOARD_URL = BASE_URL + "/index.php/api/get_school_noticeboard";
    public static String GROWTH_URL = BASE_URL + "/index.php/api/get_student_growth";
    public static String RESULTS_REPORT_URL = BASE_URL + "/index.php/api/get_result_report";
    public static String ENQUIRY_URL = BASE_URL + "/index.php/api/get_enquiry";
    public static String SEND_ENQUIRY_URL = BASE_URL + "/index.php/api/send_enquiry";
    public static String FCM_REGISTER_URL = BASE_URL + "/index.php/api/register_fcm";
    public static String SCHOOL_PROFILE_URL = BASE_URL + "/index.php/api/get_school_profile";
    public static String TIME_TABLE_URL = BASE_URL + "/index.php/api/timetable";
    public static String GET_SUBJECT_URL = BASE_URL + "/index.php/api/get_subject_list";
    public static String GET_QUESTION_URL = BASE_URL + "/index.php/api/get_question_by_subject";
    public static String SET_RESULT_URL = BASE_URL + "/index.php/api/set_quiz_result";
    public static String GET_QUIZ_RESULT_URL = BASE_URL + "/index.php/api/get_quiz_report";
    public static String GET_FEES_URL = BASE_URL + "/index.php/api/list_student_fees_by_student";
    public static String GET_BOOK_URL = BASE_URL + "/index.php/api/get_book_by_standard";
    public static String IMG_BOOK_URL = BASE_URL + "/uploads/book_image/";
    public static String PDF_BOOK_URL = BASE_URL + "/uploads/book_pdf/";
    public static String GET_NOTIFICATION_URL = BASE_URL + "/index.php/api/notification_list";
    public static String IMG_NOTIFICATION_URL = BASE_URL + "/uploads/notification/";

    public static String PREF_NAME = "energizando.pref";
    public static String COMMON_KEY = "employee_id";

    // global topic to receive app wide push notifications
    // Not this topic must be same as specified in backend application/config/config.php file
    public static final String TOPIC_GLOBAL = "energizando";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    public static String GCM_SENDER_ID = "881835876590";

    //Sun,Mon,Tue,Wed,Thu,Fri,Sat
    public static final String HOLIDAY_START_DAY = "Mon";
    public static final String HOLIDAY_END_DAY = "Sun";

    public static final Locale LOCALE = Locale.US;
    public static final String CURRENCY = "Rs";

    public static enum getLanguageList {
        English("en");


        private String value;

        getLanguageList(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

}
