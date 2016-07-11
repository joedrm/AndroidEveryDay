## Android点击的写法
1. 按钮点击通常有四种写法：

	1. 通过匿名内部类来实现
	2. 使用内部类去实现事件的处理
	3. 适用于多个按钮的点击，让当前的Activity实现View.OnClickListener接口。
	4. 在xml布局文件中添加点击事件，在.java文件实现
	
2. 代码实现：

```java

package wdy.com.buttonclickeddemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    /*
    写法一
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("button1点击了");
            }
        });
    }*/

    /*
    // 写法二：推荐使用
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new MyClickListener());
    }

    public class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }*/

    // 写法三：适用于多个按钮的点击
    // 同时需要当前Activity实现这个接口：public class MainActivity extends Activity implements View.OnClickListener
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:

                break;

            case R.id.button2:

                break;
        }
    }

    // 写法四(不推荐)：在xml布局文件中添加点击事件，在.java文件实现
    // 这里必须和xml里面的写法一直，否则会报错
    public void login(View v){

    }
}

```