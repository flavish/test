package flavish.test.utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMElementUtils {

    public static String getChildElementTextContent(Element parentElement, String tagName) {
        NodeList elementsByTagName = parentElement.getElementsByTagName(tagName);
        if (elementsByTagName.getLength() == 0) {
            return null;
        }
        return elementsByTagName.item(0).getTextContent();
    }
}
