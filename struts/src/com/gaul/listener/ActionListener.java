package com.gaul.listener;

import com.gaul.bean.XmlBean;
import com.gaul.util.XmlParser;
import org.dom4j.DocumentException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

/**
 * Created by gao on 2015/9/17.
 */
public class ActionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("message: system is initializing...");
        ServletContext context = servletContextEvent.getServletContext();

        String contextPath = context.getRealPath("\\");
        String classPath = context.getInitParameter("strutsConfig");

        Map<String, XmlBean> map = null;
        try {
            map = XmlParser.parseXml(contextPath + classPath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        context.setAttribute("xmlMap", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("message: system is shutting down...");
    }
}
