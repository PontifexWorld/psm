package com.example.psm.config;


import com.example.lesscode.util.JsonUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Description 对访问的请求进行过滤
 * @Author: liubicong
 * @Date: 2020/7/23 6:53 下午
 */
@WebFilter(urlPatterns = "/*")
@Order(1)
@ConditionalOnExpression
public class CharacterFilter implements Filter {

    private LessCodeConfigProperties configProperties;
    private List<String> characterFilterList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        configProperties = applicationContext.getBean(LessCodeConfigProperties.class);
        if (!StringUtils.isEmpty(configProperties.getCharacterFilter())){
            String[] characterFilterArray = configProperties.getCharacterFilter().split("\\|");
            String[] newArray = Arrays.copyOf(characterFilterArray, characterFilterArray.length + 1);
            //手动添加 |
            newArray[newArray.length - 1] = "|";
            characterFilterList = Arrays.asList(newArray);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("===============过滤器进来了‘===========");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String method = httpServletRequest.getMethod();
//        String header = httpServletRequest.getHeader(HttpHeaders.CONTENT_TYPE);
//
//        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
//        Map<String, Object> paramMap = null;
//
//        //针对使用application/json形式提交的请求
//        if (RequestMethod.POST.name().equals(method) && !StringUtils.isEmpty(header) && header.toLowerCase(Locale.ENGLISH).indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) {
//            String paramData = BodyReaderHttpServletRequestWrapper.getBodyString(requestWrapper);
//            paramMap = JsonUtils.deserializeJson(paramData, Map.class);
//        } else {
//            paramMap = new LinkedHashMap<>();
//            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
//            if(!CollectionUtils.isEmpty(parameterMap)){
//                for (String key : parameterMap.keySet()) {
//                    paramMap.put(key, parameterMap.get(key));
//                }
//            }
//        }
//        //针对流的形式提交
//        if (!CollectionUtils.isEmpty(paramMap) && configProperties.isCharacterFilterEnable()) {
//            //验证特殊字符
//            ResponseCodeAssert.BusinessFail.assertBool(checkCharacter(paramMap, characterFilterList), "特殊字符");
//        }
//        filterChain.doFilter(requestWrapper, servletResponse);
    }

    /**
     * 监测提交的串里是否包含特殊字符
     *
     * @param mapList
     * @param filterList
     */
    private boolean checkCharacter(Map<String, Object> mapList, List<String> filterList) {
        Set<Map.Entry<String, Object>> entrySet = mapList.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            checkValue(value,filterList);
        }
        return false;
    }

    private boolean checkValue(Object value,List<String> filterList){

        if(StringUtils.isEmpty(value)){
            return false;
        }

        if (value instanceof Collection) {
            Iterator<Object> iterator1 = ((Collection) value).iterator();
            while (iterator1.hasNext()) {
                Object items = iterator1.next();
                checkValue(items,filterList);
            }
        }else if (value instanceof Map) {
            Map<String, Object> subMap = (Map<String, Object>) value;
            if (checkCharacter(subMap, filterList)) {
                return true;
            }
        } else {
            String[] val = value.toString().toLowerCase(Locale.ENGLISH).split(" ");
            for (String stringVal : val) {
                if (filterList.contains(stringVal)) {
                    return true;
                }
            }
        }
        return false;
    }


}
