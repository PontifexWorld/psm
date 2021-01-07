package com.example.psm.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.IllegalSQLInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-11-10 12:44
 **/
@Configuration
public class MybatisPlusConfig {

    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //分页查询上限重新赋值
        paginationInterceptor.setLimit(100000);

        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        // 动态表名SQL解析器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        // Map的key就是需要替换的原始表名
        tableNameHandlerMap.put("user_vo", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                // 自定义表名规则，或者从配置文件、request上下文中读取
                // 假设这里的用户表根据年份来进行分表操作
                Date date = new Date();
                //DateTimeFormatter dateTimeFormatter = new DateTimeFormatter(LocalDateTime.now());
                String year = String.format("_%tY%tm", date,date);
                // 返回最后需要操作的表名，table_name_YYYYMM月表
                System.out.println("======================================="+myTableName.get());
                return myTableName.get();
            }
        });
        tableNameHandlerMap.put("t_a_dc_check_xxx2", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                // 自定义表名规则，或者从配置文件、request上下文中读取
                // 假设这里的用户表根据年份来进行分表操作
                Date date = new Date();
                //DateTimeFormatter dateTimeFormatter = new DateTimeFormatter(LocalDateTime.now());
                String year = String.format("_%tY%tm", date,date);
                // 返回最后需要操作的表名，sys_user_2019
                return "t_a_dc_check_xxx2" + year;
            }
        });

        //增加一个销账月表表名过滤器
        tableNameHandlerMap.put("dt_xxx_item_month", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableName.get();//返回null不会替换 注意 多租户过滤会将它一块过滤不会替换@SqlParser(filter=true) 可不会替换
            }
        });

        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }

}