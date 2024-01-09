package hibernate.starter.entity;

import hibernate.starter.convertor.BirthDayConvertor;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1034149667309624302L;
    private String firstname;
    private String lastname;
    @Convert(converter = BirthDayConvertor.class)
    private BirthDay birthDate;
}
