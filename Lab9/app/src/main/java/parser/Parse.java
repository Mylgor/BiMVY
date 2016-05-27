package parser;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab6.MainActivity;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Дмитрий on 28.04.2016.
 */
public class Parse{

    private String IdApp = "8LKU27-YGGHXK3UL2";
    private Activity activity;

    public Parse(){
    }


    public String startParse(String data)
    {
        String answer = "";
        InputStream inputStream = null;
        InputStreamReader readStream = null;
        Boolean isRightAnswer = false;
        try
        {
            URL url = new URL("http://api.wolframalpha.com/v2/query?input=" + data + "&appid=" + IdApp);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            if (inputStream != null)
            {
                XmlPullParser parser = Xml.newPullParser();
                readStream = new InputStreamReader(inputStream);
                parser.setInput(readStream);

                while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("pod")) {
                        if (parser.getAttributeValue(0).equals("Result")) {
                            isRightAnswer = true;
                            parser.next();
                        } else if (parser.getAttributeValue(0).equals("Exact result")) {
                            isRightAnswer = true;
                            parser.next();
                        } else if (parser.getAttributeValue(0).equals("Decimal form")) {
                            isRightAnswer = true;
                            parser.next();
                        } else if (parser.getAttributeValue(0).equals("Decimal approximation")) {
                            isRightAnswer = true;
                            parser.next();
                        }
                    } else if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("plaintext") && isRightAnswer) {
                        answer = parser.nextText();
                        isRightAnswer = false;
                    }

                    // переходим к следующему элементу
                    parser.next();

                }
            }
        }
        catch (Exception ex)
        {}
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }catch (Exception e){}

            try {
                if (readStream != null){
                    readStream.close();
                }
            }catch (Exception e){}

        }

        return answer;
    }
}
