package hello.advanced.proxyconfig;

import hello.advanced.proxyapp.v1.*;
import org.springframework.context.annotation.Bean;

//@Configuration
public class AppV1Config {

    @Bean("orderControllerProxyV1")
    public OrderControllerV1 orderControllerV1() {
        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean("orderServiceProxyV1")
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1Impl(orderRepositoryV1());
    }

    @Bean("OrderRepositoryProxyV1")
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }
}
