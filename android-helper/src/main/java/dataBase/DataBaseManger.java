package dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseManger extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "app.db";
    // path
    private   String path;


    private Context context;

    private  SQLiteDatabase db;

    public DataBaseManger(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        OpenDataBase(context);
        path = "/data/data/" + context.getPackageName() + "/databases/"
                + DATABASE_NAME;

    }

    private void OpenDataBase(Context context) {
        try {
            String destPath = "/data/data/" + context.getPackageName()
                    + "/databases";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();
                // ---copy the db from the assets folder into
                // the databases folder---
                CopyDB(context.getAssets().open(DATABASE_NAME),
                        new FileOutputStream(destPath + "/" + DATABASE_NAME));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        // ---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public  SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public  String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
