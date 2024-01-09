package hibernate.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    @EmbeddedId
    private PersonalInfo personalInfo;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
