package hibernate.starter.problem_n_plus_one.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * анатацыя @BatchSize(size = n) ограничивает выгрузку до определенного числа и делает это одним запросом
 */


/**
 * онатацыя @FetchProfile делает так что бы выгрузка из бд происходила сразу с присоеденением через джоин
 * но что бы ее использовать в hibernate нужно использовать session.enableFetchProfile("name")
 * где name это: @FetchProfile(name = "fetchChat", имя профиля
 */
@FetchProfile(name = "fetchChat", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = Chat.class, association = "userChats", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = Company.class, association = "companies", mode = FetchMode.JOIN)
})
/**
 * анатацыя @NamedEntityGraph один из лучших выриантов решения этой проблемы тут также отрибут "name"
 * это имя котороу будем использовать в вызове определенного метода в сессии тут мы используем так же
 * два поля "companies" и "userChats" так же мы используем: subgraph = "chats" чтобы так же заджинить
 * поля из таблицы на которую мы уже делаем джоин
 * реализацыя использования:
 * var userGraph = session.createEntityGraph(Chat.class);
 *         userGraph.addAttributeNodes("company", "userChats");
 *         var userChatSubgraph = userGraph.addSubgraph("UserChat", UserChat.class);
 *         userChatSubgraph.addAttributeNodes("chat");
 *         Map<String, Object> properties = Map.of(
 *                 GraphSemantic.LOAD.getJpaHintName(), userGraph
 *         );
 *         var chatFind = session.find(Chat.class, 1L, properties);
 *         System.out.println(chatFind.getUser().getUsername());
 *         System.out.println(chatFind.getUserChats().size());
 *         var userQuery = session.createQuery(
 *                 "select u from User u", Chat.class)
 *                 .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
 *                 .list();
 *         userQuery.forEach(it -> System.out.println(it.getUser().getUsername()));
 *         userQuery.forEach(it -> System.out.println(it.getUserChats().size()));
 *
 *         перед использованием требуется нормально настроить связи здесь используется
 *         демонстративнвя версия!
 */
@NamedEntityGraph(
        name = "graph",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "userChats", subgraph = "chats")
        },
        subgraphs = {
                @NamedSubgraph(name = "chats", attributeNodes = @NamedAttributeNode("chat"))
        }
)
@Data
@Entity
@Table(name = "chat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "chat")
    @BatchSize(size = 1)
    private List<UserChat> userChats = new ArrayList<>();
    @OneToMany(mappedBy = "chat")
    @BatchSize(size = 1)
    private List<Company> companies = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
}
