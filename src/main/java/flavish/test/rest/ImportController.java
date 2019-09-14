package flavish.test.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/import")
public class ImportController {

    @PostMapping
    public ResponseEntity importData(@RequestParam("file") MultipartFile file) {
        if (!MediaType.APPLICATION_XML_VALUE.equals(file.getContentType())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid file type");
        }
        return ResponseEntity
                .ok()
                .build();
    }
}
