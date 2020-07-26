package com.example.umeed.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/**
 * User: Aman
 * Date: 29-12-2019
 * Time: 05:34 PM
 */
public class PrefManager {
    public static final String WORKER_LOCATION_ID = "worker_location_id";
    public static final String IS_WORKER_LOCATION_SENT = "is_worker_location_sent";
    public static final String ADDRESS_ID = "address_id";
    public static final String WORKER_WALLET_SUBCAT = "worker_wallet_subcat";
    private static final String DEVICE_TOKEN = "device_token";
    private static final String IS_LOGGED_IN = "is_logged_in";
    private static final String IS_LOCATION_SCREEN_DONE = "is_location_screen_done";
    private static final String PREFS_NAME = "A-Helper";
    private static final String USER_ID = "user_id";
    private static final String LOCATION_ID = "location_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL_ID = "email_id";
    private static final String MOBILE_NUMBER = "mobile_number";
    private static final String USER_ALREADY_REGISTERED = "user_already_registered";
    private static final String VENDOR_ALREADY_REGISTERED = "vendor_already_registered";
    private static final String WORKER_ID = "worker_id";
    public static final String WORKER_FIRST_NAME = "worker_first_name";
    public static final String WORKER_LAST_NAME = "worker_last_name";
    public static final String WORKER_CITY_NAME = "worker_city_name";
    public static final String WORKER_IMAGE = "worker_image";
    public static final String IMAGE = "image";
    public static final String WORKER_DOB = "worker_dob";
    private static final String SUBCAT_LIST = "subcat_list";



    private static final String AUTH_TOKEN="auth-token";
    //    private static final String MOBILE_NUMBER = "user_id";
    private static PrefManager instance;
    private SharedPreferences preferences;

    private PrefManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void initInstance(Context context) {
        instance = new PrefManager(context);
    }

    public static PrefManager getInstance() {
        return instance;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    private void setDeviceTokenIfNotExist() {
        if (preferences.getString(DEVICE_TOKEN, null) == null) {
            preferences.edit().putString(DEVICE_TOKEN, UUID.randomUUID().toString()).commit();
        }
    }

    public String getDeviceToken() {
        setDeviceTokenIfNotExist();
        return preferences.getString(DEVICE_TOKEN, "");
    }
    public void setAuthToken(String token){
        preferences.edit().putString(AUTH_TOKEN,token).apply();
    }
    public String getAuthToken() {
        return preferences.getString(AUTH_TOKEN,"");
    }
    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        preferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply();
    }

//    public boolean isLocationScreenDone() {
//        return preferences.getBoolean(IS_LOCATION_SCREEN_DONE, false);
//    }

    public void setLocationScreenDone(boolean isLocationScreenDone) {
        preferences.edit().putBoolean(IS_LOCATION_SCREEN_DONE, isLocationScreenDone).apply();
    }

    public int getUserId() {
        return preferences.getInt(USER_ID, 0);
    }

    public void setUserId(int userId) {
        preferences.edit().putInt(USER_ID, userId).apply();
    }

    public int getLocationId() {
        return preferences.getInt(LOCATION_ID, 0);
    }

    public void setLocationId(int locationId) {
        preferences.edit().putInt(LOCATION_ID, locationId).apply();
    }

    public String getMobileNumber() {
        return preferences.getString(MOBILE_NUMBER, "");
    }

    public void setMobileNumber(String mobileNumber) {
        preferences.edit().putString(MOBILE_NUMBER, mobileNumber).apply();
    }

    public String getFirstName() {
        return preferences.getString(FIRST_NAME, "");
    }

    public void setFirstName(String firstName) {
        preferences.edit().putString(FIRST_NAME, firstName).apply();
    }

    public String getLastName() {
        return preferences.getString(LAST_NAME, "");
    }

    public void setLastName(String lastName) {
        preferences.edit().putString(LAST_NAME, lastName).apply();
    }

    public String getEmail() {
        return preferences.getString(EMAIL_ID, "");
    }

    public void setEmail(String email) {
        preferences.edit().putString(EMAIL_ID, email).apply();
    }

    public String getWorkerCityName() {
        return preferences.getString(WORKER_CITY_NAME, "");
    }

    public void setWorkerCityName(String workerCityName) {
        preferences.edit().putString(WORKER_CITY_NAME, workerCityName).apply();
    }

    public boolean isUserAlreadyRegistered() {
        return preferences.getBoolean(USER_ALREADY_REGISTERED, false);
    }

    public void setUserAlreadyRegistered(boolean isAlreadyRegistered) {
        preferences.edit().putBoolean(USER_ALREADY_REGISTERED, isAlreadyRegistered).apply();
    }

    public boolean isWorkerAlreadyRegistered() {
        return preferences.getBoolean(VENDOR_ALREADY_REGISTERED, false);
    }

    public void setWorkerAlreadyRegistered(boolean isAlreadyRegistered) {
        preferences.edit().putBoolean(VENDOR_ALREADY_REGISTERED, isAlreadyRegistered).apply();
    }

    public int getWorkerId() {
        return preferences.getInt(WORKER_ID, 0);
    }

    public void setWorkerId(int workerId) {
        preferences.edit().putInt(WORKER_ID, workerId).apply();
    }

    public void setIsWorkerLocationSent(boolean isWorkerLocationSent) {
        preferences.edit().putBoolean(IS_WORKER_LOCATION_SENT, isWorkerLocationSent).apply();
    }

    public boolean isWorkerLocationSent() {
        return preferences.getBoolean(IS_WORKER_LOCATION_SENT, false);
    }

    public int getWorkerLocationId() {
        return preferences.getInt(WORKER_LOCATION_ID, 0);
    }

    public void setWorkerLocationId(int workerLocationId) {
        preferences.edit().putInt(WORKER_LOCATION_ID, workerLocationId).apply();
    }

//    public int getAddressId() {
//        return preferences.getInt(ADDRESS_ID, 0);
//    }

//    public void setAddressId(int addressId) {
//        preferences.edit().putInt(ADDRESS_ID, addressId).apply();
//    }

    public int getWalletSubcat() {
        return preferences.getInt(WORKER_WALLET_SUBCAT, 0);
    }

    public void setWalletSubcat(int ww_subcat_id) {
        preferences.edit().putInt(WORKER_WALLET_SUBCAT, ww_subcat_id).apply();
    }

    public String getWorkerFirstName() {
        return preferences.getString(WORKER_FIRST_NAME, "");
    }

    public void setWorkerFirstName(String firstName) {
        preferences.edit().putString(WORKER_FIRST_NAME, firstName).apply();
    }

    public String getWorkerLastName() {
        return preferences.getString(WORKER_LAST_NAME, "");
    }

    public void setWorkerLastName(String lastName) {
        preferences.edit().putString(WORKER_LAST_NAME, lastName).apply();
    }

    public String getWorkerImage() {
        return preferences.getString(WORKER_IMAGE, "");
    }

    public void setWorkerImage(String workerImage) {
        preferences.edit().putString(WORKER_IMAGE, workerImage).apply();
    }

    public String getWorkerDob() {
        return preferences.getString(WORKER_DOB, "");
    }

    public void setWorkerDob(String workerDob) {
        preferences.edit().putString(WORKER_DOB, workerDob).apply();
    }

    public String getImage() {
        return preferences.getString(IMAGE, "");
    }

    public void setImage(String Image) {
        preferences.edit().putString(IMAGE, Image).apply();
    }

    public String getSubcatList() {
        return preferences.getString(SUBCAT_LIST, "");
    }

    public void setSubcatList(String subcat) {
        preferences.edit().putString(SUBCAT_LIST, subcat).apply();
    }
}
