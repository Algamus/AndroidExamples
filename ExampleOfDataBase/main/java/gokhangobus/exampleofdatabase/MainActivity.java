package gokhangobus.exampleofdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBAdapter db= new DBAdapter(this);


        // add acontact
        db.DBHelper.open();
        long id=db.DBHelper.insertContact("Tacha Serif","myemail@qgg.com");
        id = db.DBHelper.insertContact("ahmet","asdasd@asdasd");
        db.DBHelper.close();
    }
}
