package com.epam.streamdp.lerning.entity;


import com.epam.streamdp.two.Entity;

public class Order extends Entity {
    private long orderId;
    private double amount;


    public Order() {
    }

    public Order(long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        return Double.compare(order.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (orderId ^ (orderId >>> 32));
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
