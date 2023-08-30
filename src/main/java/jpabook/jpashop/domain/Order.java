package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") //DB에 ORDER BY라는 예약어가 있는곳도 있어서, table명을 ORDERS로 지정
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
/*    @Column(name = "MEMBER_ID") //객체보다는 관계형 DB에 맞춘 설계 <<문제점이 있다>>
    private Long memberId;  //식별자만 가져오기 때문에 참조가 다 끊겨버린다. --> 연관관계 Mappiong이 필요하다 !
    private Member member; //Order에서 find MemberId한 후, Member에서도 찾아오는 것보다 객체 지향적 방법
 */
    @ManyToOne // 연관관계 매핑으로 dev해보자. 관계를 명시해 주면서 매핑을 한다. Order의 입장에서 Member는 N:1
    @JoinColumn(name = "MEMBER_ID") //ORDERS table FK
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    //TODO 반면 Order Table의 OrderList FK는 business적으로 가치가 있는 참조이다. 이렇게 매핑한다.
    // 사실 Member , Order 안의 orderList는 굳이 없어도 된다.
    // Main에서 List 만들어서 Order 통으로 넣어주면 되기 때문. 이는(양방향 연관관계) 개발상의 편의를 원한 것.
    // ** ==> 핵심 : 실무에서 되도록 단방향으로 하라. 굳이 실무에서 필요하면 양방향 연관관계를 추가한다. **
/*
    OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);
 */
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();



    private LocalDateTime orderDate; //java8에서 자동으로 LocalDateTime은 매핑됨
    //SpringBoot에서 Hibernate의 관례 변경이 가능하다.
    //ORDER_DATE, order_date : DBA성향에 따라 rule이 다르기 때문에 boot에서는 원하는 이름으로 설정을 변경할 수 있게 되었다.
    //camel of java --> underscore of DB
    @Enumerated(EnumType.STRING) //필수
    private OrderStatus status; //made by Enum


    //TODO 연관관계 편의 메소드를 이런식으로 만들어 준다 (양방향 연관관계 이기 때문에 굳이 만들어 준 것.)
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


}
