# testcode_musinsa
coding test for musinsa


## 1. 프로젝트 빌드 & jar 생성
### 1.1. 로컬의 프로젝트 경로에서 아래 명령어를 사용하여 compile 및 jar 파일 패키징을 진행.
  
    mvnw clean install  

### 1.2. 프로젝트 폴더 하위의 target 디렉토리에서 testcode-0.0.1.jar 파일 생성 확인


## 2. 리눅스 서버 경로로 배포
### 2.1. scp 명령어 혹은 ftp 프로그램으로 jar파일 업로드(/usr/local 경로 혹은 ~(home) 디렉토리)


## 3. jar 파일 실행
### 3.1. 아래 명령어로 실행하거나

    java -jar testcode-0.0.1.jar


### 3.2. service 등록 후 start

#### 3.2.1. /etc/system/system.d 디렉토리 하위에 testcode.service 생성

      vi /etc/system/system.d/testcode.service

#### 3.2.2. testcode.service 파일 내에 아래와 같이 작성

      [Unit]
      Description=Service Description
      After=syslog.target network.target postgresql.service
      
      [Service]
      ExecStart=/bin/bash -c "exec java -jar {jar 파일이 위치한 경로}/testcode-0.0.1.jar"
      Restart=on-failure
      RestartSec=10
      
      User=root
      Group=root
      
      [Install]
      WantedBy=multi-user.target
      
#### 3.2.3. 아래 명령어를 실행하여 demon reload

      daemon-reload

#### 3.2.4. 아래 명령어로 servcie 실행

      service testcode start (혹은 servicectl start testcode)


## 4. 테스트 데이터 입력
### 4.1. h2 console 접속

    http://localhost:8080/h2-console

### 4.2. h2 DB에 아래 데이터 insert

    INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-a','opt-aa',0);
    INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-a','opt-ab',0);
    INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-b','opt-ba',0);
    INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-b','opt-bb',0);
    INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-b','opt-bc',0);
    INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-c','opt-ca',0);


## 5. API 호출
### 5.1. 재고현황 조회
#### 5.1.1. 전달받은 상품명에 해당하는 옵션별 재고수량
##### 5.1.1.1. 호출 URL 

    http://{ip}:8080/get/{상품명}/cnt

##### 5.1.1.2. 예. 'prd-a'상품명으로 조회하는 경우

    http://{ip}:8080/get/prd-a/cnt
 
#### 5.1.2. 전달받은 상품명 + 옵션명 조합에 해당하는 재고수량
##### 5.1.2.1. 호출URL

    http://{ip}:8080/get/{상품명}/{옵션명}/cnt

##### 5.1.2.2. 예. 'prd-a'상품명, 'opt-aa' 옵션명으로 조회하는 경우

    http://{ip}:8080/get/prd-a/opt-aa/cnt

#### 5.1.3. 재고 증가 처리 API
##### 5.1.3.1. 호출URL

    http://{ip}:8080/increase/{상품명}/{옵션명}/{증가할 수량}

##### 5.1.3.2. 예. 'prd-a'상품명, 'opt-aa' 옵션명으로 재고 증가

    http://{ip}:8080/increase/prd-a/opt-ab/1

#### 5.1.4. 재고 감소 처리 API
##### 5.1.4.1. 호출URL

    http://{ip}:8080/decrease/{상품명}/{옵션명}/{감소할 수량}

##### 5.1.4.2. 예. 'prd-a'상품명, 'opt-aa' 옵션명으로 재고 감소

    http://{ip}:8080/decrease/prd-a/opt-ab/1

