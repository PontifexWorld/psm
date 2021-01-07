package com.example.psm;

import com.example.aspect.log.OperLog;
import com.example.psm.config.MybatisPlusConfig;
import com.example.psm.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-23 17:45
 **/
@RestController
@RequestMapping(value = "psm")
public class PsmController {

    public static final Logger logger = LoggerFactory.getLogger(PsmController.class);

    public Integer kop = 0;

    @Autowired
    UserDao user;

    @RequestMapping(value = "demo")
    public String psmTest(MultipartFile file) {

        System.out.println(file.getOriginalFilename()+"====---");

        System.out.println("进入控制层");
        MybatisPlusConfig.myTableName.set("user_2019");
        System.out.println("2133333");
        System.out.println("00000000000000");
        MybatisPlusConfig.myTableName.set("user_2018");
        UserVo userV2 = user.selectById(1);
        System.out.println(userV2);
        return "success";
    }


    @RequestMapping(value = "oom")
    public String psmOom(){
        Long L1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            logger.info("logger info " +i);
            try {
                for (int j = 0; j < 10; j++) {
                   String sa = "123123";
                }
                Thread.sleep(1000*2);
                logger.info("剩余执行个数{}",50000-i);
            } catch (InterruptedException e) {}
        }
        logger.info("操作人: {},一共执行了{}时间","lsy",System.currentTimeMillis()-L1);
        return "执行完成";
    }

    @RequestMapping(value = "oms")
    @OperLog(operModul = "測試model",operType = "添加信息",operDesc = "測試數據信息")
    public User psmOms(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreateTime(LocalDateTime.now());
        orderDTO.setUpdateTime(new Date());
        System.out.println(orderDTO.toString());
        User user = new User();
        if (user.getId() == null) {
            throw new NullPointerException();
        }
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return user;
    }

    public int Test () {
         int i = 0;
        i ++;
        return i;
    }




    private byte[] bytes;
    private int initSize;



    public void set(int number) {
        //相当于对一个数字进行右移动3位，相当于除以8
        int index = number >> 3;
        //相当于 number % 8 获取到byte[index]的位置
        int position = number & 0x07;
        //进行|或运算  参加运算的两个对象只要有一个为1，其值为1。
        bytes[index] |= 1 << position;
    }

    public boolean contain(int number) {
        int index = number >> 3;
        int position = number & 0x07;
        return (bytes[index] & (1 << position)) != 0;
    }

    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();
        map.put(null,"");
        System.out.println(map);




        logger.info("{}","分界线");

        Predicate<Integer> predicate = x -> x > 185;
        Student student = new Student("9龙", 23, 175);
        System.out.println(
                "9龙的身高高于185吗？：" + predicate.test(student.getStature()));

        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        Supplier<Integer> supplier =
                () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println(integer);
        test(() -> "我是一个演示的函数式接口");

        int a = 60;
        int b = 3;
        System.out.println(~a);
        System.out.println(a|=b);
        System.out.println(a&=b);
        System.out.println(a^=b);

    }

    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }

}