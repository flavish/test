package flavish.test.rest;

import flavish.test.model.Issue;
import flavish.test.service.model.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/issue")
public class IssueController {
    private final IssueService issueService;

    @GetMapping("/all")
    public ResponseEntity findAll() {
        try {
            List<Issue> issues = issueService.findAll();
            return ResponseEntity.ok(issues);
        } catch (Exception e) {
            log.error("Issue controller error", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/all/{projectKey}")
    public ResponseEntity findByProjectKey(@PathVariable(name = "projectKey") String projectKey) {
        try {
            List<Issue> issues = issueService.findByProjectKey(projectKey);
            return ResponseEntity.ok(issues);
        } catch (Exception e) {
            log.error("Issue controller error", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{key}")
    public ResponseEntity findByKey(@PathVariable(name = "key") String key) {
        try {
            Issue issue = issueService.findByKey(key);
            return ResponseEntity.ok(issue);
        } catch (Exception e) {
            log.error("Issue controller error", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
