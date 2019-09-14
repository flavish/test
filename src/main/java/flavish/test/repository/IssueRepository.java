package flavish.test.repository;

import flavish.test.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IssueRepository extends JpaRepository<Issue, BigInteger> {
}
