package com.example.liuyang05_sx.androidstudy.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class RxBus {

    /**
     * 主题
      */
    private final FlowableProcessor<Object> bus;

    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Flowable的数据发射给观察者
     */
    private RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getDefault() {
        return RxBusHolder.INSTANCE;
    }

    private static class RxBusHolder {
        private static final RxBus INSTANCE = new RxBus();
    }

    /**
     * 提供了一个新的事件
     *
     * @param o Object
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     *
     * @param eventType Event type
     * @param <T> 对应的Class类型
     * @return Flowable<T>
     */
//    public <T> Flowable<T> toFlowable(Class<T> eventType) {
//        return bus.ofType(eventType);
//    }
    public <T> Flowable<T> toObservable(LifecycleOwner owner, final Class<T> eventType) {
        LifecycleProvider<Lifecycle.Event> provider = AndroidLifecycle.createLifecycleProvider(owner);
        return bus.ofType(eventType).compose(provider.<T>bindToLifecycle());
    }

}
