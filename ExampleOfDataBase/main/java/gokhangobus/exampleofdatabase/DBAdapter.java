package gokhangobus.exampleofdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 29.11.2016.
 * DBadapter
 */
public class DBAdapter {
    public static final String KEY_ROWID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";
    public static final String TAG="DBAdapter";
    public static final String DATABASE_NAME="MyDb";
    public static final String DATABASE_TABLE="contacts";
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_CREATE="create table contacts (_id integer primary key autoincrement, name text not null, email text not null";

    private final Context context;
    public static DatabaseHelper DBHelper;
    private static SQLiteDatabase db;
    public DBAdapter(Context ctx){
        context=ctx;
        DBHelper=new DatabaseHelper(this,context);
    }
    protected static class DatabaseHelper extends SQLiteOpenHelper{
        private DBAdapter dbAdapter;
        DatabaseHelper(DBAdapter d,Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            dbAdapter=d;
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try {

                db.execSQL(DATABASE_CREATE);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            Log.w(TAG,"Upgrading Database from Version" + oldVersion + " to " + newVersion+", which will destroy all data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }

        //--opens the database
        public DBAdapter open() throws SQLException{
            db=DBHelper.getWritableDatabase();
            return dbAdapter;///problem  bakılcak...
        }

        //close the database
        public  void close(){
            DBHelper.close();
        }
        //insert a contact
        public long insertContact(String name,String email){
            ContentValues initialValues= new ContentValues();
            initialValues.put(KEY_NAME,name);
            initialValues.put(KEY_EMAIL,email);
            return  db.insert(DATABASE_TABLE,null,initialValues);

        }

        // delete a particular contact
        public  boolean deleteContact(long rowId){
            return db.delete(DATABASE_TABLE,KEY_ROWID+"="+rowId,null)>0;
        }
    //retrives all the contacts
        public Cursor getAllContacts(){
            return  db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL},null,null,null,null,null);
        }
    //retrive a particular contact
        public Cursor getContact(long rowId) throws SQLException{

            Cursor mCursor=
            db.query(true,DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL},KEY_ROWID+"="+rowId ,null,null,null,null,null);
            if(mCursor!=null){
                mCursor.moveToFirst();
            }
            return mCursor;
        }

        ///updates
        public boolean updateContact(long rowId,String name,String email){
            ContentValues args=new ContentValues();
            args.put(KEY_NAME,name);
            args.put(KEY_EMAIL,email);
            return  db.update(DATABASE_TABLE,args,KEY_ROWID+"="+rowId,null)>0;
        }

    }
}
