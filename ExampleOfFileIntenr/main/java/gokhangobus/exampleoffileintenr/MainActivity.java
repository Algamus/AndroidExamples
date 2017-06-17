package gokhangobus.exampleoffileintenr;

import android.app.Activity;
import android.view.View;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity {
    private EditText textBox;
    private static final int READ_BLOCK_SIZE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText) findViewById(R.id.txtText1);
        Button saveBtn = (Button) findViewById(R.id.btnSave);
        Button loadBtn = (Button) findViewById(R.id.btnLoad);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = textBox.getText().toString();
                try
                {
                    FileOutputStream fOut =
                            openFileOutput("textfile.txt",
                                    MODE_WORLD_READABLE);
                    OutputStreamWriter osw = new
                            OutputStreamWriter(fOut);

                    osw.write(str);
                    osw.flush();
                    osw.close();



                    Toast.makeText(getBaseContext(),
                            "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

                    textBox.setText("");
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                try
                {
                    FileInputStream fIn =
                            openFileInput("textfile.txt");
                    InputStreamReader isr = new
                            InputStreamReader(fIn);
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    String s = "";
                    int charRead;
                    while ((charRead = isr.read(inputBuffer))>0)
                    {

                        String readString =
                                String.copyValueOf(inputBuffer, 0,
                                        charRead);
                        s += readString;
                        inputBuffer = new char[READ_BLOCK_SIZE];
                    }

                    textBox.setText(s);
                    Toast.makeText(getBaseContext(),
                            "File loaded successfully!",
                    Toast.LENGTH_SHORT).show();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }
}