package flavish.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    @OneToMany(mappedBy = "project")
    private Set<Issue> issues = new HashSet<>();
}
