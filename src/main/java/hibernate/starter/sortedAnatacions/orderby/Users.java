package hibernate.starter.sortedAnatacions.orderby;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * анатация @OrderBy("id DESC") позволяет сортировать коллекцыю средствами SQl
 * так же указываем порядок сортировки DESC или ASC
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
    @OrderBy("id DESC")
    private List<Pet> usersMap = new ArrayList<>();

}
