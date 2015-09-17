package com.gaul.util;

import com.gaul.bean.XmlBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gao on 2015/9/17.
 */
public class XmlParser {
    public static Map<String, XmlBean> parseXml(String xmlPath) throws DocumentException{
        Map<String, XmlBean> xmlBeans = new HashMap<String, XmlBean>();

        SAXReader reader = new SAXReader();
        Document document =  reader.read(new File(xmlPath));

        Element rootElement = document.getRootElement();
        Element actionMappings = rootElement.element("action-mappings");
        List<Element> actions = actionMappings.elements("action");

        for (Element action : actions) {
            XmlBean xmlBean = new XmlBean();

            String name = action.attributeValue("name");

            //获取bean对应的class
            Element forms = rootElement.element("formbeans");
            List<Element> formBeans = forms.elements("formbean");

            String formClass = null;
            for (Element formBean : formBeans) {
                if(name.equals(formBean.attributeValue("name"))) {
                    formClass = formBean.attributeValue("value");
                    break;
                }
            }

            String actionClass = action.attributeValue("class");
            String path = action.attributeValue("path");

            List<Element> results = action.elements("result");
            for (Element result : results) {
                String resultName = result.attributeValue("name");
                String resultValue = result.attributeValue("value");
                xmlBean.getResultMap().put(resultName, resultValue);
            }

            xmlBean.setActionClass(actionClass);
            xmlBean.setFormBean(formClass);
            xmlBean.setPath(path);

            xmlBeans.put(path, xmlBean);
        }
        
        return xmlBeans;
    }
}
