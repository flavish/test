package flavish.test.service.parser;

import flavish.test.model.Issue;
import flavish.test.model.Project;
import flavish.test.utils.DOMElementUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DOMParserService implements ParserService {

    @Override
    public List<Project> parse(InputStream stream) throws ParserConfigurationException, IOException, SAXException {
        Document doc = initParser().parse(stream);
        return getProjects(doc);
    }

    private ArrayList<Project> getProjects(Document doc) {
        ArrayList<Project> projects = new ArrayList<>();
        NodeList elements = doc.getElementsByTagName(XML_PROJECT_ELEMENT);
        for (int i = 0; i < elements.getLength(); i++) {
            Element item = (Element) elements.item(i);
            projects.add(getProject(item));
        }
        return projects;
    }

    private Project getProject(Element element) {
        Project project = new Project();
        project.setKey(DOMElementUtils.getChildElementTextContent(element, XML_KEY_ELEMENT));
        project.setSummary(DOMElementUtils.getChildElementTextContent(element, XML_SUMMARY_ELEMENT));
        project.setIssues(getIssues(element, project));
        return project;
    }

    private Set<Issue> getIssues(Element parentElement, Project project) {
        Set<Issue> issues = new HashSet<>();
        NodeList elements = parentElement.getElementsByTagName(XML_TASK_ELEMENT);
        for (int i = 0; i < elements.getLength(); i++) {
            Element item = (Element) elements.item(i);
            issues.add(getIssue(item, project));
        }
        return issues;
    }

    private Issue getIssue(Element element, Project project) {
        Issue issue = new Issue();
        issue.setKey(DOMElementUtils.getChildElementTextContent(element, XML_KEY_ELEMENT));
        issue.setSummary(DOMElementUtils.getChildElementTextContent(element, XML_SUMMARY_ELEMENT));
        issue.setDescription(DOMElementUtils.getChildElementTextContent(element, XML_DESCRIPTION_ELEMENT));
        issue.setProject(project);
        return issue;
    }

    private DocumentBuilder initParser() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
}
