### Fragment详解

1. 基本概念：
    Fragment是在Android3.0引入的新的API，Fragment代表Activity子模块，可以把Fragment理解成Activity的片段。Fragment有自己的生命周期，可以接受自己的输入事件，Fragment必须被"嵌入"Activity中使用，受到宿主Activity的生命周期的影响，比如Activity 被destory销毁了，他也会跟着销毁。

2. 几个特征：
    
    - Fragment作为Activity界面的组成部分。可以调用getActivity()方法获取所在Activity，Activity可以调用FragmentManager的findFragmentById()或findFragmentByTag()方法来获取Fragment。
    - 在Activity运行过程中，可调用FragmentManager的add()、remove()、replace()方法来动态添加、删除、替换Fragment。
    - 一个Activity可以同时组合多个Fragment，反过来，一个Fragment可以被多个Activity复用。
    - Fragment有自己的生命周期，可以接受自己的输入事件，Fragment必须被"嵌入"Activity中使用

3. Fragment的生命周期图
![](http://www.runoob.com/wp-content/uploads/2015/08/31722863.jpg)

4. 创建一个Fragment
创建Fragment步骤：
    
    - 定义Fragment的xml布局文件，就是fragment显示内容的
    - 自定义一个Fragment类,需要继承Fragment或者他的子类,重写`onCreateView()`方法，在该方法中调用:`inflater.inflate()`方法加载Fragment的布局文件,接着返回加载的view对象

```java
public class Fragmentone extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container,false);
        return view;
    }   
}
```

    -  在需要加载Fragment的Activity对应的布局文件中添加fragment的标签， 记住，name属性是全限定类名哦，就是要包含Fragment的包名，如:
    
```java
<fragment
    android:id="@+id/fragment1"
    android:name="com.jay.example.fragmentdemo.Fragmentone"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1" />
```

    - Activity在`onCreate()`方法中调用`setContentView()`加载布局文件即可!

5. 动态加载Fragment:
    - 通过`getFragmentManager()`获得`FragmentManager`对象
    - 获得`FragmentTransaction`对象，`fm.beginTransaction()`
    - 调用`add()`或`replace()`方法加载`fragment`
    - 调用`commit()`方法提交事务
当横竖屏切换的时候地切换Fragment，Activity代码如下：

```java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display dis = getWindowManager().getDefaultDisplay();
        if(dis.getWidth() > dis.getHeight())
        {
            Fragment1 f1 = new Fragment1();
            getFragmentManager().beginTransaction().replace(R.id.LinearLayout1, f1).commit();
        }
        
        else
        {
            Fragment2 f2 = new Fragment2();
            getFragmentManager().beginTransaction().replace(R.id.LinearLayout1, f2).commit();
        }
    }   
}
```

#### 6. Fragment与Activity通信

![Fragment与Activity通信](http://www.runoob.com/wp-content/uploads/2015/08/45971686.jpg)


+ 组件获取
    
    - Fragment获得Activity中的组件:getActivity().findViewById(R.id.list)；
    - Activity获得Fragment中的组件(根据id和tag都可以)：getFragmentManager.findFragmentByid(R.id.fragment1);
    
+ 数据传递

    - Activit传递数据给Fragment:在Activity中创建Bundle数据包,调用Fragment实例的setArguments(bundle)从而将Bundle数据包传给Fragment,然后Fragment中调用getArguments获得 Bundle对象,然后进行解析就可以了
    - Fragment传递数据给Activity：在Fragment中定义一个内部回调接口,再让包含该Fragment的Activity实现该回调接口,Fragment就可以通过回调接口传数据了,回调。

代码如下：

```java
第一步：定义一个回调接口:(Fragment中)
/*接口*/  
public interface CallBack{  
    /*定义一个获取信息的方法*/  
    public void getResult(String result);  
} 

第二步：接口回调（Fragment中）
/*接口回调*/  
public void getData(CallBack callBack){  
    /*获取文本框的信息,当然你也可以传其他类型的参数,看需求咯*/  
    String msg = editText.getText().toString();  
    callBack.getResult(msg);  
}

第三步：使用接口回调方法读数据(Activity中)
/* 使用接口回调的方法获取数据 */  
leftFragment.getData(new CallBack() {  
 @Override  
       public void getResult(String result) {              /*打印信息*/  
            Toast.makeText(MainActivity.this, "-->>" + result, 1).show();  
            }
        }); 
```

参考资料：
- [Fragment基本概述](http://www.runoob.com/w3cnote/android-tutorial-fragment-base.html)
	
