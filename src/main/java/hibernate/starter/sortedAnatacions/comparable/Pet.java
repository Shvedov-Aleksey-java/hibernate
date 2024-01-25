package hibernate.starter.sortedAnatacions.comparable;


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
public class Pet implements Comparable<Pet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean gender;
    @ManyToOne
    private Users owner;

    @Override
    public int compareTo(Pet o) {
        return this.gender.compareTo(o.gender);
    }
}
