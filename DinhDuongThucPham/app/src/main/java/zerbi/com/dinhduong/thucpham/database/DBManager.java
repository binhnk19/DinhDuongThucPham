package zerbi.com.dinhduong.thucpham.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by binhnk on 6/14/2017.
 */

public enum  DBManager {

    INSTANCE;
    public static final String TABLE_NAME = "TABLE_FOOD";

    public static final String APP_UUID = "APP_UUID";
    public static final String APP_PACKAGE = "APP_PACKAGE";
    public static final String APP_NAME = "APP_NAME";
    public static final String APP_ACTIVITIES = "APP_ACTIVITIES";
    public static final String APP_PERMISSIONS = "APP_PERMISSIONS";
    public static final String APP_ICON_URI = "APP_ICON_URI";

    public static String DB_PATH = "";
    public static String DB_NAME = "AppInformation.db";
    public static final int DB_VERSION = 1;
    private DatabaseHelper databaseHelper;

    public void addAppInforToDb(AppObject object) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(APP_UUID, UUID.randomUUID().toString());
        values.put(APP_PACKAGE, object.getPkgName());
        values.put(APP_NAME, object.getAppName());
        values.put(APP_ACTIVITIES, object.getAppActivities());
        values.put(APP_PERMISSIONS, object.getAppPermissions());
        values.put(APP_ICON_URI, object.getIconLauncher());
        db.insert(TABLE_NAME, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int getDBCount() {
        int count = 0;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.getCount() > 0) {
            count = cursor.getCount();
        }
        cursor.close();
        return count;
    }

    public void clearDB() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public void removeApp(String pkgName, Context context) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_NAME, APP_PACKAGE + "='" + pkgName + "'", null);
        context.sendBroadcast(new Intent("com.reload.app"));
    }

    public ArrayList<AppObject> getAppList() {
        ArrayList<AppObject> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                AppObject object = new AppObject(cursor);
                list.add(object);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ArrayList<AppObject> getAdsAppList() {
        ArrayList<AppObject> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(3).contains("com.google.android.gms.ads")
                        || cursor.getString(3).contains("com.facebook.ads")
                        || cursor.getString(3).contains("com.startapp.android.publish")
                        || cursor.getString(3).contains("com.ironsource.mobilcore")
                        || cursor.getString(3).contains("com.appnext.ads")
                        || cursor.getString(3).contains("com.purplebrain.adbuddiz")
                        || cursor.getString(3).contains("com.unity3d.ads")) {
                    AppObject object = new AppObject(cursor);
                    list.add(object);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public int getAdsAppCount() {
        ArrayList<AppObject> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(3).contains("com.google.android.gms.ads")
                        || cursor.getString(3).contains("com.facebook.ads")) {
                    AppObject object = new AppObject(cursor);
                    list.add(object);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list.size();
    }

    public int getSumPermissionAppCount() {
        ArrayList<AppObject> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(4).contains("READ_CALENDAR")
                        || cursor.getString(4).contains("WRITE_CALENDAR")
                        || cursor.getString(4).contains("CAMERA")
                        || cursor.getString(4).contains("READ_CONTACTS")
                        || cursor.getString(4).contains("WRITE_CONTACTS")
                        || cursor.getString(4).contains("GET_ACCOUNTS")
                        || cursor.getString(4).contains("ACCESS_FINE_LOCATION")
                        || cursor.getString(4).contains("ACCESS_COARSE_LOCATION")
                        || cursor.getString(4).contains("RECORD_AUDIO")
                        || cursor.getString(4).contains("READ_PHONE_STATE")
                        || cursor.getString(4).contains("CALL_PHONE")
                        || cursor.getString(4).contains("READ_CALL_LOG")
                        || cursor.getString(4).contains("WRITE_CALL_LOG")
                        || cursor.getString(4).contains("ADD_VOICEMAIL")
                        || cursor.getString(4).contains("USE_SIP")
                        || cursor.getString(4).contains("PROCESS_OUTGOING_CALLS")
                        || cursor.getString(4).contains("BODY_SENSORS")
                        || cursor.getString(4).contains("SEND_SMS")
                        || cursor.getString(4).contains("RECEIVE_SMS")
                        || cursor.getString(4).contains("READ_SMS")
                        || cursor.getString(4).contains("RECEIVE_WAP_PUSH")
                        || cursor.getString(4).contains("RECEIVE_MMS")
                        || cursor.getString(4).contains("READ_EXTERNAL_STORAGE")
                        || cursor.getString(4).contains("WRITE_EXTERNAL_STORAGE")) {
                    AppObject object = new AppObject(cursor);
                    list.add(object);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list.size();
    }

    public ArrayList<AppObject> getPermissionAppList() {
        ArrayList<AppObject> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(4).contains("READ_CALENDAR")
                        || cursor.getString(4).contains("WRITE_CALENDAR")
                        || cursor.getString(4).contains("CAMERA")
                        || cursor.getString(4).contains("READ_CONTACTS")
                        || cursor.getString(4).contains("WRITE_CONTACTS")
                        || cursor.getString(4).contains("GET_ACCOUNTS")
                        || cursor.getString(4).contains("ACCESS_FINE_LOCATION")
                        || cursor.getString(4).contains("ACCESS_COARSE_LOCATION")
                        || cursor.getString(4).contains("RECORD_AUDIO")
                        || cursor.getString(4).contains("READ_PHONE_STATE")
                        || cursor.getString(4).contains("CALL_PHONE")
                        || cursor.getString(4).contains("READ_CALL_LOG")
                        || cursor.getString(4).contains("WRITE_CALL_LOG")
                        || cursor.getString(4).contains("ADD_VOICEMAIL")
                        || cursor.getString(4).contains("USE_SIP")
                        || cursor.getString(4).contains("PROCESS_OUTGOING_CALLS")
                        || cursor.getString(4).contains("BODY_SENSORS")
                        || cursor.getString(4).contains("SEND_SMS")
                        || cursor.getString(4).contains("RECEIVE_SMS")
                        || cursor.getString(4).contains("READ_SMS")
                        || cursor.getString(4).contains("RECEIVE_WAP_PUSH")
                        || cursor.getString(4).contains("RECEIVE_MMS")
                        || cursor.getString(4).contains("READ_EXTERNAL_STORAGE")
                        || cursor.getString(4).contains("WRITE_EXTERNAL_STORAGE")) {
                    AppObject object = new AppObject(cursor);
                    list.add(object);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * khởi tạo database
     *
     * @param context
     */
    public static void init(Context context) {
        if (INSTANCE.databaseHelper == null) {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            File file = new File(DB_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            INSTANCE.databaseHelper = new DatabaseHelper(context);
            try {
                INSTANCE.databaseHelper.createDataBase();
                INSTANCE.databaseHelper.openDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        private SQLiteDatabase sqLiteDatabase;

        public DatabaseHelper(Context context) {
            super(context, DB_PATH + DB_NAME, null, DB_VERSION);
        }

        public void createDataBase() throws IOException {
            this.getReadableDatabase();
        }

        public void openDataBase() throws SQLException {
            close();
            // Open the database
            String myPath = DB_PATH + DB_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                        + APP_UUID + " TEXT PRIMARY KEY, "
                        + APP_PACKAGE + " TEXT, "
                        + APP_NAME + " TEXT, "
                        + APP_ACTIVITIES + " TEXT, "
                        + APP_PERMISSIONS + " TEXT, "
                        + APP_ICON_URI + " TEXT)");
            } catch (android.database.SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

        @Override
        public synchronized void close() {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
            super.close();
        }
    }
}
