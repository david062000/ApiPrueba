package co.com.pichincha.transactions.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false, unique = true)
    @Size(min = 8, max = 10, message = "It should be into 8 and 10 characters")
    private String identification;

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false)
    @Size(min = 4, max = 40, message = "It should be into 4 and 40 characters")
    private String name;

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false)
    @Size(min = 1, max = 1, message = "It should be into 1 and 1 characters")
    private String gender;

    @Column(nullable = false)
    private int age;

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false)
    @Size(min = 5, max = 40, message = "It should be into 5 and 20 characters")
    private String address;

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false)
    @Size(min = 9, max = 9, message = "It should be into 7 and 13 characters")
    private String telephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
