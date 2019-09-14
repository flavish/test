package flavish.test.repository;

import flavish.test.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProjectRepository extends JpaRepository<Project, BigInteger> {
}
