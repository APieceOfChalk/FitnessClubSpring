package com.server.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;


@Entity //указывает, что данный бин (класс) является сущностью.
@EnableAutoConfiguration
@Table(name = "trainers") //указывает на имя таблицы, которая будет отображаться в этой сущности.
public class Trainers {

    @Id //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице)
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что данное свойство будет создаваться согласно указанной стратегии.
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne(mappedBy = "trainer")
    private Activities activities;

    Trainers(String name, String passport, String phone, String address) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
        this.address = address;

    }

    Trainers() {}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Trainers))
            return false;
        Trainers trainers = (Trainers) o;
        return Objects.equals(this.id, trainers.id) && Objects.equals(this.name, trainers.name)
                && Objects.equals(this.passport, trainers.passport) && Objects.equals(this.phone, trainers.phone)
                && Objects.equals(this.address, trainers.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone, this.address);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", passport='" + this.passport + '\'' +
                ", phone='" + this.phone + ", address='" + this.address + '}';
    }
}
