package flavish.test.service.model;

import flavish.test.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final IssueRepository issueRepository;

}
