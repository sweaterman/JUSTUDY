# JUSTUDY - WebRTC 기반 스터디 모임 사이트

## ✏ 소개 영상

---

[ucc.mp4](/img/ucc.mp4)

## ✏ 프로젝트 진행 기간

---

2022.01.09(월) ~ 2022.02.17(금) - 약 6주

## ✏ JUSTUDY - 개요

---

### IT 개발자 지망생들을 위한 토탈 웹 서비스

 삼성청년 SW아카데미에서 제공하는 EC2 서버를 기반으로 실제적인 서비스 제공과 늘어나는 IT개발자 지망생을 위한 온라인 스터디 서비스를 기획하게 되었습니다. 서비스 기획을 위해 삼성청년 SW아카데미 교육생들을 대상으로 인터뷰와 설문을 진행하였으며, 이를 바탕으로 단순한 webRTC를 활용한 기능이 아닌 서비스의 목표 고객이 추구하는 목표를 도울 플랫폼 형태의 토탈 웹 서비스를 구성할 수 있었습니다. JUSTUDY를 통해 IT 개발자 지망생들이 필요한 기술 스택 공부와 온라인 스터디 모집 및 참여는 물론 자기 주도 학습을 위한 여러 툴들을 활용해 목표하는 바를 이루었으면 좋겠습니다.

## ✏ 주요 기능

---

- 주니어 개발자를 위한 스터디 상위/하위 카테고리 별로 모집 및 관리 환경 제공
- 화상 스터디 및 스터디에 필요한 부가 기능을 추가한 webRTC 서비스
- 자기주도 학습을 위한 컴퓨터 비전 기반 자기 검열 기능
- 삼성청년SW아카데미생들 사이에 공부 시너지를 위한 커뮤니티 제공
- 페이지 관리를 위한 관리자 페이지
- 랭킹, 스터디 뱃지를 활용한 공부 의지 고취 시스템

## ✏ 주요 기술

---

Back-end

- JAVA Open JDK 11
- Spring boot 2.7.7
- JPA
- queryDSL
- swagger2
- mysql 8.0.x
- Kurento media server
- Coturn TURN server

Front-end

- nodejs 18.12.1
- Visual Studio Code IDE
- Vue 2.6.14
- Vuetify 2
- Vue-router 3.6.5
- Vuex 3.6.2
- axios 1.2.2
- chart.js
- FaceAPI
- kurento-utils

CI/CD

- AWS EC2
- Jenkins
- NGINX
- Docker

협업 툴 & 기획

- Git + GitLab
- Notion
- JIRA
- MatterMost
- Webex
- Figma
- ERD Cloud

## ✏ 프로젝트 파일 구조

---

```
back
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─justudy
    │  │          └─backend
    │  │              ├─admin
    │  │              ├─category
    │  │              ├─common
    │  │              ├─community
    │  │              ├─config
    │  │              ├─exception
    │  │              ├─file
    │  │              ├─GroupCall
    │  │              ├─login
    │  │              ├─member
    │  │              ├─rank
    │  │              ├─report
    │  │              ├─study
    │  │              ├─timer
    │  │              └─util
    │  └─resources
    └─test
        └─java
            └─com
                └─justudy
                    └─backend
                        ├─category
                        ├─community
                        ├─config
                        ├─date
                        ├─login
                        ├─member
                        └─study
```

```
justudy-front
  ├── node_modules
  ├── public
  └── src
      ├── assets
      ├── components
      │   ├── admin
      │   ├── common
      │   ├── community
      │   ├── mypage
      │   ├── ranking
      │   ├── study
      │   ├── timer
      │   └── webRTC
      ├── data
      ├── plugins
      ├── router
      ├── scss
      ├── store
      │   └── modules
      ├── utils
      └── views
          ├── admin
          ├── community
          ├── login
          ├── main
          ├── mypage
          ├── ranking
          ├── self-test
          ├── study
          ├── studyOne
          ├── timer
          └── webRTC
```

## ✏ 아키텍쳐 설계도

---

## ✏ 협업 환경

---

- GitLab
    - 코드 버전 관리
    - Merge Request 시, 리뷰어를 정해 리뷰를 진행하고 피드백
- JIRA
    - 매주 월요일 오전, 해당 주차의 목표를 정해 스프린트 진행
    - Story point 설정 후, 해당 이슈에 커밋 연결하여 확인 가능
- 회의
    - 매일 아침 10~15분 스크럼을 통해 각자 전 날의 달성 과제와 이슈 사항을 보고하고, 그 날의 도전 과제 브리핑
- Notion
    - 후순위로 생각해야 할 점이 생기면, 노션에 브레인스토밍 식으로 기재 후 검토
    - Git, JIRA, Front-end, Back-end 컨벤션 정리
    - 피그마를 활용한 MockUP, ERD 설계도, 요구사항 명세서, API 명세서 공유
    - 학습내용이나 트러블 슈팅시 노션을 통한 문서화

## ✏ 팀원 역할 분배

---

- 류한민[BE]
    - 서버 담당 - CICD 자동화 구현
    - API 설계 및 관리
- 이신광[BE]
    - 유저, 게시글, 카테고리 API 설계 및 관리
- 오의석[BE]
    - WebRTC기능 구현
    - API 설계 및 관리
- 서주광[FE]
    - JavaScript 이용한 주요 컴포넌트 개발
    - API 설계 및 관리
- 김도균[FE]
    - UI/UX 담당
    - 기획 및 와이어프레임
- 이연희[FE]
    - UI/UX 담당
    - 와이어프레임 및 API 설계

## ✏ 프로젝트 산출물

---

- [Front-end Convention](https://www.notion.so/Front-End-Convention-58902da3538e46ed819af8ed1999de41)
- [Back-end Convention](https://www.notion.so/Back-End-Convention-c2a0b27112ff45de9dc834dceac5a0d4)
- [요구사항 명세서](https://www.notion.so/222eead98a7a41b5a2c38d0a227ef9a9)
- [MockUp](https://www.notion.so/Mockup-8544d56b3d184b32adaf10b268c79a64)
- [ERD 및 테이블 명세서](https://www.notion.so/ERD-0cd67de4cb3d407487207b1733997f4f)
- [API 명세서](https://docs.google.com/spreadsheets/d/1vRqVG1yKFz3FTqCCotNTmszlJB8_D4nm1iDSqHvz46k/edit?usp=sharing)
- [트러블 슈팅 문서](https://www.notion.so/45928b42382d432aa8804f41f0c5d7b0)
- [학습 문서](https://www.notion.so/fd3bdd2f67dc452bb546ab2ac12ba6b3)
- [포팅 메뉴얼](https://www.notion.so/481710d796d740e397b2bb090f507a06)

## ✏ JUSTUDY 서비스 화면

---

- 커뮤니티
    - 불편한 내용의 커뮤니티는 신고가 가능하다.
    - 더 나아가 좋아요 및 북마크 기능이 있어 마이페이지에서 언제든 볼 수 있다.
    - 또한, 댓글 및 대댓글까지 기능 구현이 되어있다.
![동작](/img/커뮤니티.gif)

- 스터디지원
    - 마이페이지에서 부족한 공부 카테고리를 알 수 있다.
    - 그와 맞는 스터디를 찾고 신청하는 것이 가능하다.
![동작](/img/스터디지원.gif)

- 스터디수락
    - 스터디장은 지원현황을 보고 수락 및 거절을 할 수 있다.
![동작](/img/스터디수락.gif)

- 스터디기능
    - 스터디원이면 스터디 게시판에 글을 쓰고, 좋아요 및 북마크가 가능하다.
    - 해당 게시글은 스터디원만 볼 수 있다.
    - 더 나아가, 스터디가 진행중이면 'on Air'표시가 나타난다.
    - 언제든 들어가서 같이 코딩 작업도 할 수 있다.
![동작](/img/스터디기능.gif)

- 관리자
    - 대시보드로 신고 및 공부 비율 등을 알 수 있다.
    - 신고 게시판에서 신고 내용을 확인하고, 해당 글을 볼 수 없게 세팅할 수 있다.
    - 사용자 관리에서는 해당 사용자를 찾아 블랙리스트 처리가 가능하다.
    - 스터디관리 및 게시판 관리도 존재하며, 블랙리스트 및 정보 확인 가능하다.
![동작](/img/관리자기능.gif)

- 회원가입
    - 중복 확인 및 정규표현식 기능을 넣었다. 그리고 mattermost회원 여부를 판단하여 회원가입 처리가 가능하다
![동작](/img/회원가입.gif)

- 자가 진단 설문
    - 회원가입 직후 자가진단 페이지를 통해 해당 개발자의 수준을 대략적으로 파악할 수 있다.
    물론 이것은 하나의 가이드라인으로 원한다면 변경할 수 있다.
![동작](/img/자가진단.gif)

- 타이머
    - 혼자 공부했을 때, 공부량을 측정할 수 있다. 5초마다 DB로 전송하여 공부량이 갱신된다.
![동작](/img/타이머기능.gif)

- 팔로우
    - 팔로우 및 팔로잉된 사람들에 대한 대략적인 정보와 공부량을 알 수 있다.
![동작](/img/팔로우기능.gif)

- 온라인 스터디 기능
    - 화상회의 도중 제비뽑기가 가능하다. 각자의 역할을 공정하게 지정할 수 있다.
    - 화상회의 도중 채팅이 가능하다. 누군가 발표하고 있을때, 급하게 전달할 내용이 있으면 채팅을 치면 된다. 더 나아가, 느낌표 표시로 글이 왔는지 파악이 가능하다.
    - 화상회의 도중 에디터 기능이 가능하다. WebRTC의 DataChannel에 연결되어 있어, 동시에 여러명이 같이 코딩작업이 가능하다.
    - 화상회의 도중 투표를 통해 해당 인원을 음소거 할 수 있다
    - 화상회의 도중 투표를 통해 스터디에 방해가 되는 인원을 강제 퇴장시킬 수 있다.
    - 온라인 스터디를 마치고 싶은데, 그만하자고 말을 하기 어려울 때, 스터디 끝내기를 제안할 수 있다. 투표를 통해 과반수가 허락하면 온라인 스터디가 끝난다.
![동작](/img/온라인스터디기능1.gif)
![동작](/img/온라인스터디기능2.gif)
![동작](/img/온라인스터디기능3.gif)
![동작](/img/온라인스터디기능4.gif)
![동작](/img/온라인스터디기능5.gif)
![동작](/img/온라인스터디기능6.gif)

- 랭킹
    - 개인 랭킹과 스터디 랭킹을 통해 다른 사람들의 공부량을 파악할 수 있다.
![캡처](/img/랭킹1.png)
![캡처](/img/랭킹2.png)
