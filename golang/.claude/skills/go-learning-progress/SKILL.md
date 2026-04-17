---
name: go-learning-progress
description: A Tour of Go 공식 소스 기반 슬라이드 한글 번역 (프로젝트 파일 분석)
---

# Go Learning Progress (A Tour of Go 공식 소스 기반)

## 개요

A Tour of Go의 58개 슬라이드를 **GitHub 공식 소스에서 직접 가져와** 한글로 번역하며 진행합니다.

## 사용 시기

- 일일 학습 시작 시 현재 진도 확인
- 다음 슬라이드의 공식 코드와 한글 번역 필요
- 슬라이드 내용 재확인 필요

## 동작 방식

### Step 1: 프로젝트 파일 분석 (핵심!)
```
1. 프로젝트의 모든 .go 파일 스캔
2. 각 파일의 package 선언 확인
3. A Tour of Go 슬라이드 매핑과 비교
4. 완료한 슬라이드 결정
5. 다음 슬라이드 번호 결정
```

**예시:**
- `basics/functions.go` 파일 존재 + `package main` 
  → 슬라이드 5 (Functions) 완료
- `moretypes/slices.go` 파일 존재 
  → 슬라이드 19 (Slices) 완료

### Step 2: 공식 소스에서 코드 가져오기
GitHub에서 실시간 다운로드:
```
https://raw.githubusercontent.com/golang/website/master/_content/tour/basics/{슬라이드명}.go
```

### Step 3: 한글 번역 생성
- 슬라이드의 핵심 개념 설명
- 공식 코드 예제 제시
- 코드 라인별 해석
- 학습 포인트 정리
- 현재 프로젝트 코드와의 비교

### Step 4: 다음 단계 안내
- 다음 슬라이드 번호와 제목
- 권장 실습 내용
- 관련 링크

## A Tour of Go 슬라이드 ↔ 파일명 매핑

### Week 1, Day 1 (슬라이드 1~12)
| # | 슬라이드명 | 공식 파일명 | 한글명 | 감지 기준 |
|---|---|---|---|---|
| 1 | Welcome | welcome.article | 환영 | welcome/ 디렉토리 |
| 2 | Packages | basics/packages.go | 패키지 | Packages 개념 |
| 3 | Imports | basics/imports.go | 임포트 | 여러 import 사용 |
| 4 | Exported names | basics/exported-names.go | 내보낸 이름 | 대문자 함수 호출 |
| 5 | Functions | basics/functions.go | 함수 | 사용자 정의 함수 |
| 6 | Multiple results | basics/multiple-results.go | 다중 반환 | 다중 반환값 |
| 7 | Named return values | basics/named-results.go | 반환값 이름 | 반환값 이름 지정 |
| 8 | Variables | basics/variables.go | 변수 | var 또는 := 사용 |
| 9 | Constants | basics/constants.go | 상수 | const 선언 |
| 10 | For | flowcontrol/for.go | 반복문 | for 루프 |
| 11 | If | flowcontrol/if.go | 조건문 | if 문 |
| 12 | If and else | flowcontrol/if-and-else.go | If와 Else | if-else 문 |

### Week 1, Day 2 (슬라이드 13~28)
| # | 슬라이드명 | 공식 파일명 | 한글명 |
|---|---|---|---|
| 13 | Else if | flowcontrol/else-if.go | Else if |
| 14 | Defer | flowcontrol/defer.go | 지연 실행 |
| 15 | Pointers | moretypes/pointers.go | 포인터 |
| 16 | Struct fields | moretypes/struct-fields.go | 구조체 필드 |
| 17 | Struct literals | moretypes/struct-literals.go | 구조체 리터럴 |
| 18 | Arrays | moretypes/array.go | 배열 |
| 19 | Slices | moretypes/slices.go | 슬라이스 |
| 20 | Slice literals | moretypes/slice-literals.go | 슬라이스 리터럴 |
| 21 | Slice defaults | moretypes/slice-bounds.go | 슬라이스 기본값 |
| 22 | Slice len/cap | moretypes/slice-len-cap.go | 슬라이스 길이와 용량 |
| 23 | Nil slices | moretypes/nil-slices.go | Nil 슬라이스 |
| 24 | Making slices | moretypes/making-slices.go | 슬라이스 생성 |
| 25 | Slices of slices | moretypes/slices-of-slices.go | 2차원 슬라이스 |
| 26 | Appending | moretypes/append.go | 슬라이스 추가 |
| 27 | Range | flowcontrol/range.go | 범위 반복 |
| 28 | Range continued | flowcontrol/range-continued.go | 범위 반복 계속 |

## 호출 방법

```
/go-learning-progress
```

## 출력 형식

```
✅ 현재 진도 분석
프로젝트 파일 스캔 완료
- {파일명}: 슬라이드 N 개념 감지
- {파일명}: 슬라이드 N 개념 감지

현재 진도: 슬라이드 1-4 (Welcome ~ Exported names)
다음 학습: 슬라이드 5 (Functions)

📝 다음 학습: 슬라이드 5 - Functions (함수)

📄 슬라이드 핵심 개념
...

💻 공식 코드 예제
...

🔍 코드 해석
...

💡 학습 포인트
...

🎯 실습
프로젝트 코드에 추가할 내용:
...

📚 자료
- 공식: https://go.dev/tour
```

## 진도 감지 로직

**파일 분석 순서:**
1. `.go` 파일 모두 검색
2. 각 파일 첫 10줄 읽기 (package 선언 확인)
3. 파일 내용 분석 (함수, 변수, 상수 등 키워드)
4. A Tour of Go 슬라이드 매핑과 대조
5. 완료 슬라이드 → 다음 슬라이드 결정

**완료 판단 기준:**
- 파일 존재 + 해당 슬라이드 개념 구현
- 예: `functions.go` 파일 + 사용자 함수 정의 → 슬라이드 5 완료

**다음 슬라이드 선택:**
- 완료한 슬라이드 이후 첫 번째 미완료 슬라이드
- 예: 슬라이드 1-4 완료 → 슬라이드 5 제시
