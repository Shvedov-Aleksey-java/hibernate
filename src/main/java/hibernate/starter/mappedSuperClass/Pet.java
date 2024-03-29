package hibernate.starter.mappedSuperClass;


import javax.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends MappedSuperClass<Long, String> {
    @ManyToOne
    private User owner;
}
