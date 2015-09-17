package com.gaul.action;

import com.gaul.bean.XmlBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by gao on 2015/9/17.
 */
public class ActionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = this.getPath(req.getServletPath());
        ServletContext context = req.getServletContext();
        Map<String, XmlBean> xmlMap = (Map<String, XmlBean>) context.getAttribute("xmlMap");
        XmlBean xmlBean = xmlMap.get(path);

        Action action = null;
        try {
            Class clazz = Class.forName(xmlBean.getActionClass());
            action = (Action) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = action.execute(req, resp, xmlBean.getResultMap());
        req.getRequestDispatcher(result).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service(req, resp);
    }

    private String getPath(String path) {
        return path.split("\\.do")[0];
    }
}
