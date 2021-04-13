package com.server.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;


@Entity //указывает, что данный бин (класс) является сущностью.
@EnableAutoConfiguration //позволяет делать автоматическое создание таблицы
@Table(name = "subscriprions") //указывает на имя таблицы, которая будет отображаться в этой сущности.
public class Subscriptions {
    @Id
    //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице)
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что данное свойство будет создаваться согласно указанной стратегии.
    private Long id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Clients client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private Activities activity;

    @Column(name = "date", nullable = false)
    private String date;
    
    @Column(name = "price", nullable = false)
    private Long price;
    
    @Column(name = "payment_status", nullable = false)
    private Boolean paymentStatus;

    public Subscriptions(Clients client, Activities activity, String date, Long price, Boolean paymentStatus) {
        this.client = client;
        this.activity = activity;
        this.date = date;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }
    
    Subscriptions() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Activities getActivity() {
        return activity;
    }

    public void setActivity(Activities activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Subscriptions))
            return false;
        Subscriptions subscriptions = (Subscriptions) o;
        return Objects.equals(this.id, subscriptions.id) && Objects.equals(this.client, subscriptions.client)
                && Objects.equals(this.activity, subscriptions.activity)
                && Objects.equals(this.date, subscriptions.date)
                && Objects.equals(this.price, subscriptions.price)
                && Objects.equals(this.paymentStatus, subscriptions.paymentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.client, this.activity, this.date, this.price, this.paymentStatus);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", client='" + this.client + '\'' + ", activity='" + this.activity + '\'' +
                ", date='" + this.date + ", price='" + this.paymentStatus + ", price='" + this.paymentStatus + '}';
    }
}
