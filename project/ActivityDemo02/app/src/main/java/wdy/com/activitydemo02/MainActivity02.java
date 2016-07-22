package wdy.com.activitydemo02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity02 extends AppCompatActivity {

    private Button button2;
    private EditText textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);

        textView = (EditText)findViewById(R.id.textView2);
        button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();
                Bundle bd = new Bundle();
                bd.putString("inputStr",textView.getText().toString().trim());
                it.putExtras(bd);
                setResult(0x123,it);
                finish();
            }
        });
    }



}
