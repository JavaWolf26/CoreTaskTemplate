package jm.task.core.jdbc.model;

import javax.persistence.*;

@Table(name = "user_table", schema = "user")
public class User {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private Byte age;

    public User() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User {" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", age: " + age +
                '}';
    }
}