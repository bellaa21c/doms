package com.skbroadband.doms.global.constant;

import org.springframework.http.HttpStatus;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.constant
 * @File : ErrorCode
 * @Program :
 * @Date : 2022-12-23
 * @Comment :
 */
public enum ErrorCode {
    INVALID_NO_CONENT(204000, HttpStatus.NO_CONTENT,
            "NO_CONTENT"),


    /* HttpStatus.BAD_REQUEST 400 */
    BAD_REQUST(400000, HttpStatus.BAD_REQUEST,
            "BAD_REQUEST"),

    BAD_REQUST_MISSING_SERVLET_REQUEST_PARAMETER(400001, HttpStatus.BAD_REQUEST,
            "BAD_REQUST_MISSING_SERVLET_REQUEST_PARAMETER"),

    BAD_REQUST_ILLEGALARGUMENT(400002, HttpStatus.BAD_REQUEST,
            "BAD_REQUST_ILLEGALARGUMENT"),

    BAD_REQUST_MISSING_SERVLET_REQUEST_PART(400003, HttpStatus.BAD_REQUEST,
            "BAD_REQUST_MISSING_SERVLET_REQUEST_PART"),

    BAD_REQUST_METHOD_ARGUMENT_TYPE_MISMATCH(400004, HttpStatus.BAD_REQUEST,
            "BAD_REQUST_METHOD_ARGUMENT_TYPE_MISMATCH"),

    BAD_REQUST_SERVLET_REQUEST_BIND(400005, HttpStatus.BAD_REQUEST,
            "BAD_REQUST_SERVLET_REQUEST_BIND"),

    BAD_REQUST_METHOD_ARGUMENT_NOTVALID(400006, HttpStatus.BAD_REQUEST,
            "BAD_REQUST_METHOD_ARGUMENT_NOTVALID"),

    /* HttpStatus.UNAUTHORIZED 401 */
    UNAUTHORIZED(401000, HttpStatus.UNAUTHORIZED,
            "로그인이 되어 있지 않습니다."),

    UNAUTHORIZED_LOGIN_REQUIRED(401001, HttpStatus.UNAUTHORIZED,
            "로그인이 필요한 서비스입니다."),



    /* HttpStatus.FORBIDDEN 403 */
    FORBIDDEN(403000, HttpStatus.FORBIDDEN,
            "접근권한이 없는 서비스입니다."),


    /* HttpStatus.NOT_FOUND 404 */
    INVALID_NOT_FOUND(404000, HttpStatus.NOT_FOUND,
            "INVALID_NOT_FOUND"),



    /* HttpStatus.INTERNAL_SERVER_ERROR 505 */
    INTERNAL_SERVER_ERROR(400500, HttpStatus.INTERNAL_SERVER_ERROR,
            "INTERNAL_SERVER_ERROR"),

    INTERNAL_SERVER_ERROR_NOT_DEFINITION_EXCEPTION(400501, HttpStatus.INTERNAL_SERVER_ERROR,
            "INTERNAL_SERVER_ERROR_NOT_DEFINITION_EXCEPTION"),
    ;


    private int value;
    private HttpStatus httpStatus;
    private String message;

    ErrorCode(int value, HttpStatus httpStatus, String message) {
        this.value = value;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
