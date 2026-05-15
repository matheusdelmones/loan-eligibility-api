package CodeBank.com.Master.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Float income;
    private String location;   
    
    @Column(unique = true) 
    private String cpf;

    public User(String name, Integer age, Float income, String location, String cpf) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.location = location;
        this.cpf = cpf;
    }

}
