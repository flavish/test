package flavish.test.rest;

import flavish.test.service.ImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/import")
public class ImportController {
    private final ImportService importService;

    @PostMapping
    public ResponseEntity importData(@RequestParam("file") MultipartFile file) {
        if (!MediaType.APPLICATION_XML_VALUE.equals(file.getContentType())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid file type");
        }
        try {
            importService.importData(file);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (Exception e) {
            log.error("Import error", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
