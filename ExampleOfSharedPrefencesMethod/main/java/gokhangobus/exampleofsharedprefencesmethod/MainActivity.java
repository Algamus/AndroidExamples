package gokhangobus.exampleofsharedprefencesmethod;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private String prefName="MyPref";
    private EditText editText;
    private SeekBar seekBar;
    private Button btn;
    private static final String FONT_SIZE_KEY = "fontsize";
    private static final String TEXT_VALUE_KEY= "textvalue";

    //called when the activity is first created .

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //get the shared prefences of the objects
                pref= getSharedPreferences(prefName,MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                //save the values in the EditText view to prefences
                editor.putFloat(FONT_SIZE_KEY,editText.getTextSize());
                editor.putString(TEXT_VALUE_KEY,editText.getText().toString());
                //save the values
                editor.commit();
                //display file saved message
                Toast.makeText(getBaseContext(),"Font size saved successfully",Toast.LENGTH_SHORT).show();
            }
        });
        //load the sharedprefences object

        SharedPreferences prefs = getSharedPreferences(prefName,MODE_PRIVATE);

        // set the textview fontsize to previously saved values
        float fontsize = prefs.getFloat(FONT_SIZE_KEY,12);
        //init the seekbar and edit text

        seekBar.setProgress((int)fontsize);
        editText.setText(prefs.getString(TEXT_VALUE_KEY,""));
        editText.setTextSize(seekBar.getProgress());


        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromuser){
                //change the font size of the edit text
                editText.setTextSize(progress);
            }
        });

    }




}
