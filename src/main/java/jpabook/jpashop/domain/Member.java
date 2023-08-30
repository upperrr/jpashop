package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;


@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue //(strategy = GenerationType.AUTO) 생략가능
    @Column(name = "MEMBER_ID") //varchar(255) 기본으로 생성 되기에, length를 명시 해주는 것이 좋다.
    private Long Id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    //TODO Orders : List 가 추가 되었다면? (Member 입장에서 Orders List가 필요하다면 ?)
    // 참조 : 좋은 DB설계는 아님. 모든 객체에 넣으려고 하면 끝이 없다. 관심사의 분리로 끊어내는 것이 중요
    // 그래서 JPA가 객체 설계가 중요한 것. (필요하면 member 조회하고 order 조회 하면 됨)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>(); //OneToMany니까 List가 되야 할 것.



//Getter는 관계 없지만, Setter를 막 만들면, 아무데서나 set이 가능하기에 유지/보수 성이 떨어질 수 있다.
//가능하면 생성자에서 최대한 값을 set하고, setter의 사용을 줄여야 유지/보수 가 용이하다.
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
