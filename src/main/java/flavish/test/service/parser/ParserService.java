package flavish.test.service.parser;

import flavish.test.model.Project;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ParserService {
    String XML_PROJECT_ELEMENT = "project";
    String XML_TASK_ELEMENT = "task";
    String XML_KEY_ELEMENT = "key";
    String XML_SUMMARY_ELEMENT = "summary";
    String XML_DESCRIPTION_ELEMENT = "description";

    List<Project> parse(InputStream stream) throws ParserConfigurationException, SAXException, IOException;
}
