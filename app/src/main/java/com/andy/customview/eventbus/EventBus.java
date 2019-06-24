package com.andy.customview.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author andysong
 * @data 2019-06-24
 * @discription xxx
 */
public class EventBus {


    private Map<Object, List<SubscribeMethod>> cacheMap;

    private Handler mHandler;

    private static class LazyLoad {
        private static EventBus instance = new EventBus();
    }


    private EventBus(){
        cacheMap = new HashMap<>();
        mHandler  = new Handler();
    }

    public static EventBus getDefault(){
        return LazyLoad.instance;
    }

    public void register(Object object){
        List<SubscribeMethod> list = cacheMap.get(object);
        if (list == null){
            list = findSubscribeMethods(object);
            cacheMap.put(object,list);
        }
    }

    private List<SubscribeMethod> findSubscribeMethods(Object object) {
        List<SubscribeMethod> list = new ArrayList<>();
        Class<?> clazz  = object.getClass();

        //循环找其父类带subscirbe的方法
        while (clazz != null){
            //凡是系统级别的父类，直接跳过
            String name = clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax.")
            ||name.startsWith("android.")){
                break;
            }


            Method[] methods = clazz.getDeclaredMethods();
            for (Method method :
                    methods) {
                //寻找所有带Subscribe注解的方法
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe == null){
                    continue;
                }
                //判断当前Subscribe注解的方式，是否有且仅有一个参数
                Class<?> [] types = method.getParameterTypes();
                if (types.length!=1){
                    Log.e("szx","错误的使用方式");

                }
                ThreadMode threadMode = subscribe.threadMode();

                SubscribeMethod subscribeMethod = new SubscribeMethod(method,threadMode,types[0]);
                list.add(subscribeMethod);


            }
            clazz = clazz.getSuperclass();
        }



        return list;
    }

    public void post(Object bean){
        //直接循环cacheMap然后找到对应bean的方法
        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            List<SubscribeMethod> list = cacheMap.get(obj);
            for (SubscribeMethod sub :
                    list) {




                //判断a 对象所对应的类是不是b对象所对应的类信息的父类或者接口
                if (sub.getType().isAssignableFrom(bean.getClass())){
                    //线程切换
                    switch (sub.getThreadMode()){
                        case MAIN:
                            //主--主
                            if (Looper.myLooper() == Looper.getMainLooper()){
                                invoke(sub,obj,bean);
                            }else {
                                //子--主
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(sub,obj,bean);
                                    }
                                });
                            }
                            break;
                        case BACKGROUND:
                            //子--主
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    invoke(sub,obj,bean);
                                }
                            });
                            break;
                    }
                }
            }
        }
    }

    private void invoke(SubscribeMethod sub, Object obj, Object bean) {
        Method method = sub.getMethod();
        try {
            method.invoke(obj,bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
