### JUSTUDY

SSAFY 2학기 공통 프로젝트

### 프로젝트 진행 기간

2023.01.09 ~ 2023.02.17

### 팀원

김도균: FE, 발표, Design 총괄
<br>
류한민: BE, 팀장, CICD
<br>
서주광: FE, API 총괄
<br>
오의석: BE, WebRTC 총괄
<br>
이신광: BE, UCC, Back-end 총괄
<br>
이연희: FE, Front-end 총괄

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

## **_프로젝트 명세서_**

[회의록 정리](https://www.notion.so/cf1646ad64944a8180672dd4ab57d2a1)

# 기획

[요구사항 명세서](https://www.notion.so/222eead98a7a41b5a2c38d0a227ef9a9)

[FrontEnd Convention](https://www.notion.so/Frontend-58902da3538e46ed819af8ed1999de41)

[BackEnd Convention](https://www.notion.so/Back-End-Convention-c2a0b27112ff45de9dc834dceac5a0d4)

[Git Convention & Jira Convention](https://www.notion.so/Git-Convention-Jira-Convention-51df0771f294443497ab435665bbc3c5)

# 설계

[와이어 프레임](https://www.notion.so/Mockup-8544d56b3d184b32adaf10b268c79a64)

[API 명세서](https://www.notion.so/API-f50f5cd46a14477398f0583fee05f5ad)

[[DB] ERD 및 테이블 명세서](https://www.notion.so/DB-ERD-0cd67de4cb3d407487207b1733997f4f)

[서비스 아키텍처 구조](https://www.notion.so/313d5a098e8049dd8947b351bc11cc28)

# 개발

[트러블 슈팅 문서](https://www.notion.so/45928b42382d432aa8804f41f0c5d7b0)

[학습 문서](https://www.notion.so/fd3bdd2f67dc452bb546ab2ac12ba6b3)

  <!-- # 테스트 -->

# 배포 및 유지보수

[포팅 메뉴얼](https://www.notion.so/481710d796d740e397b2bb090f507a06)

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
