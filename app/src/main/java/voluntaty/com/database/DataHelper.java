package voluntaty.com.database;
/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataHelper {
    private static final String DATABASE_NAME = "basaa942.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME9 = "login";
    private static final String TABLE_NAME10 = "email";
    private static final String TABLE_NAME11 = "password";
    private static final String TABLE_NAME15 = "url_http";

    private static final String TABLE_NAME26 = "idmessage";
    private static final String TABLE_NAME27 = "idmessageskype";

    private static final String TABLE_NAME28 = "idmessagetwo";
    private static final String TABLE_NAME29 = "idmessagesviber";

    private static final String TABLE_NAME31 = "serviceskype";
    private static final String TABLE_NAME32 = "serviceviber";

    private static final String TABLE_NAME33 = "fasebook";
    private static final String TABLE_NAME34 = "fasebookusers";

    private static final String TABLE_NAME35 = "servicefasebook";
    private static final String TABLE_NAME36 = "servicefasebookusers";

    private static final String TABLE_NAME37 = "vibernumbername";

    private static final String TABLE_NAME38 = "watshappid";
    private static final String TABLE_NAME39 = "watshappidmessage";
    private static final String TABLE_NAME40 = "watshappiduser";

    private static final String TABLE_NAME41 = "vibernumbernameto";


    private Context context;
    private SQLiteDatabase db;

    public DataHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
    }


    public long InsertLogin(String login){
        this.db.execSQL("DELETE from " + TABLE_NAME9 + ";");
        ContentValues cv = new ContentValues();
        cv.put("login",login);
        return this.db.insert(TABLE_NAME9, null, cv);
    }

    public long InsertEmail(String email){
        this.db.execSQL("DELETE from " + TABLE_NAME10 + ";");
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        return this.db.insert(TABLE_NAME10, null, cv);
    }

    public long InsertPassword(String password){
        this.db.execSQL("DELETE from " + TABLE_NAME11 + ";");
        ContentValues cv = new ContentValues();
        cv.put("password",password);
        return this.db.insert(TABLE_NAME11, null, cv);
    }

    public long InsertUrl_Http(String url_http){
        this.db.execSQL("DELETE from " + TABLE_NAME15 + ";");
        ContentValues cv = new ContentValues();
        cv.put("url_http", url_http);
        return this.db.insert(TABLE_NAME15, null, cv);
    }

    public long insertIdMessage(String idmessage){
        //   this.db.execSQL("DELETE from " + TABLE_NAME21 + ";");
        ContentValues cv = new ContentValues();
        cv.put("idmessage", idmessage);
        return this.db.insert(TABLE_NAME26, null, cv);
    }

    public long insertIdMessageSkype(String idmessage){
        //   this.db.execSQL("DELETE from " + TABLE_NAME21 + ";");
        ContentValues cv = new ContentValues();
        cv.put("idmessage", idmessage);
        return this.db.insert(TABLE_NAME27, null, cv);
    }

    public long insertIdMessageTwo(String idmessage){
        //   this.db.execSQL("DELETE from " + TABLE_NAME21 + ";");
        ContentValues cv = new ContentValues();
        cv.put("idmessage", idmessage);
        return this.db.insert(TABLE_NAME26, null, cv);
    }

    public long insertIdMessageTwoTwo(String idmessage){
        //   this.db.execSQL("DELETE from " + TABLE_NAME21 + ";");
        ContentValues cv = new ContentValues();
        cv.put("idmessage", idmessage);
        return this.db.insert(TABLE_NAME41, null, cv);
    }





    public long insertIdMessageViber(String idmessage){
        //   this.db.execSQL("DELETE from " + TABLE_NAME21 + ";");
        ContentValues cv = new ContentValues();
        cv.put("idmessage", idmessage);
        return this.db.insert(TABLE_NAME27, null, cv);
    }

    public long insertViberNumber(String number,String memberid){
        ContentValues cv = new ContentValues();
        cv.put("number", number);
        cv.put("memberid", memberid);
        return this.db.insert(TABLE_NAME37, null, cv);
    }

    public long insertServiceskype(String serviceskype){
        this.db.execSQL("DELETE from " + TABLE_NAME31 + ";");
        ContentValues cv = new ContentValues();
        cv.put("serviceskype",serviceskype);
        return this.db.insert(TABLE_NAME31, null, cv);
    }

    public long insertServiceviber(String serviceviber){
        this.db.execSQL("DELETE from " + TABLE_NAME32 + ";");
        ContentValues cv = new ContentValues();
        cv.put("serviceviber",serviceviber);
        return this.db.insert(TABLE_NAME32, null, cv);
    }

    public long insertServicefasebook(String servicefasebook){
        this.db.execSQL("DELETE from " + TABLE_NAME35 + ";");
        ContentValues cv = new ContentValues();
        cv.put("servicefasebook",servicefasebook);
        return this.db.insert(TABLE_NAME35, null, cv);
    }

    public long insertServicefasebookusers(String servicefasebookusers){
        this.db.execSQL("DELETE from " + TABLE_NAME36 + ";");
        ContentValues cv = new ContentValues();
        cv.put("servicefasebookusers",servicefasebookusers);
        return this.db.insert(TABLE_NAME36, null, cv);
    }

    public long insertServiceWatsapp(String watsappid){
        this.db.execSQL("DELETE from " + TABLE_NAME38 + ";");
        ContentValues cv = new ContentValues();
        cv.put("watsappid",watsappid);
        return this.db.insert(TABLE_NAME38, null, cv);
    }

    public long insertWatsappMessage(String watsappidmessage){
        ContentValues cv = new ContentValues();
        cv.put("watsappidmessage",watsappidmessage);
        return this.db.insert(TABLE_NAME39, null, cv);
    }

    public long insertWatsappUser(String watsappiduser){
        ContentValues cv = new ContentValues();
        cv.put("watsappiduser",watsappiduser);
        return this.db.insert(TABLE_NAME40, null, cv);
    }



    //watsappiduser


    public long insertFasebook(String msgid){
        ContentValues cv = new ContentValues();
        cv.put("msgid",msgid);
        return this.db.insert(TABLE_NAME33, null, cv);
    }

    public long InsertFasebookUsers(String userkey){   //-------------------------------------------------
        ContentValues cv = new ContentValues();
        cv.put("userkey",userkey);
        return this.db.insert(TABLE_NAME34,null,cv);
    }



    public void DeleteViberNumber(){
        this.db.execSQL("DELETE from " + TABLE_NAME37 + ";");
    }

    public void DeleteServicefasebook(){
        this.db.execSQL("DELETE from " + TABLE_NAME35 + ";");
    }

    public void DeleteServicefasebookusers(){
        this.db.execSQL("DELETE from " + TABLE_NAME36 + ";");
    }

    public void DeleteServiceskype(){
        this.db.execSQL("DELETE from " + TABLE_NAME31 + ";");
    }

    public void DeleteServiceviber(){
        this.db.execSQL("DELETE from " + TABLE_NAME32 + ";");
    }

    public void DeleteIdMessage(){
        this.db.execSQL("DELETE from " + TABLE_NAME26 + ";");
    }

    public void DeleteIdMessageSkype(){
        this.db.execSQL("DELETE from " + TABLE_NAME27 + ";");
    }

    public void DeleteIdMessageTwo(){
        this.db.execSQL("DELETE from " + TABLE_NAME28 + ";");
    }

    public void DeleteIdMessageViber(){
        this.db.execSQL("DELETE from " + TABLE_NAME29 + ";");
    }

    public void DeleteLogin(){
        this.db.execSQL("DELETE from " + TABLE_NAME9 + ";");
    }

    public void DeleteEmail(){
        this.db.execSQL("DELETE from " + TABLE_NAME10 + ";");
    }

    public void DeletePassword(){
        this.db.execSQL("DELETE from " + TABLE_NAME11 + ";");
    }

    public void DeleteUrl_Http(){
        this.db.execSQL("DELETE from " + TABLE_NAME15 + ";");
    }

    public void DeleteFasebook(){
        this.db.execSQL("DELETE from " + TABLE_NAME33 + ";");
    }

    public void DeleteFasebookUsers(){
        this.db.execSQL("DELETE from " + TABLE_NAME34 + ";");
    }

    public void DeleteWatsapp(){
        this.db.execSQL("DELETE from " + TABLE_NAME38 + ";");
    }

    public void DeleteWatsappMessage(){
        this.db.execSQL("DELETE from " + TABLE_NAME39 + ";");
    }

    public void DeleteWatsappUser(){
        this.db.execSQL("DELETE from " + TABLE_NAME40 + ";");
    }

    public void DeleteViberTwoTwo(){
        this.db.execSQL("DELETE from " + TABLE_NAME41 + ";");
    }



    public String selectViberNumber(String memberid)
    {
        Cursor cursor = this.db.query(TABLE_NAME37, new String[] {"memberid","number"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(memberid.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(1);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectFasebook(String msgid)
    {
        Cursor cursor = this.db.query(TABLE_NAME33, new String[] {"msgid"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(msgid.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }


    public String selectFasebookUsers(String userkey)
    {
        Cursor cursor = this.db.query(TABLE_NAME34, new String[] {"userkey"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(userkey.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }


    public String selectUrl_Http(){

        Cursor cursor = this.db.query(TABLE_NAME15, new String[] {"url_http"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                strt =cursor.getString(0);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }

    public String selectLogin(){

        Cursor cursor = this.db.query(TABLE_NAME9, new String[] {"login"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                strt =cursor.getString(0);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }

    public String selectEmail(){

        Cursor cursor = this.db.query(TABLE_NAME10, new String[] {"email"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                strt =cursor.getString(0);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }

    public String selectPassword(){

        Cursor cursor = this.db.query(TABLE_NAME11, new String[] {"password"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                strt =cursor.getString(0);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }

     public String selectServiceskype(String serviceskype)
    {
        Cursor cursor = this.db.query(TABLE_NAME31, new String[] {"serviceskype"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(serviceskype.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectServiceviber(String serviceviber)
    {
        Cursor cursor = this.db.query(TABLE_NAME32, new String[] {"serviceviber"}, null, null,null, null, null, null);
        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(serviceviber.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }


    public String selectServicefasebook(String servicefasebook)
    {
        Cursor cursor = this.db.query(TABLE_NAME35, new String[] {"servicefasebook"}, null, null,null, null, null, null);
        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(servicefasebook.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }


    public String selectServicefasebookusers(String servicefasebookusers)
    {
        Cursor cursor = this.db.query(TABLE_NAME36, new String[] {"servicefasebookusers"}, null, null,null, null, null, null);
        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(servicefasebookusers.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;
    }

    public String selectIdMessage(String idmessage)
    {
        Cursor cursor = this.db.query(TABLE_NAME26, new String[] {"idmessage"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(idmessage.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectIdMessageSkype(String idmessage)
    {
        Cursor cursor = this.db.query(TABLE_NAME27, new String[] {"idmessage"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(idmessage.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectIdMessageTwoTwo(String idmessage)
    {
        Cursor cursor = this.db.query(TABLE_NAME41, new String[] {"idmessage"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(idmessage.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectIdMessageTwo(String idmessage)
    {
        Cursor cursor = this.db.query(TABLE_NAME28, new String[] {"idmessage"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(idmessage.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectIdMessageViber(String idmessage)
    {
        Cursor cursor = this.db.query(TABLE_NAME29, new String[] {"idmessage"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(idmessage.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectServiceWatsapp(String watsappid)
    {
        Cursor cursor = this.db.query(TABLE_NAME38, new String[] {"watsappid"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(watsappid.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }


    public String selectIWatsappMessage(String watsappidMessage)
    {
        Cursor cursor = this.db.query(TABLE_NAME39, new String[] {"watsappidmessage"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(watsappidMessage.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    public String selectIWatsappUser(String watsappiduser)
    {
        Cursor cursor = this.db.query(TABLE_NAME40, new String[] {"watsappiduser"}, null, null,null, null, null, null);

        String strt = "";
        if (cursor.moveToFirst()) {
            do {
                if(watsappiduser.equals(cursor.getString(0)))
                {
                    strt =cursor.getString(0);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return strt;

    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME9+ "(id INTEGER PRIMARY KEY,login TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME10+ "(id INTEGER PRIMARY KEY,email TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME11+ "(id INTEGER PRIMARY KEY,password);");
            db.execSQL("CREATE TABLE " + TABLE_NAME15+ "(id INTEGER PRIMARY KEY,url_http TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME26+ "(id INTEGER PRIMARY KEY,idmessage TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME27+ "(id INTEGER PRIMARY KEY,idmessage TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME28+ "(id INTEGER PRIMARY KEY,idmessage TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME29+ "(id INTEGER PRIMARY KEY,idmessage TEXT);");

            db.execSQL("CREATE TABLE " + TABLE_NAME31+ "(id INTEGER PRIMARY KEY,serviceskype TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME32+ "(id INTEGER PRIMARY KEY,serviceviber TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME33+ "(id INTEGER PRIMARY KEY,msgid TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME34+ "(id INTEGER PRIMARY KEY,userkey TEXT);");

            db.execSQL("CREATE TABLE " + TABLE_NAME35+ "(id INTEGER PRIMARY KEY,servicefasebook TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME36+ "(id INTEGER PRIMARY KEY,servicefasebookusers TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME37+ "(id INTEGER PRIMARY KEY,number TEXT,memberid TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME38+ "(id INTEGER PRIMARY KEY,watsappid TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME39+ "(id INTEGER PRIMARY KEY,watsappidmessage TEXT);");
            db.execSQL("CREATE TABLE " + TABLE_NAME40+ "(id INTEGER PRIMARY KEY,watsappiduser TEXT);");

            db.execSQL("CREATE TABLE " + TABLE_NAME41+ "(id INTEGER PRIMARY KEY,idmessage TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME9);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME10);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME11);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME15);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME26);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME27);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME28);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME29);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME31);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME32);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME33);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME34);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME35);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME36);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME37);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME38);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME39);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME40);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME41);

            onCreate(db);
        }
    }
}