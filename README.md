> # JH - Spring Boot + Graph QL 기반의 API  프로젝트





## 들어가기
- 개인프로젝트를 위한 스프링 + 그래큐QL + MySql 기반 프레임워크
- Spring for GraphQL 1.0.0 프로토타입

## 사용 버전

| 이름                               | 버전          | 사용                         |
| ---------------------------------- | ------------- | ---------------------------- |
| spring boot                        | 2.7.1         |                              |
| jpa                                | 3.0.1         |                              |
| graph ql                           | 3.0.1         |                              |
| swagger                            | 4.0.0         | API 개발 및 API 문서            |
| query dsl                          | 0.3.3         | 인증.인가                      |
|                                    | 0.0.0         |                              |


## 프로젝트 초기 설정

- `TODO`, `FIXME` 확인 하고 수정한다
- Constants.js  확인하고 수정한다
- `.application.yml` 파일 확인하고 수정한다
  - `DB URL`  :
  - `AWS KEY` :  

  


## 기본 url 정의



| 패턴                                       | name                 | 기능 명         |
| ------------------------------------------ | -------------------- | --------------- |
| /                                          | home-main            | 메인 화면       
| /user/login                                | user-login           | 로그인화면      |
| /user/agree                                | user-agree           | 약관동의        |
| /user/register                             | user-register        | 회원가입        |
| /user/find/id                              | user-find-id         | 아이디 찾기     | 
| /user/find/pwd                             | user-find-pwd        | 비밀번호 찾기   |  
| /user/modify/pwd/:userId/:expireDate/:hash | user-modify-pwd      | 비밀번호 변경   | 




## 주의

### tdd 규칙





## 실행 

`VM Option`세팅

- `-Dspring.profiles.active=환경명`
- develop: 개발 / test: 테스트 / product: 운영



## 배포하기



