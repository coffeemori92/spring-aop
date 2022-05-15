package hello.advanced.proxyconfig.v2_dynamicproxy;

import hello.advanced.proxyapp.v1.*;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;

//@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean("orderControllerDynamicProxyV1")
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderService(trace));
        return (OrderControllerV1) Proxy.newProxyInstance(
            OrderControllerV1.class.getClassLoader(),
            new Class[]{OrderControllerV1.class},
            new LogTraceFilterHandler(orderController, trace, PATTERNS));
    }

    @Bean("orderServiceDynamicProxyV1")
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepository(trace));
        return (OrderServiceV1) Proxy.newProxyInstance(
            OrderServiceV1.class.getClassLoader(),
            new Class[]{OrderServiceV1.class},
            new LogTraceFilterHandler(orderService, trace, PATTERNS));
    }

    @Bean("orderRepositoryDynamicProxyV1")
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(
            OrderRepositoryV1.class.getClassLoader(),
            new Class[]{OrderRepositoryV1.class},
            new LogTraceFilterHandler(orderRepository, trace, PATTERNS));
    }
}
