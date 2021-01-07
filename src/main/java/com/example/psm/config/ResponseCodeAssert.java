package com.example.psm.config;

import lombok.Getter;

/**
 * 响应结构状态码枚举类
 *
 * @author Chao.yy
 * @version 1.0
 * @since 2020/7/8 8:30
 */
@Getter
public enum ResponseCodeAssert implements BusinessAssert {
    /**
     * 响应服务请求的状态码与说明
     */
    Success("00000", "成功"),
    Fail("99999", "失败"),
    UserValidateFail("A0001", "用户端错误"),
    UserRegisterFail("A0100", "用户注册错误"),
    UserNameValidateFail("A0110", "用户名校验失败"),
    UserNameExist("A0111", "用户名已存在"),
    UserNameInvalid("A0112", "用户名包含特殊字符"),
    PasswordValidateFail("A0120", "密码校验失败"),
    PasswordLengthValid("A0121", "密码长度不够"),
    ShortMessageValidFail("A0130", "短信校验码输入错误"),
    UserLoginException("A0200", "用户登陆异常"),
    UserAccountNotExist("A0201", "用户账户不存在"),
    RequestParamError("A0300", "用户请求参数错误"),
    InvalidUserInput("A0301", "无效的用户输入"),
    RequiredParamIsEmpty("A0310", "请求必填参数为空"),
    InvalidTimeStamp("A0311", "非法的时间戳参数"),
    UserInputInvalid("A0312", "用户输入内容非法"),
    ValidateCodeExpire("A0400", "验证码过期"),
    FormValidateFail("A0401", "表单校验失败"),
    ParamValidateFail("A0402", "参数校验失败"),
    ParamBindFail("A0403", "参数绑定失败"),
    PhoneAlreadyRegister("A0405", "手机号已经注册"),
    SystemException("B0001", "系统执行异常：{0}"),
    ResourceDisabled("B0002", "资源被禁用"),
    ResourceNoAuthority("B0003", "该资源未定义访问权限"),
    AccessDenied("B0004", "访问权限不足"),
    ValidateCodeError("B0005", "验证码错误！"),
    PhoneNumNotFound("B0006", "找不到该用户，手机号码有误：：{0}"),
    BusinessFail("B0007", "{0}"),
    Timeout("B0100", "系统执行超时"),
    StrikeRecovery("B0200", "系统容灾系统被触发"),
    RpcInvokeError("C0001", "调用第三方服务出错"),
    ServerError("C0002", "服务器内部错误"),
    UnknownError("C0003", "未知异常");


    /**
     * 信息
     */
    private final String message;

    /**
     * 编码
     */
    private final String code;


    /**
     * 全参构造
     *
     * @param code    编码
     * @param message 编码名称
     * @author Chao.yy
     * @since 2020/7/8 8:51
     */
    ResponseCodeAssert(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过编码获取名称
     *
     * @param code 编码
     * @return java.lang.String
     * @author Chao.yy
     * @since 2020/7/8 8:52
     */
    public static String getMessageByCode(String code) {
        for (ResponseCodeAssert resultCodeEnum :  ResponseCodeAssert.values()) {
            if (resultCodeEnum.code.equals(code)) {
                return resultCodeEnum.message;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return getCode()+":"+getMessage();
    }
    /**
     * 创建异常
     * @param e  异常对象
     * @return
     */
    @Override
    public BusinessException newException(Object e){
        return new BusinessException(this.getCode(),this.getMessage(),e);
    }


    /**
     * 创建异常
     * @param e 异常对象
     * @param args  占位符信息
     * @return
     */
    @Override
    public BusinessException newException(Object e, Object... args){
        return new BusinessException(this.getCode(),this.getMessage(),e,args);
    }
}
