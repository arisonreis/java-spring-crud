package com.arisonreis.javacrud.domain.user;


import jakarta.persistence.*;
import lombok.*;



@Table(name = "user", schema = "public")
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String lastname;
    private Integer age;

    public User(RequestUser requestUser){
        this.name = requestUser.name();
        this.lastname =  requestUser.lastname();
        this.age = requestUser.age();
    }

}
