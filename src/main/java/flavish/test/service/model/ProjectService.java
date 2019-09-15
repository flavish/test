package flavish.test.service.model;

import flavish.test.model.Project;
import flavish.test.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> saveAll(List<Project> projects) {
        return projectRepository.saveAll(projects);
    }

    public Project findByKey(String key) {
        return projectRepository.findByKey(key);
    }
}
