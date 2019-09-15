package flavish.test.service;

import flavish.test.model.Project;
import flavish.test.service.model.ProjectService;
import flavish.test.service.parser.DOMParserService;
import flavish.test.service.parser.ParserService;
import flavish.test.service.parser.SAXParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImportService {
    private final DOMParserService domParserService;
    private final SAXParserService saxParserService;
    private final ProjectService projectService;

    @Value("${dom.xml.size.max}")
    private Long domMaxXmlSize;

    @Transactional
    public void importData(MultipartFile file) throws IOException, ParserConfigurationException, SAXException {
        List<Project> projects = getParserService(file.getSize()).parse(file.getInputStream());
        projectService.saveAll(projects);
    }

    private ParserService getParserService(Long fileSize) {
        return (domMaxXmlSize != null && fileSize < domMaxXmlSize)
                ? domParserService : saxParserService;
    }
}
