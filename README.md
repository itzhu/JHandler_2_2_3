JHandler
===
Android 2.2.3 里面handler相关的源码，用于学习handler机制。

可用于java、javafx等项目，作为线程消息队列使用。


```java
public class HandlerTest {

    public static void main(String... args) {
        //启动一个主线程处理消息
        startMainHandler();

        //其他的handler线程
        HandlerThread handlerThread = new HandlerThread("MAIN") {
            @Override
            protected void onLooperPrepared() {
                super.onLooperPrepared();
                //设置这个线程为handler消息处理的主线程
                Log.d("MAIN", "tid=" + Thread.currentThread().getId());
            }
        };
        //开始运行主线程
        handlerThread.start();


        //其他线程
        Thread otherThread = new Thread(() -> {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            Handler htHandler = new Handler(handlerThread.getLooper());

            while (true) {
                Log.d("\notherThread", "=====================\n");
                Log.d("otherThread", "tid=" + Thread.currentThread().getId());

                mainHandler.postDelayed(() -> {
                    //这将会在mainThread里面运行
                    Log.d("mainHandler", "run:tid=" + Thread.currentThread().getId());
                }, 3000);


                htHandler.postDelayed(() -> {
                    //这将会在handlerThread里面执行
                    Log.d("htHandler", "run:tid=" + Thread.currentThread().getId());
                }, 2000);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        otherThread.start();
    }


    /**
     * 启动之后，可以使用Looper.getMainLooper()
     */
    public static void startMainHandler() {
        //此线程做为handler的主线程
        Thread mainThread = new Thread(() -> {
            Looper.prepareMainLooper();
            Log.d("mainThread", "tid=" + Thread.currentThread().getId());
            Looper.loop();
        });
        mainThread.start();
    }

}

```

```log
> Task :HandlerTest.main()
2023-03-02 10:40:55 MAIN:tid=16
2023-03-02 10:40:55 
otherThread:=====================

2023-03-02 10:40:55 mainThread:tid=15
2023-03-02 10:40:55 otherThread:tid=17
2023-03-02 10:40:57 htHandler:run:tid=16
2023-03-02 10:40:58 mainHandler:run:tid=15
2023-03-02 10:41:00 
otherThread:=====================

2023-03-02 10:41:00 otherThread:tid=17
2023-03-02 10:41:02 htHandler:run:tid=16
2023-03-02 10:41:03 mainHandler:run:tid=15

```

