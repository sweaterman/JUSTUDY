### JUSTUDY
SSAFY 2학기 공통 프로젝트


### 프로젝트 진행 기간
2023.01.09 ~ 2023.02.17

### 팀원 
김도균: FE
<br>
류한민: BE, 팀장
<br>
서주광: FE
<br>
오의석: BE
<br>
이신광: BE
<br>
이연희: FE

### 프로젝트 설명
서비스 설명 
싸피인 스터디를 위한 토탈 웹 서비스
기능
- 스터디 모집, 관리 환경
- 화상 스터디 및 스터디에 필요한 부가 기능을 추가한 webRTC 서비스
- 자기주도 학습을 위한 컴퓨터비전 기반 자기 검열 기능
- 싸피생들 사이에 공부 시너지를 위한 커뮤니티 제공
- 페이지 관리를 위한 관리자 페이지
- 랭킹, 스터디 뱃지를 활용한 공부 의지 고취 시스템

[회의록 정리](https://www.notion.so/cf1646ad64944a8180672dd4ab57d2a1)

- **프로젝트 명세서**
    
    # 기획.
    
    [요구사항 명세서](https://www.notion.so/f64e6c525ecb4651bfd6cac0f5b15016)
    
    [API 명세서](https://www.notion.so/023ef6124242475fbba3f8171864ce87)
    
    [[DB] ERD 및 테이블 명세서](https://www.notion.so/DB-ERD-0cd67de4cb3d407487207b1733997f4f)
    
    [서비스 아키텍처 구조](https://www.notion.so/313d5a098e8049dd8947b351bc11cc28)
    
    [Frontend 코딩 컨벤션](https://www.notion.so/Frontend-58902da3538e46ed819af8ed1999de41)
    
    [Back-End Convention](https://www.notion.so/Back-End-Convention-c2a0b27112ff45de9dc834dceac5a0d4)
    
    [Git Convention & Jira Convention](https://www.notion.so/Git-Convention-Jira-Convention-51df0771f294443497ab435665bbc3c5)
    
    [Git 학습](https://www.notion.so/Git-c97038ca088e4799a4a40ecd3a313b18)
    
    [스터디 명세서](https://www.notion.so/24b6700094024f8aa2624eaf5a3efdb1)
    
    [Error 명세서](https://www.notion.so/6a6f08b9953548de8b92fb164da616d9)
    
    /api/enum → enum 리스트 가져오기
    
    [로그인 및 회원 명세서](https://www.notion.so/1ed6c018edec4614bee26578346fdf05)
    
    추가
    
    [새로운 요구사항 명세서](https://www.notion.so/222eead98a7a41b5a2c38d0a227ef9a9)
    
    [새로운 API 명세서](https://www.notion.so/API-3bb2f812a802493ba6eb3e0a9668f32d)
    
    [트러블 슈팅 문서](https://www.notion.so/45928b42382d432aa8804f41f0c5d7b0)
    
    # 설계
    
    # 개발
    
    # 테스트
    
    # 배포
    
    # 유지보수
    
- **설치 버전**
    
    # Back-End
    
    - **`Java`** : azul-11 (java 11.0.17)
    - **`mysql`** : 8.0.30
    - **`SpringBoot`** : 2.7.7
    - **`IDE`** : IntelliJ
    
    # Front-End
    
    - `**node JS`** : ****18.12.1 LTS
    - **`IDE`** : VSCode
        - `Extension` : ESLint, EditorConfig for VS Code, vscode-icons, Vue VSCode Snippets, Prettier-Code formatter, Vue Language Features (Volar)
    - `**Vue**`
        - vue 2.6.14, vue-router 3.6.5, vuex 3.6.2, axios 1.2.2
            
            ```
            npm install -g  @vue/cli → 컴퓨터 전역에 vue client 깔기 이거 해야 vue create ~~ 를 할 수 있음
            vue create my-project  →my-project 디렉토리가 만들어짐
            cd my-project  → my-project 디렉토리로 들어감
            npm install vuex@3         (상태관리)
            npm install vue-router@3      (url 이동과 관련)
            npm install axios          (백엔드와 통신하는데 필요)
            vue add vuetify           (vuetify 설치 - vuetify2)
            ```
            
        
- **도움 링크**
    - TUPLI - [https://github.com/hotsix-turtles/TUPLI](https://github.com/hotsix-turtles/TUPLI)
    - 페이징 성능 개선하기
        - NoOffset : [https://jojoldu.tistory.com/528](https://jojoldu.tistory.com/528)
        - 커버링 인덱스: [https://jojoldu.tistory.com/529](https://jojoldu.tistory.com/529)
    - WebRTC open source - [https://medevel.com/13-os-webrtc-server/](https://medevel.com/13-os-webrtc-server/)
    - Realtime communication with WebRTC - [https://github.com/googlecodelabs/webrtc-web](https://github.com/googlecodelabs/webrtc-web)
    - WebRTC 예제 정리 - [https://forest71.tistory.com/211](https://forest71.tistory.com/211)
    - [gradle] 그레이들 Annotation processor 와 Querydsl - [http://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html](http://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html)
    - OpenVidu - [https://github.com/OpenVidu/openvidu.io-docs/tree/master/docs/advanced-features](https://github.com/OpenVidu/openvidu.io-docs/tree/master/docs/advanced-features)
    - hola! - [https://holaworld.io/](https://holaworld.io/)
    - Wepeech 친구와 함께하는 1:1 화상 토론 서비스 **-** [https://github.com/hanghae99-6/FE](https://github.com/hanghae99-6/FE)
    - 코스모스 - [https://github.com/ehhclaire/COSMOS](https://github.com/ehhclaire/COSMOS)
- **Jira 대충정리한 내용..**
    
    
    - 이슈 트래킹 툴 중 하나
    - 프로젝트 매니지먼트 가능
    - GitLab, Github에 연동이 가능하다. (스마트 커밋)
    
    ## 사용법(Jira Cloud)
    
    - 이슈 만들기
        - Issue Type: Epic(큰 틀), Task(작은 틀), Story(사용자의 스토리), Bug
        - Status: 진행 중, 완료 상태 표시
        - Summary: 개요. 제목이라고 생각하면 됨
        - Components: 하나의 방? 역할? 큰 틀은 없고 팀에서 정하면 된다. (UX, 인프라.. 등)
        - Label: 대소문자 구별 잘하기
        - Sprint: 나중에 할당도 가능
    
    ## JQL (Jira Query Language)
    
    - Jira Issue를 구조적으로 검색하기 위해 제공하는 언어 (SQL과 비슷)
    - 쌓인 Issue들을 재가공해 유의미한 데이터를 도출해내는데 활용
    - =, ≠, >, ≥, in, not in, ~(contains), !~(not contains), is empty, in not empty, is null, is not null
    - AND, OR, NOT, EMPTY, NULL, ORDER BY
    - 날짜를 상대적으로 표현함
    - endOfDay(), startOfDay()
    - endOfWeek() (Saturday), startOfWeek() (Sunday)
    - endOfMonth(), startOfMonth(), endOfYear(), startOfYear()
    - currentUser()
    - JQL문을 사용하여 필터를 만들 수 있음(공유도 가능)
    - Dashboard: 역시 공유 가능. 레이아웃도 지정 가능.
    - Gadget을 Dashboard에 추가할 수 있다
    - Agile Board를 사용할 수 있다.
    - 스크럼보드, 칸반 보드 등 생성 가능
    
- **Git Lab 로컬에 클론하기 (초기설정)**
    
    git Lab 접속 → edit profile → access token → 모두 체크하고 토큰 발행
    
    git clone 주소 → 무슨 창 뜨면 username에 싸피 깃 메일 입력하고 비밀번호에 토큰 입력
    
    토큰은 한번 생성할때만 확인할 수 있기 때문에 각자 저장해놓으세용
    
- 컨벤션 정리
    
    ## 깃 컨벤션
    
    ### 브랜치
    
    - main = latest release
    
    - dev
    
    - feat
    - feat-front/[feature name]
    - feat-back/[feature name]
    - ex. feat-back/chat
    
    - hotfix
    - hotfix/[issue num]
    
    ### 커밋
    
    ## 코딩 컨벤션
    
    [styleguide/intellij-java-google-style.xml at gh-pages · google/styleguide](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
    
    ## Rest API 가이드
    
- 해결되지 않은 토의
    - 기획,구현 VS 네트워크,서버 우선순위 선택
    - 완성도 창의성
    - 로그인 방식: 세션 vs JWT????
    - RTC 기능의 당위성 - 추가기능 (컴파일러,코드윗미)
    - 남은 일정 정하기
    - mm연동방식
- WebRTC 개념
    
    # WebRTC = Web Real-Time communication
    
    # 1.Why?
    
    웹으로 통신을 이야기하면 보통 HTTP통신을 이야기한다.
    
    Http통신은 서버와 클라이언트 모델을 따른다. 또한 게속 연결된 형태가 아니라서 서버 간의 최대 연결 수 보다 더 많이 요청,응답이 가능하다.그러나! 요청이 있어야만 응답이 있어서, 이전 상황을 알 수가 없다.(stateless 특징) + 텍스트 기반(reqeust -> response)
    
    → 폴링 기술을 사용해서 해결하려고 노력을 했었다. 지속적으로 업데이트틑 확인하는 폴링 방식과 업데이트가 생길 때까지 이벤트를 기다리는 롱 폴링 방식을 적용하였으나 한계가 존재했다.
    
    → 그래서 웹소켓 통신이 생겼다. 실시간 양방향 통신으로 서버와 클라이언트가 계속 연결을 유지하는 통신이다. (open ->실시간 통신-> close)
    
    → 그러나 웹소켓은 서버의 성능이 중요하다. 서비스를 사용하는 유저가 많아질수록 많은 메모리가 요구되고 딜레이 또한 발생할 수 있다.
    
    → 그래서 WebRTC가 생긴 것이다. 브라우저를 서버에 연결하는 것이 아니라 브라우저끼리 연결하는 방식이다. 즉 P2P 통신방식이다.(서버를 통해서 전달되는 방식이 아닌 것이다.)텍스트뿐만 아니라 영상 오디오 등 거의 모든 데이터를 리얼타임으로 전송할 수 있다.
    
    →  다만 이 방식도 문제가 생긴다. 1000명이 WebRTC로 연결된 상태라면, 하나의 브라우저가 999개의 데이터를 다운받고 업로드한다는 단점이다. 즉, 확장성의 제약이 생긴다.
    
    → 보통 그래서 미디어 서버를 같이 쓴다. 대표적으로 Kurento가 있다.
    
    # 2. How
    
    ## 이론
    
    WebRTC의 통신방식을 설명하겠다. 브라우저끼리 직접 통신하는 방식이다.간단히 이야기하면 다음과 같다.
    
    ### a. 각 브라우저가 P2P 커뮤니케이션에 동의를 해야한다.
    
    ### b. 그 후 서로의 주소를 공유한다.
    
    ```
    다만 브라우저는 웹 서버가 아니라서 외부에서 접근할 수 있는 주소가 없다.
    그래서 **중재자**가 초기에 통신설정을 도와줘야한다.
    공인 IP와 포트번호를 확인하여 알려주어야 한다.(라우터의 역할)
    ```
    
    ### c. 방화벽을 우회한다.
    
    ```
    라우터들은 방화벽이 설정되었다.
    이러한 라우터를 통과해서 연결할 방법을 찾는 과정을 NAT트래버셜이라고 한다.
    (NAT : 여러 대의 컴퓨터가 하나의 공인 IP를 공유, 트래버셜 = 순회)
    ```
    
    ### d.멀티미디어 데이터를 실시간으로 교환한다.
    
    ```
    SDP(session Description Protocol)이라는 통신규약을 이용하여, 미디어 정보를 교환한다.
    SDP의 데이터는 고유 세션 아이디,버전,세션 이름과 목적,멀티캐스트 ip,ttl,세션 타이밍,미디어 관련 정보 등을 담고 있다.
    ```
    
    ### NAT트래버셜 작업
    
    ㄱ. STUN 서버 (Session Traversal Utilities for NAT)자신의 공인 IP와 포트를 확인하는 방식두 peer가 자기 자신의 주소를 알아내면, p2p 연결을 시도할 두개의 고유한 주소가 생긴 것이다.다만, 정보를 알아도 연결 자체를 못하게 하는 경우도 있다.(ex. Symmetric Nat : 이전에 연결된 적 있는 네트워크끼리만 연결 가능하도록 제한)ㄴ. TURN 서버 (Traversal Using Relay NAT)네트워크 미디어를 중개하는 서버 이용하는 방식.중간에 서버를 한 번 거치는 방식이라서 P2P 통신이 아니게 된다. 서버의 성능이 중요해짐.
    
    이러한 과정들을 이용하면, 최대 3개의 주소를 얻게 된다. (자신의 사설 ip와 포트, 자신의 공인 ip와 포트, turn 서버의 ip와 포트)따라서 ICE(Interactive Connectivity Establishment)를 이용하여 최적의 경로를 찾는다.
    
    이러한 모든 과정을 시그널링 이라고 부른다.
    
    > 결론은 시그널링이라는 NAT 우회과정을 거친다는 것이다.(주의, 시그널링 서버는 표준이 없음. 따라서 브라우저 호환성을 생각해야한다.)
    > 
    
    =============================================================
    
    =============================================================
    
    =============================================================경계선을 기준으로 위는 WebRTC를 연결 전 미리 준비하는 과정이다WebRTC 연결이 되면 속도가 빠른 UDP 위에서 데이터를 송수신한다. 다만 UDP는 데이터 손실이 많이 발생한다.그래서 중요한 파일들을 전송할 때 파일이 동작하지 않을 수 있다.그래서 오디오나 미디어에 사용된다.(조금 끊겨도 괜찮기 때문)(안정적인 TCP를 사용할 수 있지만, 미디어 스트리밍에 적합하지 않고 모든 브라우저가 TCP를 지원하는 것은 아니다.)
    
- 중첩댓글 구현
    
    ### ERD 구조
    
    ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%200a34b4d2350842a4a2d2b2dcd62aa462/Untitled.png)
    
    중첩댓글 기능을 위한 컬럼값
    
    댓글 그룹 - (group) 
    
    댓글 순서 - (order)
    
    댓글 계층 - (step)
    
    부모ID - (parent_seq)
    
    대댓글수 - (child_number)
    
    ### 댓글
    
    - 댓글이 저장될때마다 group 의 최댓값을 찾아 +1 하여 저장
    
    ### 중첩댓글
    
    - 부모 댓글을 찾아 부모의 step+1 저장
    - 부모 그룹의 모든 자식의 갯수를  더한값 child_number_sum 찾기
    - 부모 그룹의 step 최댓값 max_step찾기
    - 같은 group의 order 변경
        - step이 max_step보다 크다면 order = order + 1
        - step이 max_step보다 작으면 order = child_number_sum + 1
        - step이 max_step과 같다면 order = order + child_number_sum + 1
    - 부모의 child_number +1 업데이트
    
    ### 출력
    
    - group의 내림차순 + order의 내림차순으로 정렬 후 출력
    
- 서버 구축 과정
    
    # ec2에서 구축 시작
    
    pre
    
    윈도우 환경에서 ssh 접속 설정 - ssh aws_ryu
    
    zsh설치
    
    ```powershell
    sudo apt-get install zsh
    
    cat /etc/shells
    
    chsh -s $(which zsh)
    
    sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
    
    # 또는
    sh -c "$(wget -O- https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
    
    # 또는
    sh -c "$(fetch -o - https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
    
    vim ~/.zshrc
    
    ZSH_THEME="agnoster"
    
    # autojump 설치
    > brew install autojump
    
    > git clone git://github.com/wting/autojump.git
    
    # Run the installation script and follow on screen instructions.
    > cd autojump
    > ./install.py
    
    # zsh-autosuggestion 설치
    > brew install zsh-autosuggestions
    
    > git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions
    
    # zsh-syntax-highlighting 설치
    > brew install zsh-syntax-highlighting
    
    > git clone https://github.com/zsh-users/zsh-syntax-highlighting.git
    
    # 적용
    > vi ~/.zshrc
     pligins=( 
     	git
        	autojump
        	zsh-autosuggestions
        	zsh-syntax-highlighting
     )
     
    > source ~/.zshrc
    ```
    
    1. ec2 보안 설정 (ssh 20, tcp 80 9999)
    2. docker 설치 (apt updage, apt upgrade, apt install [docker.io](http://docker.io) -y)
    3. docker-compose 설치
        
        ```powershell
        curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
        
        chmod +x /usr/local/bin/docker-compose
        
        docker-compose --version
        ```
        
    4. nginx설치 (apt install nginx) - /etc/nginx/nginx.conf, sudo service nginx restart
    5. jenkins 컨테이너 설치 - docker-compose 구성 (docker-compose up -d)
    6. git과 webhook 설정
        1. ssh 키 생성
            
            ```powershell
            mkdir /var/lib/jenkins/.ssh
            
            ssh-keygen -t rsa -b 4096 -C "wh-key" -f /var/lib/jenkins/.ssh/jenkins-github-wh
            ```
            
        2. git deploy키에 퍼블릭키 추가
        3. jenkins Credentials 설정 (cat /var/lib/jenkins/.ssh/jenking-github-wh)
        4. git webhook 설정
        5. plugin 설치
    7. 파이프라인 구성
        
        ```powershell
        pipeline {
            agent any
            options {
                timeout(time: 1, unit: 'HOURS')
            }
            environment {
                SOURCE_CODE_URL = 'https://github.com/ryuhm48/whereismyhome.git'
                RELEASE_BRANCH = 'master'
            }
            stages {
                stage('Init') {
                    steps {
                        catchError {
                            echo 'docker compose down'
                            sh 'docker-compose down'
                            // echo 'clear fe'
                            // sh 'docker stop fe'
                            // sh 'docker rm fe'
                            
                            // echo 'clear be'
                            // sh 'docker stop be'
                            // sh 'docker rm be'
                            
                            // echo 'clear image'
                            // sh 'docker rmi todo/backend'
                            // sh 'docker rmi todo/frontend'
                            deleteDir()
                        }
                    }
                    
                }
        
                stage('clone') {
                    steps { 
                        git credentialsId: '234c2501-0f5f-4581-b511-9a28a5f2b3fd', url: 'git@github.com:ryuhm48/whereismyhome.git'
                        sh "ls -al"
                    }
                }
                stage('frontend dockerizing') {
                    steps {
                        echo "FE"
                        // sh "docker build -t todo/frontend ./"
                    }
                }
        
                stage('backend dockerizing') {
                    steps {
                        dir("./BE"){
                            echo "BE"
                            sh "pwd"
                            sh "mvn package"
            
                            sh "ls"
                        }
                    }
                }
                stage('docker-compose') {
                    steps {
                        dir("./"){
                            sh "ls -al"
                            sh "docker-compose up -d --build"
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        echo "end"
                        // sh '''
                        //   docker run --name fe -d -p 5000:5000 todo/frontend
        
                        //   docker run --name be -d -p 8080:8080 todo/backend 
                        // '''
                    }
                }
            }
        }
        ```
        
- 윈도우로 도커 설정하는 법
    
    윈도우로 도커 설정하는 법
    
    1. 윈도우용 도커 설치
    [https://desktop.docker.com/win/main/amd64/Docker Desktop Installer.exe](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe)
    2. power shell 관리자 모드 켜기(해당 명령어 하기)
    dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
    dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
    3. wsl 설치
    [https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi](https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi)
    4. kurento 서버 돌려보기(cmd에 해당 명령어 쳐보기: 한번만 치면 다음부터는 도커에서 on/off가능)
    docker run -p 8888:8888/tcp -p 5000-5050:5000-5050/udp -e KMS_MIN_PORT=5000 -e KMS_MAX_PORT=5050 kurento/kurento-media-server:latest &

---

### 대충 정리

- **완전 Default 기능**
    - **유저**:
        - 회원가입
        - 마이페이지
            - 회원정보 수정
            - 회원탈퇴
            - 내 일정확인
            - 내 스터디 목록
            - 좋아요 스터디 목록
        - 스터디신청하기
        - 자기 진단(설문)
    - **스터디 홍보 카테고리**:
        - 스터디 검색 카테고라이징(디테일하게)
        - 스터디장의 스터디 홍보 글 CRUD
        - 관심있는 스터디 좋아요
        - 스터디 평균 활동시간? 확인
    - **스터디**
        - 스터디 기본정보확인
        - 스터디 CRUD
        - 회원승인기능
        - 스터디룸에서 스터디 진행
        - 스터디 공지게시판
        - 스터디 자유게시판
        - 스터디장의 스터디 관리하는 기능
        - 스터디 달력 연동
    - **커뮤니티**:
        - 각종 게시판 (글 CRUD,+댓글???)
    - **관리자** :
        - 유저 정보 수정, 탈퇴
        - 멘토 역할 관리(멘토 추가시)
        

- **추가 기능**
    - 멘토: 코드리뷰 신청하고 작성하기, 멘토참석을 위한 미끼(github 벳지나..), 멘토카테고리 분류(프로젝트? 코드리뷰?)
    - 스터디룸: 사다리 타기, 투표하기, 클릭하면 화면 확대 되게 하기, 시간 설정 해놓고 라이브코딩
    - 열품타(공부한 시간, 랭킹) / 미션 (백준처럼 랭킹, 뱃지 제공)
    - 쪽지 보내기
    - 친구 CRUD
    - 오프라인 스터디룸 신청/추천 + 지도
    - 관리자에게 연락 (카카오api ?)
    - 업적 기능
    - 달성률
    - 스터디 시간 연락 (카카오 api ?)
    - 카카오로 일정 공유하기
    - 기술블로그? 같은 링크 연결해서 오른쪽애이쁘게띄우기 (간지용) 카카오..우아한..라인..
    - mm, 카톡 알림서비스
    - 라이브 코딩 모듈?
    - 싸피기능을 추가하자 → 메뉴, 싸피 지원금 생성기, (싸피인들에 대한 튜토리얼 느낌?)
    - 투표해서 코인 같은 거 모아서 프로필 꾸미는 기능을 추가하자!
    - AR기능 추가해서 사람얼굴에 해바라기씌우는 그거..
    

- 다른 팀들 차별점
    - 코딩테스트용 에디터, 코드윗미, 일타싸피, 유니티..

---

## 업무분담

### 프론트

- Default: 페이지/컴포넌트(기능)별로 나누어서 개발하되, 아직 다들 부족하니 코드리뷰하는 시간을 지속적으로 갖는다.
- 기능나누기 (일단 로그인을 했다고 가정하고 제작)
    - header nav, footer (도균)
    - 로그인,회원가입 (도균)
    - 메인페이지 (연희)
    - 자가진단 (문제, 결과) (주광)
    - 스터디검색 페이지 → 스터디 지원페이지 → 스터디 지원 모달 창 (연희)
    - 스터디 생성 페이지 → 활동주기 선택 모달창 (연희)
    - 게시판리스트 → 게시판 글쓰기, 수정하기, 게시판 상세보기 (도균)
    - 마이페이지, 프로필 모달창, 회원정보수정 (주광)

### 백엔드

유저관련,관리자 - 이신광

게시판,스터디,채팅 - 류한민

WebRTC ,타이머,랭킹- 오의석