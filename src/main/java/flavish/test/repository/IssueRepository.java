package flavish.test.repository;

import flavish.test.model.Issue;
import flavish.test.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, BigInteger> {
    Issue findByKey(String key);
    List<Issue> findAllByProject(Project project);
}
