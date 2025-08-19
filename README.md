#### 설계 목적

일관된 응답을 위한 포맷 : 성공/실패에 상관없이 동일한 JSON 구조로 내려주기 위한.
예외의 표준화 : 서비스/ 도메인 로직에서 상황별로 thorw new Apiexception(./.) 만 던지면, 컨트롤러 단에서 잡지 않아도 전역에서 일정한 응답을 반환
각 클래스 구조 및 동작

ApiResponse<T>
- 역활 : 모든 API 응답 바디의 공통 포맷
- 필드 
  - success: 성공/실패 플래그. 
  - code: 숫자 코드(여기서는 HTTP status를 그대로 씀). 
  - message: 사용자/개발자 가독성 높은 메시지. 
  - data: 성공 시 페이로드(실패 시 null 가능). 
  - timestamp: 응답 시각(ISO-8601, OffsetDateTime). 
- @JsonInclue(JsonInclue.Inclue.NON_NULL) : data가 null이면 JSON에서 필드 자체를 제거 → 실패 응답이 깔끔해짐.
- 정적 팩토리 
  - ok(data), created(data), error(code,message)로 생성 일관화, 컨트롤러에서 return ApiResponse.ok(dto) 처럼 간결하게 사용. 
- 참고: OffsetDateTime은 타임존 오프셋을 포함합니다. 서버 표준을 UTC로 맞추려면 OffsetDateTime.now(ZoneOffset.UTC)를 고려하세요(운영 환경 권장)

ApiException
- 역할: 서비스/도메인 레이어에서 상황별로 던지는 커스텀 런타임 예외. 
- 구성: GlobalErrorCode를 보유하고, super(errorCode.getMessage())로 상위 예외 메시지도 세팅.
- 의도: 컨트롤러는 try/catch 불필요. 전역 핸들러가 잡아 표준 응답으로 변환. 
- 확장 팁: 원인 추적을 위해 cause, detail(예: 파라미터 값, 컨텍스트) 필드를 가진 추가 생성자도 자주 둡니다.

GlobalExceptionHandler
- 역할: 전체 애플리케이션의 예외를 가로채 표준 ApiResponse로 변환.
- 핸들러들
  - handleApi(ApiException): 우리가 의도적으로 던진 비즈니스 예외 → errorCode의 status/message로 응답. 
  - handleValidation(…): @Valid 실패(MethodArgumentNotValidException, ConstraintViolationException) → 400 리턴. 
  - handleEtc(Exception): 기타 모든 예상치 못한 예외 → 500 리턴.
- 장점: 컨트롤러/서비스에 예외 응답 코드가 흩어지지 않고, 한 곳에서 관리.

예외처리 흐름
- Service에서 예외 발생 -> throw new ApiException(GlobalErrorCode.OUT_OF_STOCK) -> GlobalExceptionHandler가 잡음 
-> Exception의 class의 따라 handler내부의 메서드로 진입 하여 맞춰서 응답 
-> 클라이언트는  success=fals, code=409, message =..., timestamp를 항상 같은 포맷으로 수신