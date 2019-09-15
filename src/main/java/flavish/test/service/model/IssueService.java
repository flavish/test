package flavish.test.service.model;

import flavish.test.model.Issue;
import flavish.test.model.Project;
import flavish.test.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final ProjectService projectService;

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    public List<Issue> findByProjectKey(String projectKey) {
        Project project = projectService.findByKey(projectKey);
        if (project == null) {
            return new ArrayList<>();
        }
        return issueRepository.findAllByProject(project);
    }

    public Issue findByKey(String key) {
        return issueRepository.findByKey(key);
    }

}
