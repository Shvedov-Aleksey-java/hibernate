package hibernate.starter.sortedAnatacions.ordercolumn;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * анатация @OrderColumn(name = "id") позволяет сортировать коллекцыю средствами приложения
 * (name = "id"): поле по которому будет происходить сортировка
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
    private List<Pet> usersMap = new ArrayList<>();

}
