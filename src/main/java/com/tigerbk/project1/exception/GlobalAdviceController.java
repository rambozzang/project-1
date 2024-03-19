//package com.tigerbk.project1.exception;
//
//
//import com.google.protobuf.ServiceException;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// *
// * @Package     : com.withuslaw.common.exception
// * @name        : GlobalAdviceController.java
// * @date        : 2023/09/14 1:51 PM
// * @author      : tigerBK
// * @version     : 1.0.0
//**/
//@Slf4j
//@RequiredArgsConstructor
//@RestControllerAdvice
//public class GlobalAdviceController {
//
//
//    @ExceptionHandler(value = ServiceException.class)
//    public ResponseEntity<Object> handleServiceException(HttpServletRequest request, ServiceException ex) {
//        log.debug("###################### GlobalAdviceController() > ServiceException !!");
//        ex.printStackTrace();
//
//        return ResponseEntity.internalServerError().body(new ErrorResponse(String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value()),  ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR ));
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception ex) {
//        log.debug("###################### GlobalAdviceController() > handleException !!");
//        ex.printStackTrace();
//
//        return ResponseEntity.internalServerError().body(new ErrorResponse(String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value()),  ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR ));
//    }
//
//    /**
//     * try catch에서 Exception 인 경우
//     * @name        : GlobalAdviceController.defaultException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(value = DefaultException.class)
//    protected ResponseEntity<ErrorResponse> defaultException(HttpServletRequest request,DefaultException e) {
//        log.debug("###################### GlobalAdviceController() > defaultException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.internalServerError().body(new ErrorResponse(String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value()),  e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR ));
//    }
//
//    /**
//     * 비즈니스 오류는 모두 여기로
//     * @name        : GlobalAdviceController.badRequestException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(value = BadRequestException.class)
//    protected ResponseEntity<ErrorResponse> badRequestException(HttpServletRequest request,BadRequestException e) {
//        log.debug("GlobalAdviceController() > badRequestException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.badRequest().body(new ErrorResponse(String.valueOf( HttpStatus.BAD_REQUEST.value()), e.getMessage() , HttpStatus.BAD_REQUEST ));
//    }
//
//
//    /**
//     * not found
//     * @name        : GlobalAdviceController.notFoundException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(value = NotFoundException.class)
//    protected ResponseEntity<ErrorResponse> notFoundException(HttpServletRequest request,NotFoundException e) {
//        log.debug("GlobalAdviceController() > notFoundException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.badRequest().body(new ErrorResponse( String.valueOf( HttpStatus.NOT_FOUND.value()), e.getMessage() , HttpStatus.NOT_FOUND ));
//    }
//
//
//    /**
//     * 인증 오류인경우
//     * @name        : GlobalAdviceController.unAuthException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(value = UnAuthException.class)
//    protected ResponseEntity<ErrorResponse> unAuthException(HttpServletRequest request,UnAuthException e) {
//        log.debug("GlobalAdviceController() > unAuthException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(String.valueOf( HttpStatus.UNAUTHORIZED.value()), e.getMessage() , HttpStatus.UNAUTHORIZED));
//    }
//
//
//    /**
//     * 파일
//     * @name        : GlobalAdviceController.fileNotFoundException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(value = FileNotFoundException.class)
//    protected ResponseEntity<ErrorResponse> fileNotFoundException(HttpServletRequest request,FileNotFoundException e) {
//         log.debug("GlobalAdviceController() > FileNotFoundException !!");
//        e.printStackTrace();
//
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(String.valueOf( HttpStatus.NOT_FOUND.value()), e.getMessage() , HttpStatus.NOT_FOUND ));
//     }
//
//
//    /**
//     * 파일
//     * @name        : GlobalAdviceController.fileStorageException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(value = FileStorageException.class)
//    protected ResponseEntity<ErrorResponse> fileStorageException(HttpServletRequest request,FileStorageException e) {
//        log.debug("GlobalAdviceController() > FileStorageException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR ));
//    }
//
//
//    /**
//     * vo, dto validate error
//     * @name        : GlobalAdviceController.processValidationException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Object processValidationException(HttpServletRequest request,MethodArgumentNotValidException e) {
//        log.debug("GlobalAdviceController() > processValidationException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.badRequest().body(new ErrorResponse(String.valueOf( HttpStatus.BAD_REQUEST.value()), e.getBindingResult().getAllErrors().get(0).getDefaultMessage() , HttpStatus.BAD_REQUEST));
//    }
//
//    /**
//     * vo, dto validate error
//     * @name        : GlobalAdviceController.processUnexpectedTypeException
//     * @author      : tigerBK
//     * @param       :
//     * @return      :
//    **/
//    @ExceptionHandler(UnexpectedTypeException.class)
//    public Object processUnexpectedTypeException(HttpServletRequest request,UnexpectedTypeException e) {
//        log.debug("GlobalAdviceController() > processUnexpectedTypeException !!");
//        e.printStackTrace();
//
//        return ResponseEntity.badRequest().body(new ErrorResponse( String.valueOf( HttpStatus.BAD_REQUEST.value()), e.getMessage(), HttpStatus.BAD_REQUEST));
//    }
//
//}
