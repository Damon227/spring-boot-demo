package pers.ycm.sbdefault.filter;

import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 敏感信息过滤器。读取response，处理特定字段。
 *
 * @author yuanchengman
 * @date 2021-01-25
 */
public class SensitiveDataFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ContentCachingResponseWrapper responseWrapper = null;

        if (servletResponse instanceof HttpServletResponse) {
            responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        }

        try {
            if (responseWrapper == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, responseWrapper);
            }

        } finally {
            byte[] array = responseWrapper.getContentAsByteArray();
            String s = new String(array, StandardCharsets.UTF_8);
            System.out.println(1);
            responseWrapper.copyBodyToResponse();
        }
    }
}
