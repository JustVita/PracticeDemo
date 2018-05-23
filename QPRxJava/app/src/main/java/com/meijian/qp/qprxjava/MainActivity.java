package com.meijian.qp.qprxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.meijian.qp.qprxjava.datas.Translation;
import com.meijian.qp.qprxjava.datas.YDTranslation;
import com.meijian.qp.qprxjava.interfaces.GetRequest;
import com.meijian.qp.qprxjava.interfaces.YDPostRequest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Subscriber<String> subscriber;
    private Observable observable;
    private Observable<Integer> observable1;
    private Observer<Integer> observer;
    private EditText input;
    private TextView translate;
    private Button btnTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void initView() {

        input = findViewById(R.id.et_input);
        translate = findViewById(R.id.tv_translate);
        btnTranslate = findViewById(R.id.btn_translate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
                //request();
            }
        });
    }

    private void postRequest() {

        String s = input.getText().toString();

        if (s.isEmpty())
            return;

        //初始化retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //初始化请求
        YDPostRequest ydPostRequest = retrofit.create(YDPostRequest.class);

        //输入请求字符
        Call<YDTranslation> call = ydPostRequest.getCall(s);

        call.enqueue(new Callback<YDTranslation>() {
            @Override
            public void onResponse(Call<YDTranslation> call, Response<YDTranslation> response) {
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
                String s = response.body().getTranslateResult().get(0).get(0).getTgt();
                translate.setText(s);
            }

            @Override
            public void onFailure(Call<YDTranslation> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"翻译失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")// 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())//设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetRequest request = retrofit.create(GetRequest.class);

        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //链式操作
        //initRxJava();

//        initObservable();
//        initObserver();
//        initSubscribe();
    }

    private void initRxJava() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //接收事件前，会最先调用复写onSubscribe
                Log.d(TAG, "onSubscribe: 开始Subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext: 对Next事件做出响应" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //当被观察者生产error事件，观察者接收到时，会调用此方法进行响应
                Log.d(TAG, "onError: 响应error事件");
            }

            @Override
            public void onComplete() {
                //生产Complete事件时

                Log.d(TAG, "onComplete: 响应Complete事件");
            }
        });
    }

    private void initSubscribe() {

        observable.subscribe(observer);
//        observable.subscribe(subscriber);
    }

    private void initObserver() {

//        //方式1：采用observer接口
        //接收事件前，会最先调用复写onSubscribe
//当被观察者生产error事件，观察者接收到时，会调用此方法进行响应
//生产Complete事件时
//        observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//                //接收事件前，会最先调用复写onSubscribe
//                Log.d(TAG, "onSubscribe: 开始Subscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//
//                Log.d(TAG, "onNext: 对Next事件做出响应" + integer);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//                //当被观察者生产error事件，观察者接收到时，会调用此方法进行响应
//                Log.d(TAG, "onError: 响应error事件");
//            }
//
//            @Override
//            public void onComplete() {
//                //生产Complete事件时
//
//                Log.d(TAG, "onComplete: 响应Complete事件");
//            }
//        };

//        //方式2：采用subscriber抽象类
        subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe: 开始Subscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: 对Next事件做出响应" + s);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: 响应error事件");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: 响应Complete事件");
            }
        };

        //相同点：使用上完全一致，实质上，observer总是会被先转化成subscriber再使用
        //不同点：新增两个方法：
        //1：onStart()在还未响应事件前调用进行一些初始化工作的工作
        //2：unSubscribe()用于取消订阅，调用前先使用isUnsubscribed()判断状态，如果不能及时释放，就会出现内存泄露
    }

    private void initObservable() {

        //当Observable被订阅时，OnSubscribe的call()方法会被调用，事件就会按照设定依次被触发
        //在此定义需要发送的事件
        observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                //当Observable被订阅时，OnSubscribe的call()方法会被调用，事件就会按照设定依次被触发
                //在此定义需要发送的事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        //方法1：将直接传入的参数依次发送出来
        //将依次调用： onNext("A"); onNext("B"); onNext("C"); onComplete();
//        Observable observable = Observable.just("A","B","C");


        //方法2：将传入的数组拆分成具体对象后，依次发送出来
        //将依次调用： onNext("A"); onNext("B"); onNext("C"); onComplete();
//        String [] words = {"A","B","C"};
//        observable = Observable.fromArray(words);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
