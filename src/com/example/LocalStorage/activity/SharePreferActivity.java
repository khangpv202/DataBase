package com.example.LocalStorage.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.LocalStorage.R;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/22/13
 * Time: 9:02 AM
 * To change this template use FileActivity | Settings | FileActivity Templates.
 */
public class SharePreferActivity extends Activity
{
    private EditText etInput;
    private Button btOk;
    private final String key ="SavedKey";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_prefer);
        etInput = (EditText) findViewById(R.id.sharePrefer_etInput);
        btOk = (Button) findViewById(R.id.sharePrefe_btOk);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        String strSavedMem = settings.getString(key,"");
        if(!strSavedMem.isEmpty()){
            etInput.setText(strSavedMem);
        }
        btOk.setOnClickListener(btOkOnClick);

    }
    private View.OnClickListener btOkOnClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, etInput.getText().toString());
            editor.commit();
            finish();
        }
    };
}
