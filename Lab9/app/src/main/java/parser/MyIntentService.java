package parser;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;

import com.example.lab6.MainActivity;
import com.example.lab6.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String expression = intent.getStringExtra(MainActivity.EXP_CODE);
            PendingIntent pIntent = intent.getParcelableExtra(MainActivity.INT_CODE);

            Parse parser = new Parse();
            String answer = parser.startParse(expression);
            answer = checkAnswer(answer);
            try {
                Intent responseIntent = new Intent();
                responseIntent.putExtra(MainActivity.SERV_CODE, answer).putExtra("status", 1);
                pIntent.send(MyIntentService.this, 1, responseIntent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            stopSelf();
        }
    }

    private String checkAnswer(String answer)
    {
        boolean isPoint = false;
        int count = 2, i;
        for (i = 0; i < answer.length(); i++)
        {
            if (count == 0)
                break;
            if (isPoint)
                count--;
            if (answer.charAt(i) == '.')
                isPoint = true;
        }
        if (count == 0)
            return answer.substring(0, i);
        else
            return answer;
    }
}
