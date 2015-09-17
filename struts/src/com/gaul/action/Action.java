package com.gaul.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by gao on 2015/9/17.
 */
public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response, Map<String, String> resultMap);
}
