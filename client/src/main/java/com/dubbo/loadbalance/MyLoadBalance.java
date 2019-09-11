package com.dubbo.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.AtomicPositiveInteger;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyLoadBalance extends AbstractLoadBalance {

    public static final String NAME = "myOddNumber";

    private final ConcurrentMap<String, AtomicPositiveInteger> sequences = new ConcurrentHashMap<String, AtomicPositiveInteger>();


    private static Random random = new Random();

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        System.out.println("走我的负载均衡规则！！！");
        System.out.println(url.getAddress());
        Iterator<Invoker<T>> iterator = invokers.iterator();
        List<Invoker<T>> invokerList = new ArrayList<>();
        while (iterator.hasNext()) {

            Invoker<T> next = iterator.next();
            int port = next.getUrl().getPort();
            if (port % 2 == 1) {
                invokerList.add(next);
            }
        }
        if (invokerList.size() == 0) {
            return invokers.get(0);
        }
        return invokerList.get(random.nextInt(invokerList.size()));
    }
}