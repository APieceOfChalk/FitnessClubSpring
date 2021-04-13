package com.server.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@EnableAutoConfiguration
@Table(name = "activities")
public class Activities implements Serializable {

    @Id
    //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице)
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что данное свойство будет создаваться согласно указанной стратегии.
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = false)
    private Trainers trainer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private Areas area;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
    private List<Subscriptions> subscriptions = new ArrayList<>();

    public Activities(String name, Trainers trainer, Areas area) {
        this.name = name;
        this.trainer = trainer;
        this.area = area;
    }

    Activities() {}

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

    public Trainers getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainers trainer) {
        this.trainer = trainer;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Activities))
            return false;
        Activities activities = (Activities) o;
        return Objects.equals(this.id, activities.id) && Objects.equals(this.name, activities.name)
                && Objects.equals(this.trainer, activities.trainer) && Objects.equals(this.area, activities.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.trainer, this.area);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' +
                ", trainer_id='" + this.trainer + '\'' + ", area_id='" + this.area + '\'' + '}';
    }
}
