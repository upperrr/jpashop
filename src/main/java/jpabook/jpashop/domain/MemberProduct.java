package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct { //연결테이블을 Entity로 승격하여 N:1 양방향 관계로 작성하자. (N:N대신)

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;
    private LocalDateTime orderDateTime;
}
