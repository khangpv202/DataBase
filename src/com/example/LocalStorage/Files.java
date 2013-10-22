package com.example.LocalStorage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/22/13
 * Time: 9:29 AM
 * To change this template use Files | Settings | Files Templates.
 */
public class Files extends Activity
{
    private final String TAG = "Files";

    private Button btReadFromHtmlFile;
    private Button btSaveIntoSdcard;

    private WebView webView;
    private EditText etSave;
    private Button btSave;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);
        btReadFromHtmlFile = (Button) findViewById(R.id.file_btReadFromHtml);
        btSaveIntoSdcard = (Button) findViewById(R.id.file_btTextFromEdittext);
        webView = (WebView) findViewById(R.id.file_webView);
        etSave = (EditText) findViewById(R.id.file_etSave);
        btSave = (Button) findViewById(R.id.file_btSava);

        /*boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        }
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        }
        else
        {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }*/
        btReadFromHtmlFile.setOnClickListener(onClickReadFromHTMLFILE);
        btSaveIntoSdcard.setOnClickListener(onClickSaveIntoSdCard);
        btSave.setOnClickListener(onClickSave);

    }
    private View.OnClickListener onClickSave = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            File file = new File( // just an object
                    Environment.getExternalStorageDirectory() + "/Android/fileWrite2.txt");
            try{
            FileWriter fw =
                    new FileWriter(file);
            try
            {

                fw.write(etSave.getText().toString());
                Log.i(TAG,etSave.getText().toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            fw.flush(); // flush before closing
            fw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    };
    private View.OnClickListener onClickSaveIntoSdCard = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            webView.setVisibility(View.INVISIBLE);
            etSave.setVisibility(View.VISIBLE);
            btSave.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener onClickReadFromHTMLFILE = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            etSave.setVisibility(View.INVISIBLE);
            btSave.setVisibility(View.INVISIBLE);

            File file = new File(Environment.getExternalStorageDirectory() + "/Android", "ICD_CRT_AUS.html"); /*getExternalFilesDir("/Android/ICD_CRT_AUS.html");*/
            String url = Environment.getExternalStorageDirectory() + "/Android/ICD_CRT_AUS.html";
            //Read text from file
            StringBuilder text = new StringBuilder();

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null)
                {
                    text.append(line);
                    text.append('\n');
                }
            }
            catch (IOException e)
            {
                //You'll need to add proper error handling here
            }
            Log.i(TAG, text + "");
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl("file:///" + url);
        }
    };
}
