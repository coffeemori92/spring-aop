package hello.advanced.proxyconfig.v1_proxy;

import hello.advanced.proxyapp.v2.OrderControllerV2;
import hello.advanced.proxyapp.v2.OrderRepositoryV2;
import hello.advanced.proxyapp.v2.OrderServiceV2;
import hello.advanced.proxyconfig.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.advanced.proxyconfig.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.advanced.proxyconfig.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean("orderControllerProxyV2")
    public OrderControllerV2 orderController(LogTrace trace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderService(trace));
        return new OrderControllerConcreteProxy(controllerImpl, trace);
    }

    @Bean("orderServiceProxyV2")
    public OrderServiceV2 orderService(LogTrace trace) {
        OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepository(trace));
        return new OrderServiceConcreteProxy(serviceImpl, trace);
    }

    @Bean("OrderRepositoryProxyV2")
    public OrderRepositoryV2 orderRepository(LogTrace trace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, trace);
    }
}
