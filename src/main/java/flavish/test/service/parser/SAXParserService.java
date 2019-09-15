package flavish.test.service.parser;

import flavish.test.model.BaseEntity;
import flavish.test.model.Issue;
import flavish.test.model.Project;
import flavish.test.utils.ValidatorUtils;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SAXParserService implements ParserService {
    private List<Project> projects;

    @Override
    public synchronized List<Project> parse(InputStream stream) throws ParserConfigurationException, SAXException, IOException {
        projects = new ArrayList<>();
        InputSource inputSource = new InputSource(stream);
        SAXSource source = new SAXSource(inputSource);
        SAXResult saxResult = new SAXResult(new SAXParserHandler());
        ValidatorUtils.initValidator(getClass().getClassLoader().getResource("projects.xsd")).validate(source, saxResult);
        ArrayList<Project> result = new ArrayList<>(this.projects);
        projects = null;
        return result;
    }

    private class SAXParserHandler extends DefaultHandler {
        private Project project;
        private Issue issue;
        private StringBuilder content;
        private boolean isProject;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            switch (qName) {
                case XML_PROJECT_ELEMENT:
                    project = new Project();
                    isProject = true;
                    break;
                case XML_TASK_ELEMENT:
                    issue = new Issue();
                    isProject = false;
                    break;
                case XML_KEY_ELEMENT:
                case XML_SUMMARY_ELEMENT:
                case XML_DESCRIPTION_ELEMENT:
                    content = new StringBuilder();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            switch (qName) {
                case XML_PROJECT_ELEMENT:
                    projects.add(project);
                    project = null;
                    break;
                case XML_TASK_ELEMENT:
                    issue.setProject(project);
                    project.getIssues().add(issue);
                    issue = null;
                    break;
                case XML_KEY_ELEMENT:
                    getBaseEntity().setKey(content.toString().trim());
                    content = null;
                    break;
                case XML_SUMMARY_ELEMENT:
                    getBaseEntity().setSummary(content.toString().trim());
                    content = null;
                    break;
                case XML_DESCRIPTION_ELEMENT:
                    issue.setDescription(content.toString().trim());
                    content = null;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (content != null) {
                content.append(ch, start, length);
            }
        }

        private BaseEntity getBaseEntity() {
            return isProject ? project : issue;
        }
    }
}
