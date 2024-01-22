package hibernate.starter.newLessonOne;

import hibernate.starter.newLessonOne.One.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends AuditableEntity<Long> implements Comparable<Pet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users owner;
    @Override
    public int compareTo(Pet o) {
        return o.getGender() ? 1 : -1;
    }
}
