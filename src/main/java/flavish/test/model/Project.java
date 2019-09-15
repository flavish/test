package flavish.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
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
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Issue> issues = new HashSet<>();
}
