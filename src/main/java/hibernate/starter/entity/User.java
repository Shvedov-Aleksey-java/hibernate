package hibernate.starter.entity;

import hibernate.starter.convertor.BirthDayConvertor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;



@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    @Convert(converter = BirthDayConvertor.class)
    private BirthDay birthDate;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
