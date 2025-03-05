![Logo](https://github.com/user-attachments/assets/4251ac57-ff62-4ccd-9129-36c8e104906c)
# 거북이 놀이터 WebApp 백엔드

__거북이 놀이터의 유저가 즐길 수 있는__ 공간을 만들기 위한 커뮤니티 및 랭킹 서비스 __거북이 놀이터 WebApp 입니다.__


## 🌟 서비스 기능


- __간편 회원가입 및 로그인 기능__: OAuth2.0 프로토콜을 활용하여 카카오와 구글의 인증 서비스를 연동한 간편 회원가입 및 로그인 기능을 제공합니다.
- __게임 컨텐츠 소개 기능__: 거북이 놀이터 내 메인 컨텐츠인 스토리/전투/도전과제/생활 시스템에 대해서 유저들에게 가이드를 제공합니다. 
- __내 정보 조회 시스템__: 게임 내 데이터와 연동되어 유저의 보유자산, 칭호, 강화 정보 등의 인게임 정보와 홈페이지에서의 커뮤니티 활동에 대한 정보를 제공합니다.
- __출석 체크 기능__: 거북이 놀이터에 접속한 후 출석체크 버튼을 통해 매일 출석 체크를 진행하며 일일 출석 보상, 15일 이상 출석 보상, 월간 출석 보상을 실시간으로 제공합니다.
- __유저 커뮤니티__: 거북이 놀이터에서의 활동(건축, 희귀 아이템 획득 등)을 자랑하는 커뮤니티 공간을 제공합니다.
- __랭킹 시스템__: 직업별 레벨(전투직업/생활직업), 게시물, 도감, 보스 클리어, 부유자산에 대한 상위 5등까지의 랭킹을 제공합니다.

## 🛠️  기술 스택
- __Java 17__
- __Spring Boot 3.3.1__
- __Gradle__
- __MySQL__
- __Redis__
- __JPA__
- __OAuth 2.0__
- __AWS__

## ✅ 사전 요구사항
이 프로젝트를 실행하기 위해서는 아래의 환경이 필요합니다:

### 사전 설치
- Java 17
- Gradle
- MySQL
- Redis

### 외부 API
- OAuth2 제공자인 구글, 네이버, 카카오에서 OAuth2 애플리케이션을 생성
- AWS S3 사용을 위한 AccessKey, SecretKey 발급

### 마인크래프트 사설 서버 구축 및 자체 DB 구축

## 🖥️  어플리케이션 실행 (로컬 환경)
### 1. 프로젝트 Clone
```
git clone https://github.com/Team-Turtle-Minecraft/Turtule-Playground-Backend.git
```

### 2. 프로젝트 이동
```
cd Turtle-Playground-Backend
```

### 3. application.yaml 작성

다음 3개의 파일을 `src/main/resources` 아래에 붙여 넣습니다.

```
# application.yaml

spring:
  profiles:
    group:
      local: local-profile, common
      test: test-profile, common
      prod: prod-profile, common
server:
  env: blue
```

</br>
</br>

AWS의 S3 버킷이름, 리전, AccessKey, SecretKey를 입력해줍니다.
```
spring:
  config:
    activate:
      on-profile: common
  servlet:
    multipart:
      maxFileSize: 100MB
      maxRequestSize: 300MB

  jackson:
    default-property-inclusion: NON_NULL
    parser:
      allow-unquoted-control-chars: true

cloud:
  aws:
    s3:
      bucket: #####
    stack.auto: false
    region.static: #####
    credentials:
      accessKey: #####
      secretKey: #####

  port: 8080
  servlet:
    context-path: /

logging:
  level:
    com.github.wenqiqlantz.service.openaiclient: TRACE

management:
  endpoints:
    web:
      base-path: /actuator
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: 'ALWAYS'
  health:
    circuitbreakers:
      enabled: true
  security:
    enabled: false
```

</br>
웹앱용 MySQL 루트 비밀번호, 마인크래프트 사설서버 MySQL 주소, username, password 와 JWT Secret Key를 입력합니다. (로컬용)

```
application-local.yaml 

spring:
  config:
    activate:
      on-profile: local-profile   
  mvc:
    cors:
      allowed-origins: http://localhost:3000
      allowed-methods: GET,POST,PUT,DELETE,OPTIONS
      allowed-headers: "*"
      allow-credentials: true
      max-age: 3600

  data:
    redis:
      host: localhost
      port: 6377
  datasource:
    primary: # primary로 구분
      jdbc-url: jdbc:mysql://localhost:3308/turtle-playground-db   
      username: root
      password: #####
      driver-class-name: com.mysql.cj.jdbc.Driver
    secondary: # secondary로 구분
      jdbc-url: #####
      username: #####
      password: #####
      driver-class-name: com.mysql.cj.jdbc.Driver


   #JPA Configuration
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

jwt:
  secret: #####
  access:
    expiration: 6000000
    #    expiration:   10000 6000000
    header: Authorization

  refresh:
    expiration: 1209600000 # 2?
    header: Authorization-refresh

server:
  port: 8080
serverName: local-server

file:
  upload:
    image:
      api: http://localhost:8080/images/
      path: /src/main/resources/static/images

minecraft:
  attendance:
    address: http://#####
    secret: #####

logging:
  level:
    root: debug
```
</br>
</br>


### 4. 정적 파일 저장을 위한 폴더 생성
- `src/main/resources/static` 아래에 `images` 이름의 폴더를 생성합니다.

### 5. 프로젝트 빌드
```
./gradlew build -x test
```

### 6. 프로젝트 실행
```
java -Duser.timezone=Asia/Seoul -Dspring.profiles.active=local -jar ./build/libs/minecraft_service-0.0.1-SNAPSHOT.jar
```

## 🧑‍💻 개발자

- [@JSH0905](https://github.com/JSH0905)
