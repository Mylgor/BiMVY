package util;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Дмитрий on 06.04.2016.
 */
public class Utils {
    public static final String FILE_NAME = "computer.txt";

    public static void writeObjectToFile(Context context, Object object, String filename) {
        ObjectOutputStream objectOut = null;
        FileOutputStream fileOut = null;
        try {

            fileOut = context.openFileOutput(filename, Activity.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            fileOut.getFD().sync();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                }
            }
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Object readObjectFromFile(Context context, String filename) {

        ObjectInputStream objectIn = null;
        Object object = null;
        FileInputStream fileIn = null;
        BufferedInputStream buffIn = null;
        try {

            fileIn = context.getApplicationContext().openFileInput(filename);
            buffIn = new BufferedInputStream(fileIn);
            objectIn = new ObjectInputStream(buffIn);
            object = objectIn.readObject();

        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                }
            }
            if (buffIn != null) {
                try {
                    buffIn.close();
                } catch (Exception e) {
                }
            }
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (Exception e) {
                }
            }

        }
        return object;
    }
}
