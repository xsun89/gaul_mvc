package com.gaul.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gao on 2015/9/17.
 */
public class XmlBean {
    public XmlBean(){}

    private String path;
    private String actionClass;
    private String formBean;
    private Map<String, String> resultMap = new HashMap<String, String>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getActionClass() {
        return actionClass;
    }

    public void setActionClass(String actionClass) {
        this.actionClass = actionClass;
    }

    public String getFormBean() {
        return formBean;
    }

    public void setFormBean(String formBean) {
        this.formBean = formBean;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }
}
