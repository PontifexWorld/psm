package com.example.psm.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * 实体类转换 配置lombok使用节俭代码
 * @author lsy
 * @version 1.0
 * @date 2020-12-22 10:16
 **/
@Component
public class GeneralConverter {

    @Autowired
    public static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * List  实体类 转换器
     * @param source 原数据
     * @param clz    转换类型
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> converter(List<S> source, Class<T> clz) {
        if (Objects.isNull(source)) return null;
        List<T> map = Lists.newArrayList();
        for (S s : source) {
            map.add(mapper.map(s, clz));
        }
        return map;
    }

    /**
     * Set 实体类 深度转换器
     * @param source 原数据
     * @param clz    目标对象
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> Set<T> converter(Set<S> source, Class<T> clz) {
        if (Objects.isNull(source)) return null;
        Set<T> set = new TreeSet<>();
        for (S s : source) {
            set.add(mapper.map(s, clz));
        }
        return set;
    }

    /**
     * 实体类 深度转换器
     * @param source 原数据
     * @param clz 目标对象
     * @param <T> 实体
     * @param <S>
     * @return
     */
    public static <T, S> T converter(S source, Class<T> clz) {
        if (Objects.isNull(source)) return null;
        return mapper.map(source, clz);
    }

    public static void converter(Object source, Object object) {
        mapper.map(source, object);
    }

    public static <T> void copyConverter(T source, Object object) {

        mapper.map(source, object);
    }

}