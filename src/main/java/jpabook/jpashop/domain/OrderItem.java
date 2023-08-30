package jpabook.jpashop.domain;

import javax.persistence.*;
import javax.xml.namespace.QName;

@Entity
public class OrderItem extends BaseEntity{
    @Id
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    /* 이 방식은 FK를 그대로 가져온 방식이다. 아래처럼 연관관계 매핑으로 단방향 FK 매핑 처리 한다.
    @Column(name = "ORDER_ID")
    private Long orderId;
    @Column(name = "ITEM_ID")
    private Long itemId;
     */
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    private int orderPrice;
    private int count;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
