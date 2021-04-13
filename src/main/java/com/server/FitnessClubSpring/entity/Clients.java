package com.server.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity //указывает, что данный бин (класс) является сущностью.
@EnableAutoConfiguration
@Table(name = "clients") //указывает на имя таблицы, которая будет отображаться в этой сущности.
public class Clients {
    @Id
    //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице)
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что данное свойство будет создаваться согласно указанной стратегии.
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy="client")
    private List<Subscriptions> subscriptions = new ArrayList<>();


    Clients(String name, String passport, String phone, String address) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;

    }

    Clients() {}

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

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Clients))
            return false;
        Clients clients = (Clients) o;
        return Objects.equals(this.id, clients.id) && Objects.equals(this.name, clients.name)
                && Objects.equals(this.passport, clients.passport) && Objects.equals(this.phone, clients.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", passport='" + this.passport + '\'' +
                ", phone='" + this.phone + '}';
    }
}
