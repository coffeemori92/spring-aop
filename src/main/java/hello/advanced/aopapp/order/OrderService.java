package hello.advanced.aopapp.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void orderItem(String itemId) {
        log.info("[orderService] 실행");
        repository.save(itemId);
    }
}
