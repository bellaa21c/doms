package com.skbroadband.doms.global.exception;

import com.skbroadband.doms.global.dto.ErrorResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.exception
 * @File : GlobalExceptionHandler
 * @Program :
 * @Date : 2022-12-21
 * @Comment :
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("server.error.include-exception")
    private String includeException;
    @Value("server.error.include-stacktrace")
    private String includeStacktrace;

    /**
     * MissingServletRequestParameterException 예외.
     *
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * ServletRequestBindingException 예외.
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Validated 어노테이션을 사용하여 매개변수 검증에 실패할 때 이곳으로 온다.
     *
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * MissingServletRequestPartException 예외.
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Validated 어노테이션을 사용하여 매개변수 검증에 실패할 때 이곳으로 온다.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(ConstraintViolationException ex,
                                                                         WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * 정의한 예외를 제외한 모든 예외는 이곳으로 온다.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception ex,
                                            WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = GlobalException.class)
    protected ResponseEntity<Object> handleGlobalException(GlobalException ex,
                                                                         WebRequest request) {

        return handleExceptionInternal(ex, ex.getStatus(), request);
    }

    /**
     * 존재하지 않는 API를 호출할 경우.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Void> notFoundException() {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(httpStatus);
    }

    /**
     * ForbiddenException 예외.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> forbiddenException(ForbiddenException ex,
                                                     WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.FORBIDDEN, request);
    }

    /**
     * UnauthorizedException 예외.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> unauthorizedException(UnauthorizedException ex,
                                                        WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.UNAUTHORIZED, request);
    }

    /**
     * 잘못된 요청을 한 경우.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException ex,
                                                      WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * IllegalArgumentException 예외.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                 WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * MethodArgumentTypeMismatchException 예외.
     * @return ResponseEntity
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * @return
     */
    private ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                           HttpStatus status,
                                                           WebRequest request) {
        logger.error("{}", ex);

//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        ex.printStackTrace(printWriter);
//        String stackTrace = stringWriter.toString();

        return ResponseEntity.status(status.value())
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.getReasonPhrase())
                        .message(ex.getMessage())
//                        .stacktrace(stackTrace)
                        .path(((ServletWebRequest)request).getRequest().getRequestURI())
                        .build());
    }

    /**
     *
     * @return
     */
    private ResponseEntity<Object> handleExceptionInternal(BindException ex,
                                                           HttpStatus status,
                                                           WebRequest request) {
        logger.info("{}", ex);

//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        ex.printStackTrace(printWriter);
//        String stackTrace = stringWriter.toString();

        List<ErrorResponse.FieldError> customFieldErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> ErrorResponse.FieldError.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.status(status.value())
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.name())
                        .message(customFieldErrors.get(0).getMessage())
//                        .stacktrace(stackTrace)
                        .fieldErrors(customFieldErrors)
                        .path(((ServletWebRequest)request).getRequest().getRequestURI())
                        .build());
    }

    private ResponseEntity<Object> handleExceptionInternal(ConstraintViolationException ex,
                                                           HttpStatus status,
                                                           WebRequest request) {
        logger.info("{}", ex);

//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        ex.printStackTrace(printWriter);
//        String stackTrace = stringWriter.toString();

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<ErrorResponse.FieldError> customFieldErrors = constraintViolations.stream()
                .map(constraintViolation -> {
                    String message = constraintViolation.getMessage();
                    PathImpl propertyPath = (PathImpl)constraintViolation.getPropertyPath();
                    String name = propertyPath.getLeafNode().getName();
                    return ErrorResponse.FieldError.of(name, message);
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(status.value())
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.name())
                        .message(ex.getMessage())
//                        .stacktrace(stackTrace)
                        .fieldErrors(customFieldErrors)
                        .path(null)
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        logger.error("{}", ex);

//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        ex.printStackTrace(printWriter);
//        String stackTrace = stringWriter.toString();

        ErrorResponse responseBody = ErrorResponse.builder()
                .code(status.value())
                .status(status.getReasonPhrase())
                .message(ex.getMessage())
//                .stacktrace(stackTrace)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();

        return super.handleExceptionInternal(ex, responseBody, headers, status, request);
    }
}
