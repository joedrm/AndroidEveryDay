package wdy.com.androidhandlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int [] imageIds = new int[] {
            R.drawable.minions01,
            R.drawable.minions02,
            R.drawable.minions03
    };
    int currentImageId = 0;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView showImage = (ImageView) findViewById(R.id.image_show);
        final Button start_show = (Button) findViewById(R.id.start_show);

        start_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(0x1233);
                    }
                },0, 1200);
            }
        });

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0X1233){
                    showImage.setImageResource(imageIds[currentImageId++%imageIds.length]);
                }
            }
        };
    }
}
