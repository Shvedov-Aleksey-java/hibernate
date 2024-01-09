package hibernate.starter.entity;

import hibernate.starter.convertor.BirthDayConvertor;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private String firstname;
    private String lastname;
    @Convert(converter = BirthDayConvertor.class)
    private BirthDay birthDate;
}
