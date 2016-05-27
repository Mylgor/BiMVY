package manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Дмитрий on 06.04.2016.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DB_NAME    = "computers_database.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "computers";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_OPERATION_MEMORY = "operationMemory";
    public static final String COLUMN_NAME_SYSTEM_MEMORY = "systemMemory";

    public static String[] columnProjection = {
            COLUMN_NAME_ID, COLUMN_NAME_NAME, COLUMN_NAME_OPERATION_MEMORY, COLUMN_NAME_SYSTEM_MEMORY
    };

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL, " +
                COLUMN_NAME_NAME + " TEXT, " + COLUMN_NAME_OPERATION_MEMORY + " INTEGER, " + COLUMN_NAME_SYSTEM_MEMORY +" INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
