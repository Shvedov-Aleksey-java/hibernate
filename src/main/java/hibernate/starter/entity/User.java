package hibernate.starter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 анатация @ToString(exclude = "company") зделанна для того что бы работал ManyToOne
 исключаем поле компани из тустринг что бы не вызвать ошибку стэкОверФлоу переполнения к мамяти
 тем самым мы оброщаемся только к бд для вывода результатаов в тестовом классе тест checkOneToMany
 для наглядного примера
 */
@Data
@Entity
@ToString(exclude = {"company", "profile"})
//@EqualsAndHashCode(exclude = "company")
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Embedded
    private PersonalInfo personalInfo;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "users_chat",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<Chat> chats = new ArrayList<>();
    public void addChat(Chat chat) {
        chats.add(chat);
        chat.getUsers().add(this);
    }
}
