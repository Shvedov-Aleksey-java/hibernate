package hibernate.starter.InheritanceMappingStrategies.singleTable;



import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("manager")
public class Manager extends User {
    private String project;

    @Builder
    public Manager(Long id, String username, String project) {
        super(id, username);
        this.project = project;
    }
}
