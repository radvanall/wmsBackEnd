package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.model.Order;
import com.warehousemanagement.wms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(Integer productId, Integer customerId, Integer quantity) {

        orderRepository.addOrders(productId,customerId,quantity);
    }

    public List<Order> getOrders() {return orderRepository.findAll();}

    public Order getOrder(Integer id) {return orderRepository.findById(id).get();
    }

    public void updateOrder(Order order, Integer id) {
        Order getOrder =orderRepository.findById(id).get();
        getOrder.copyOrder(order);
        orderRepository.save(getOrder);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}

