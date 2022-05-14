package hello.advanced.proxyconfig.v1_proxy;

import hello.advanced.proxyapp.v1.*;
import hello.advanced.proxyconfig.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.advanced.proxyconfig.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.advanced.proxyconfig.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;

//@Configuration
public class InterfaceProxyConfig {

    @Bean("orderControllerProxyV1")
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(trace));
        return new OrderControllerInterfaceProxy(controllerImpl, trace);
    }

    @Bean("orderServiceProxyV1")
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(trace));
        return new OrderServiceInterfaceProxy(serviceImpl, trace);
    }

    @Bean("OrderRepositoryProxyV1")
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, trace);
    }
}
