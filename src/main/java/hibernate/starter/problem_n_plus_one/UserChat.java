package hibernate.starter.problem_n_plus_one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "users_chat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    private Instant createdAt;
    private String createdBy;

    public void setUser(User user) {
        this.user = user;
        user.getUserChats().add(this);
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        chat.getUserChats().add(this);
    }
}
