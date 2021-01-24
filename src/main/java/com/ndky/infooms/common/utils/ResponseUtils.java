package com.ndky.infooms.common.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:59
 */
public class ResponseUtils {

    public static void print(HttpServletResponse response, Object... object) throws IOException, ServletException {
        PrintWriter writer = utf8AndJson(response).getWriter();
        for (Object o : object) {
            writer.print(o);
        }
        writer.flush();
        writer.close();
    }

    private static HttpServletResponse utf8AndJson(HttpServletResponse response) {
        response.setContentType("text/json;charset=utf-8");
        return response;
    }
}
