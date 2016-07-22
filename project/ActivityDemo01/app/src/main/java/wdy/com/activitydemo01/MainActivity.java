package wdy.com.activitydemo01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicked = (Button) findViewById(R.id.button);
        clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 一次传递单个数据
                Intent n1 = new Intent(MainActivity.this, MyAcitvity.class);
                n1.putExtra("key","你好啊！");
                startActivity(n1);

                // 一次传递多个数据
                Intent n2 = new Intent(MainActivity.this, MyAcitvity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("number",1);
                bundle.putString("message","呵呵");
                n2.putExtras(bundle);
                startActivity(n2);
            }
        });
    }
}
