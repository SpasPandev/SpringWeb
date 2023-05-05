package com.example.events.orderlisteners;

import com.example.events.ApplicationListenerTest;
import com.example.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQuantityCalculator {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductQuantityCalculator.class);

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreated(OrderCreatedEvent orderCreatedEvent) {

        LOGGER.info("Order no {} has been create. I'm going to calculate the current product quantities.",
                orderCreatedEvent.getOrderId());

        // TODO - see what products had been ordered and calculate subtract product quantities.
    }
}
