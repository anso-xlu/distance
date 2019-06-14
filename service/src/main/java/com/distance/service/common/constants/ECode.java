package com.distance.service.common.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ECode {
    G_200("ok", HttpStatus.OK),
    G_404("资源不存在", HttpStatus.NOT_FOUND),
    G_500("未知异常", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * ---------------- global related: 99 ----------------------------
     */
    G_0001("参数错误", HttpStatus.BAD_REQUEST),
    G_0002("参数为空", HttpStatus.BAD_REQUEST),

    G_0003("数据无法解析成JSON", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    G_0004("数据格式不支持", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    G_0005("请求方法错误", HttpStatus.UNSUPPORTED_MEDIA_TYPE),

    /**
     * -------------- user related: 10 ------------------------
     */
    U_0001("请登陆!", HttpStatus.UNAUTHORIZED),
    U_0002("账户名或密码错误!", HttpStatus.UNAUTHORIZED),
    U_0003("密码过期!", HttpStatus.UNAUTHORIZED),
    U_0004("账户过期!", HttpStatus.UNAUTHORIZED),
    U_0005("账户被锁定!", HttpStatus.UNAUTHORIZED),
    U_0006("账户被禁用!", HttpStatus.UNAUTHORIZED),
    U_0007("登录失败!", HttpStatus.UNAUTHORIZED),
    U_0008("权限不足", HttpStatus.FORBIDDEN),
    ;
    /**
     * 异常信息
     */
    private final String msg;
    /**
     * httpStatus
     */
    private final HttpStatus status;

    ECode(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    public int getCode() {
        String[] split = this.name().split("_");
        Integer code = Integer.valueOf(split[1]);
        if (split[1].length() == 3) {
            return code;
        } else {
            switch (split[0]) {
                case "G":
                    return 99 * 100 + code;
                case "U":
                    return 10 * 100 + code;
                default:
                    return 98 * code;

            }
        }
    }
}
