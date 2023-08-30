package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Item 입장엫서 필요하면 연관관계를 양방향으로 만들 수 있으나, 사실 상품 입장에서 어떤 주문에 의해 상품 되었는지 중요도가 높지 않다.
//하여 Table상에서도 FK가 없는 해당 객체는 연관관계 매핑이 전혀 없다. (필요시 코드만 추가 해 주면 될 일이다. 나중에 DB 통계나 집계 report 같은거 할때나.. 빅데이터 시에만 필요)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
