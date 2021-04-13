package com.server.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;


@Entity //указывает, что данный бин (класс) является сущностью.
@EnableAutoConfiguration //позволяет делать автоматическое создание таблицы
@Table(name = "areas") //указывает на имя таблицы, которая будет отображаться в этой сущности.
public class Areas {
    @Id
    //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице)
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что данное свойство будет создаваться согласно указанной стратегии.
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy = "area")
    private Activities activities;

    public Areas(String name) {
        this.name = name;
    }
    
    Areas() {}


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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Areas))
            return false;
        Areas areas = (Areas) o;
        return Objects.equals(this.id, areas.id) && Objects.equals(this.name, areas.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
