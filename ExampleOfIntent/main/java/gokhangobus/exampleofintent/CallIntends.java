package gokhangobus.exampleofintent;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by User on 26.11.2016.
 */
public class CallIntends extends Activity {
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void callIntent(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.button01:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yeditepe.edu.tr"));
                startActivity(intent);
                break;
            case R.id.button02:
                //intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+90) 2125780470"));
                //startActivity(intent);
                break;
            case R.id.button03:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: (+49) 12345789"));
                startActivity(intent);
                break;
            case R.id.button04:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:50.123,7.1434?z=19"));
                startActivity(intent);
                break;
            case R.id.button05:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=query"));
                startActivity(intent);
                break;
            case R.id.button06:
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent,0);
                break;
            case R.id.button07:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
                break;
            case R.id.button08:
                intent = new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/1"));
                startActivity(intent);
                break;
            default:
                break;

        }
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode== Activity.RESULT_OK && requestCode ==0){
            String result = data.toURI();
            Toast.makeText(this ,result,Toast.LENGTH_LONG);
        }
    }





}
