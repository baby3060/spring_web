# spring_web

> 스프링 3까지는 RequestMapping을 위한 AnnotationMethodHandlerAdapter가 있었지만, 최신 스프링의 경우 Deprecated 되고, RequestMappingHandlerAdapter를 대신 사용
> Spring Form 사용 시 4까지는 commandName을 사용 가능했지만, 5 이상부터는 commandName을 쓰니까 에러 발생. 대신 modelAttribute 사용
>> Model에 modelAttribute(commandName) 사용하는 이름을 비어있는 객체로 제공해주기
> modelAttribute 사용 시 lombok 사용 시 적용 안 됨
> Command 클래스는 modelAttribute에서 사용하는 상태 클래스라고 생각하면 됨(로그인, 회원정보 등).