package com.su25.swp391.controller.customer;

import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;

public class OrderViewModel {
    private Food food;
    private Order order;
    private OrderItem item;

    public OrderViewModel(Food food, Order order, OrderItem item) {
        this.food = food;
        this.order = order;
        this.item = item;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }
}
