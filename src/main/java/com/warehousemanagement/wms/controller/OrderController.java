package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.model.Order;
import com.warehousemanagement.wms.services.OperatorService;
import com.warehousemanagement.wms.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(method = RequestMethod.POST, value="/create/{productId}/{customerId}/{quantity}")
    public void createOrder(@PathVariable Integer productId,@PathVariable Integer customerId,@PathVariable Integer quantity)
    {
        orderService.createOrder(productId,customerId,quantity);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Order> getAllOrder(){
        return orderService.getOrders();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Order getOrder(@PathVariable Integer id){
        return orderService.getOrder(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateOrder(@RequestBody Order order, @PathVariable Integer id){
        orderService.updateOrder(order,id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    public void deleteOrder( @PathVariable Integer id){
        orderService.deleteOrder(id);
    }
}
