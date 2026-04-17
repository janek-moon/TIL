# Go 학습 6주 체크리스트

**목표:** 웹/API 서버 개발 실무 수준 도달
**투자 시간:** 주중 45분 · 주말 1시간 (주당 약 4~5시간)
**전제:** 다른 언어 능숙자 기준 (문법 학습 최소화, Go 관용구 중심)

---

## 1주차: 문법 속성 학습

다른 언어 경험을 활용해 문법은 빠르게 훑고, Go만의 특이점에 집중합니다.

### 학습
- [ ] **Day 1~2:** A Tour of Go 완주 — https://go.dev/tour
- [ ] **Day 3:** How to Write Go Code — https://go.dev/doc/code
- [ ] **Day 4:** 공식 튜토리얼 *Getting Started* — https://go.dev/doc/tutorial/getting-started
- [ ] **Day 5:** 공식 튜토리얼 *Create a Go module* — https://go.dev/doc/tutorial/create-module
- [ ] **주말:** Go Playground에서 슬라이스·맵·구조체 자유 실험 — https://go.dev/play

### 체득해야 할 개념
- [ ] `:=` 단축 선언과 `var` 차이
- [ ] 다중 반환값 (`result, err := fn()`)
- [ ] `defer` 동작 순서
- [ ] zero value 개념 (초기화 없이 기본값)
- [ ] 슬라이스 vs 배열, `append`의 동작
- [ ] `go mod init`, `go mod tidy` 기본 명령

### 체크 질문
- [ ] `make([]int, 5)`와 `make([]int, 0, 5)`의 차이를 설명할 수 있는가
- [ ] `for range`로 인덱스와 값을 동시에 받는 방법을 아는가

---

## 2주차: Go다운 사고방식

Go가 다른 언어와 가장 다른 부분입니다. 이 주차에서 관용구를 체득해야 나머지 5주가 수월해집니다.

### 학습
- [ ] *Effective Go* 핵심 섹션 정독 — https://go.dev/doc/effective_go
  - [ ] Names
  - [ ] Control structures
  - [ ] Errors
  - [ ] Interfaces and other types
- [ ] 공식 튜토리얼 *Generics* (선택) — https://go.dev/doc/tutorial/generics

### 체득해야 할 개념
- [ ] 인터페이스의 암묵적 구현 (덕 타이핑)
- [ ] `error` 타입과 `errors.Is`, `errors.As`, `fmt.Errorf("%w", err)`
- [ ] `if err != nil` 패턴 (저항감 버리기)
- [ ] 포인터 vs 값 전달 기준 (큰 구조체·수정 필요 시 포인터)
- [ ] 패키지 가시성 (대문자=export, 소문자=unexported)
- [ ] 구조체 메서드 리시버 선택 (`(r Receiver)` vs `(r *Receiver)`)

### 실습
- [ ] 간단한 인터페이스 설계 후 2개 이상의 구조체로 구현
- [ ] 커스텀 에러 타입 정의 및 `errors.As`로 타입 추출해보기

### 체크 질문
- [ ] `io.Reader` 인터페이스가 왜 강력한지 설명할 수 있는가
- [ ] 언제 포인터 리시버를 쓰고 언제 값 리시버를 쓰는지 기준이 있는가

---

## 3주차: 동시성

API 서버의 성능과 안정성에 직결되는 영역입니다. `context`는 특히 4~5주차에서 계속 쓰이니 반드시 숙달해야 합니다.

### 학습
- [ ] 공식 튜토리얼 *Concurrency* (Tour of Go 후반부 재복습)
- [ ] *Go Concurrency Patterns* 블로그 — https://go.dev/blog/pipelines
- [ ] `context` 패키지 문서 — https://pkg.go.dev/context

### 체득해야 할 개념
- [ ] `go` 키워드로 goroutine 실행
- [ ] Channel 송수신, 버퍼드 채널
- [ ] `select` 문과 `time.After`로 타임아웃 구현
- [ ] `sync.WaitGroup`으로 goroutine 대기
- [ ] `sync.Mutex`, `sync.RWMutex` 사용 시점
- [ ] `context.Context`로 취소 신호 전파 — **API 서버 필수**
- [ ] `context.WithTimeout`, `context.WithCancel`

### 실습
- [ ] 여러 URL을 동시 호출해 응답 시간 측정하는 CLI 작성
- [ ] `context` 취소로 중단 가능하게 구현
- [ ] `-race` 플래그로 데이터 레이스 검출 경험 (`go run -race`)

### 체크 질문
- [ ] 채널을 닫아야 할 주체는 송신자인가 수신자인가
- [ ] goroutine 누수가 왜 발생하고 어떻게 방지하는가

---

## 4주차: 표준 라이브러리로 HTTP 서버

프레임워크 없이 `net/http`만으로 API를 만듭니다. Go 1.22+ 라우팅 개선으로 표준 라이브러리만으로도 충분합니다.

### 학습
- [ ] `net/http` 문서 — https://pkg.go.dev/net/http
- [ ] 공식 튜토리얼 *Developing a RESTful API with Gin* — https://go.dev/doc/tutorial/web-service-gin
  (Gin 사용하지만 REST 설계 흐름 참고용)
- [ ] Go 1.22 라우팅 개선 블로그 — https://go.dev/blog/routing-enhancements

### 체득해야 할 개념
- [ ] `http.HandleFunc`, `http.ServeMux` 기본
- [ ] Go 1.22+ 라우팅 패턴 (`GET /users/{id}`)
- [ ] `http.Handler` 인터페이스와 미들웨어 패턴 (함수 래핑)
- [ ] `encoding/json` 마샬링·언마샬링
- [ ] 구조체 태그 (` `json:"name"` `)
- [ ] `r.Context()`로 요청별 컨텍스트 접근
- [ ] `http.ResponseWriter` 사용 시 주의점 (Header → WriteHeader → Write 순서)

### 실습
- [ ] **Todo REST API (인메모리)**
  - [ ] `GET /todos` 목록 조회
  - [ ] `POST /todos` 생성
  - [ ] `GET /todos/{id}` 단건 조회
  - [ ] `PUT /todos/{id}` 수정
  - [ ] `DELETE /todos/{id}` 삭제
  - [ ] 로깅 미들웨어 추가
  - [ ] JSON 에러 응답 포맷 통일

### 체크 질문
- [ ] 미들웨어 체인이 어떻게 동작하는지 그림으로 그릴 수 있는가
- [ ] `json.Decoder`와 `json.Unmarshal` 중 언제 무엇을 쓰는가

---

## 5주차: 실전 요소 결합

프로덕션에 가까운 구성으로 확장합니다. 이 시점에서 프레임워크 도입 여부를 결정합니다.

### 학습
- [ ] `database/sql` 문서 — https://pkg.go.dev/database/sql
- [ ] `pgx` 또는 `sqlx` 중 택1 문서 훑기
  - pgx: https://github.com/jackc/pgx
  - sqlx: https://github.com/jmoiron/sqlx
- [ ] `log/slog` (Go 1.21+ 표준 구조화 로깅) — https://pkg.go.dev/log/slog
- [ ] `testing` 패키지 — https://pkg.go.dev/testing

### 체득해야 할 개념
- [ ] DB 연결 풀 관리 (`sql.DB`는 공유 객체)
- [ ] `context`를 DB 쿼리에 전달 (`QueryContext`, `ExecContext`)
- [ ] 환경변수 설정 (`os.Getenv` 또는 `envconfig`)
- [ ] 구조화 로깅 (`slog.Info("msg", "key", value)`)
- [ ] 테이블 드리븐 테스트 패턴
- [ ] `httptest` 패키지로 핸들러 테스트

### 실습
- [ ] 4주차 Todo API를 PostgreSQL 연동으로 전환
- [ ] 환경변수로 DB 접속 정보 주입
- [ ] `slog`로 요청 로깅 구조화
- [ ] 핸들러 3개 이상에 대한 단위 테스트 작성

### 프레임워크 도입 결정
- [ ] 표준 라이브러리로 불편했던 지점 정리
- [ ] 불편했다면 **Chi** 도입 검토 (표준 라이브러리 친화적)
  - https://github.com/go-chi/chi
- [ ] 불편함이 크지 않으면 그대로 진행

### 체크 질문
- [ ] `sql.DB`를 요청마다 새로 만들면 안 되는 이유는
- [ ] 테스트 시 DB 의존성을 어떻게 처리할 것인가 (mock vs testcontainers)

---

## 6주차: 배포 가능한 프로젝트

실무 배포 수준으로 마무리합니다. 최종 결과물은 포트폴리오로 활용 가능합니다.

### 학습
- [ ] Docker 멀티스테이지 빌드 패턴
- [ ] Graceful shutdown 패턴 — https://pkg.go.dev/net/http#Server.Shutdown
- [ ] GitHub Actions Go workflow 예제

### 체득해야 할 개념
- [ ] 멀티스테이지 Dockerfile (빌드 단계와 런타임 단계 분리)
- [ ] `os/signal`로 SIGTERM 처리 후 graceful shutdown
- [ ] JWT 인증 흐름 (`github.com/golang-jwt/jwt/v5`)
- [ ] `go vet`, `staticcheck`, `golangci-lint` 활용

### 실습 — 최종 프로젝트
- [ ] **인증 포함 REST API**
  - [ ] 회원가입·로그인 엔드포인트 (비밀번호 해시: `bcrypt`)
  - [ ] JWT 발급 및 인증 미들웨어
  - [ ] 인증된 사용자만 접근 가능한 리소스 엔드포인트
  - [ ] PostgreSQL 연동
- [ ] **배포 준비**
  - [ ] Dockerfile (멀티스테이지, 이미지 크기 20MB 이하 목표)
  - [ ] `docker-compose.yml`로 DB와 함께 실행
  - [ ] Graceful shutdown 구현
- [ ] **CI 구성**
  - [ ] GitHub Actions: `go test`, `go vet`, 빌드 검증
  - [ ] README 작성 (실행 방법, API 명세)

### 체크 질문
- [ ] 컨테이너 종료 시 진행 중인 요청을 어떻게 보호하는가
- [ ] JWT의 단점과 대안은 무엇인가

---

## 보조 자료

**1차 공식 자료**
- Go 공식 사이트: https://go.dev
- 표준 라이브러리 레퍼런스: https://pkg.go.dev/std
- Go 블로그: https://go.dev/blog

**검증된 서적 (선택, 웹 서버 특화)**
- *Let's Go* (Alex Edwards) — 표준 라이브러리로 웹 앱 구축
- *Let's Go Further* (Alex Edwards) — REST API 심화편

**빠른 참조**
- Go by Example: https://gobyexample.com

---

## 진행 팁

- 매주 마지막에 **체크 질문**에 자신의 말로 답해보기 — 이해도 점검에 가장 효과적
- `go doc` 명령을 습관화 (예: `go doc net/http.Handler`) — 웹 검색보다 빠름
- 코드는 항상 `gofmt` 또는 `go fmt` 적용 (에디터 저장 시 자동화 권장)
- 막히면 공식 Discord Gophers 또는 r/golang 활용
