package hibernate.starter.sortedAnatacions.comparable;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;

import java.util.Set;
import java.util.TreeSet;

/**
 * анатация @SortNatural вместе с анатацией @OrderColumn(name = "id")
 * позволяет сортировать коллекции(любую) по компоратору.
 * класс должен быть обезательно унаследован от интерфейса Comparable.
 */

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @OrderColumn(name = "id")
    @SortNatural
    private Set<Pet> pets = new TreeSet<>(Pet::compareTo);
}
