package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String product;
    private int quantity;

    public static OrderEvent fromOrder(Order order) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setProduct(order.getProduct());
        orderEvent.setQuantity(order.getQuantity());
        return orderEvent;
    }
}
