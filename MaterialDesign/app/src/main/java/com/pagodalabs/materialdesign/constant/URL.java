package com.pagodalabs.materialdesign.constant;

/**
 * Created by Ajit Kumar Baral on 6/12/2015.
 */
public class URL {
    public static final String MAIN_URL="http://zyamiapi-ajitkbaral.rhcloud.com/";
    //public static final String MAIN_URL="http://192.168.56.1:8080/Zyami/";
    //public static final String MAIN_URL="http://192.168.1.100:8080/Zyami/";
    //public static final String MAIN_URL="http://192.168.1.105:8080/Zyami/";
    public static final String API_URL = MAIN_URL + "api/";
    public static final String CATEGORIES_API_URL = API_URL + "categories/";
    public static final String CATEGORIES_ALL_URL = CATEGORIES_API_URL + "all/";
    public static final String PROFESSIONALS_URL = API_URL + "professionals/";
    public static final String USERS_API_URL = API_URL + "users/";
    public static final String REGISTER_URL = USERS_API_URL+"register/";
    public static final String PROFESSIONALS_CATEGORY_URL = PROFESSIONALS_URL + "category/";
    public static final String LOGIN_URL = USERS_API_URL+"login/";
    public static final String JOB_URL = API_URL+"job/";
    public static final String JOB_URL_PUBLISH = JOB_URL+"publish/";
    public static final String JOB_URL_FEED = JOB_URL+"feed/";
}
