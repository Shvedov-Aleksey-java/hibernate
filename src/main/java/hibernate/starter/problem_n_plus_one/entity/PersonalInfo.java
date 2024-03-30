package hibernate.starter.problem_n_plus_one.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private String firstname;
    private String lastname;

}
