package talk.innertalk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    //커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public void handleCustomException(CustomException e){
        log.error(e.getMessage());

    }
}
