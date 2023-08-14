# basic-be

>세션부터 토큰방식 및 여러가지 로그인 방식을 연습하고  
기본적인 게시판을 만들어 보는 기초연습 프로젝트

## ⚙ 프로젝트 개발 환경
- 통합개발환경 : Intellij
- JDK 버전 : JDK 17
- 스프링부트 버전 : 3.1.2
- 사용 DB : MySQL, Redis
- 빌드툴 : Gradle
- 관리툴 : Git, Github, Docker, Google Cloud Platform

## ⚒ 백엔드 기술 스택
- Backend (V1)
    - Spring Boot
    - Spring Validation
    - Spring Data Jpa
    - Spring Devtools
    - Lombok
    - Spring Session

- Database
    - MySQL 8.0.30
    - Redis 6.0


## 📜 프로젝트 구현 기능
- V1(세션 이용 버전)
  - 회원 (UserAccount)
      - 회원가입, 로그인 및 로그아웃


## 📂 프로젝트 DB 모델링
![ ](./document/basic-be-board-erd.svg)

## 📜 프로젝트 API 명세서 (V1)

| Function |Method| End Point       | Session  |
|------|---|-----------------|:--------:|
|회원가입|POST| /api/v1/signup  |    X     |
|로그인|POST| /api/v1/login   |    X     |
|로그아웃|POST| /api/v1/logout  |    O     |
|내정보 조회|GET| /api/v1/my-info |    O     |
|내정보 변경|PUT|/api/v1/my-info|O|
