package hibernate.starter.newLessonOne;

import hibernate.starter.newLessonOne.One.BaseEntity;
import hibernate.starter.newLessonOne.two.NewExtendsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * анатация @OrderBy("id DESC") позволяет сортировать коллекцыю средствами SQl
 * анатация @OrderColumn(name = "id") позволяет сортировать коллекцыю средствами приложения
 * анатация @SortNatural вместе с анатацией @OrderColumn(name = "id") и Pet имплементирует
 * итерфэйс comparable позволяет сортировать по компоратору коллекция: new TreeSet<>();
 * анатация @MapKey(name = "name") получаем коллекцию питомцов которая мапица на юзера
 * и мы можем достовать их из коллекции где имя питомца должно быть уникальным
 */

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users extends NewExtendsId<Long> {
    private String name;
    private String description;
    private LocalDate createDay;
    @OneToMany
    @Builder.Default
    @OrderBy("id DESC")
    @OrderColumn(name = "id")
    @SortNatural
    private Set<Pet> pets = new TreeSet<>(Pet::compareTo);
    @Builder.Default
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    private Map<Long, Pet> usersMap = new HashMap<>();

}
