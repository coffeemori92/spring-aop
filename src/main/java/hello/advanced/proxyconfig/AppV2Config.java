package hello.advanced.proxyconfig;

import hello.advanced.proxyapp.v2.OrderControllerV2;
import hello.advanced.proxyapp.v2.OrderRepositoryV2;
import hello.advanced.proxyapp.v2.OrderServiceV2;
import org.springframework.context.annotation.Bean;

//@Configuration
public class AppV2Config {

    @Bean("orderControllerProxyV2")
    public OrderControllerV2 orderControllerV2() {
        return new OrderControllerV2(orderServiceV2());
    }

    @Bean("orderServiceProxyV2")
    public OrderServiceV2 orderServiceV2() {
        return new OrderServiceV2(orderRepositoryV2());
    }

    @Bean("OrderRepositoryProxyV2")
    public OrderRepositoryV2 orderRepositoryV2() {
        return new OrderRepositoryV2();
    }
}
