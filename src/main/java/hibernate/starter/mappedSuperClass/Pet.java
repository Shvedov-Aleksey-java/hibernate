package hibernate.starter.mappedSuperClass;


import jakarta.persistence.*;
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
    private Users owner;
}
