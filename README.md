# spring_web

> 스프링 3까지는 RequestMapping을 위한 AnnotationMethodHandlerAdapter가 있었지만, 최신 스프링의 경우 Deprecated 되고, RequestMappingHandlerAdapter를 대신 사용
> Spring Form 사용 시 4까지는 commandName을 사용 가능했지만, 5 이상부터는 commandName을 쓰니까 에러 발생. 대신 modelAttribute 사용