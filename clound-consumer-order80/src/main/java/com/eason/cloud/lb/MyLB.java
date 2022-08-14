package com.eason.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger ai = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = ai.addAndGet(1) % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
