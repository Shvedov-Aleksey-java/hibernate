package hibernate.starter.InheritanceMappingStrategies.joined;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Programmer extends User {
    private Language language;

    @Builder
    public Programmer(Long id, String username, Language language) {
        super(id, username);
        this.language = language;
    }

}
