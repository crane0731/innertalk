# README

![ChatGPT Image 2025년 4월 17일 오후 04_14_22.png](ChatGPT_Image_2025%EB%85%84_4%EC%9B%94_17%EC%9D%BC_%EC%98%A4%ED%9B%84_04_14_22.png)

---

## 💌속마음

- 익명 고민 상담 커뮤니티
- 개인 토이프로젝트

---

## 📅 프로젝트 기간

- 2025-04-11 ~ 2025-04-17

---

## 👦🏻인원

- 1인 프로젝트

---

## 📽️서비스 시연 영상

- https://www.youtube.com/watch?v=CbqMNxD6Dn0

---

## 🖼️서비스 이미지

### 메인 화면 - 로그인 안 함                                        메인 화면 - 관리자 로그인 시

![image.png](image.png)

### 로그인 화면

![image.png](image%201.png)

### 회원가입 화면

![image.png](image%202.png)

![image.png](image%203.png)

### 게시글 작성 화면                                                    게시글 상세 화면

![image.png](image%204.png)

![image.png](image%205.png)

### 관리자 페이지 화면                                               마이페이지 화면

![image.png](image%206.png)

![image.png](image%207.png)

---

## 🎯 프로젝트 소개

- "속마음" 은 사용자들이 **익명으로 고민을 털어놓고**,
    
    다른 사람들로부터 **조언과 위로를 받을 수 있는 온라인 커뮤니티**입니다.
    
- 로그인을 기반으로 하지만, 글 작성 및 댓글은 익명으로 처리되어
    
    보다 자유롭고 솔직한 소통이 가능하도록 설계하였습니다.
    
- 간단한 게시판이지만 최대한 실무적으로 생각해보며 개발했습니다.

---

## 💡프로젝트 목적

- SSR 방식으로 뷰를 구성해 thymealf와 Spring MVC , JPA ,  Mybatis를 학습하고
    
    실전에 사용해 봄으로써 이해력을 올리고
    간단하지만 AWS에 배포까지 해봄으로써 다양한 경험을 쌓는것에 중점을 두었습니다.
    

---

## ⚙️ 핵심 기능

- **회원가입 / 로그인**
    
    기본 계정 기반으로 사용자 식별
    
- **익명 게시글 작성**
    
    닉네임 노출 없이 고민글 작성 가능
    
- **댓글 / 조언 등록**
    
    다른 사용자들이 익명으로 조언 가능
    
- **공감 기능**
    
    각 글에 대해 공감 표현
    
- **북마크 기능**
    
    관심 있는 글을 저장하여 다시 보기
    
- **고민 카테고리 분류**
    
    일상,고민,연애,기타로 카테고리 분류
    
- **신고 기능**
    
    부적절한 게시글은 신고 가능
    
- **관리자 기능**
    
    관리자 페이지를 따로 만들어 게시글, 회원, 신고 관리 가능
    

---

## 📄ERD

![속마음.png](%EC%86%8D%EB%A7%88%EC%9D%8C.png)

---

## 🛠️기술 스택

### 🖥️FRONT-END

- HTML
- CSS
- Thymeleaf
- BootStrap

### ⚙️Back-End

- JAVA
- Spring Boot
- Spring Security
- Spring Data Jpa
- MyBatis

### 📚DB

- MySQL

### ☁️CLOUD

- AWS (free-tier)

### 👬collaboration tool

- GitHub

---

## 📝기능명세서

### 🏘️ 1. 홈화면 (Home)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 홈 화면  | `/innertalk/home` | GET | 홈 화면 보기 |

### 🔐 2. 회원 (User)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 회원 탈퇴 | `/innertalk/members/{memberId}` | DELETE | 회원 탈퇴하기 |
| 회원 삭제(관리자) | `/innertalk/admin/members/{memberId}` | DELETE | 관리자가 회원을 삭제하기 |
| 회원가입 | `/innertalk/signup` | POST | 회원 가입하기 |
| 로그인(스프링 시큐리티) | `/innertalk/login` | POST | 로그인 하기 |
| 로그아웃(스프링 시큐리티) | `/innertalk/logout` | POST | 로그아웃 하기 |
| 마이페이지  | `/innertalk/mypage` | GET | 마이페이지 보기 |
| 로그인 페이지  | `/innertalk/login` | GET | 로그인 페이지 보기 |
| 로그아웃 페이지  | `/innertalk/logout` | GET | 로그아웃 페이지 보기 |

---

### 📝 3. 게시글 (Post)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 게시글 보기 | `/innertalk/posts/{postId}` | GET | 게시글 보기 |
| 게시글 쓰기 페이지 | `/innertalk/write` | GET | 게시글 쓰기 페이지 보기 |
| 게시글 쓰기 | `/innertalk/write` | POST | 게시글 쓰기 |
| 게시글 삭제 | `/innertalk/delete/posts/{postId}` | DELETE | 게시글 삭제 |
| 게시글 삭제(관리자) | `/innertalk/admin/delete/posts/{postId}` | POST | 관리자가 게시글 삭제 |

---

### 💬 4. 댓글 (Comment)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 댓글 작성 | `/innertalk/comments` | POST | 해당 글에 댓글 작성 |
| 댓글 삭제 | `/innertalk/posts/{postId}/comments/{commentId}` | DELETE | 해당 글에 댓글 삭제 |

---

### 👍 5. 공감 / 비공감 (Vote)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 공감 | `/innertalk/like/{postId}` | POST | 공감 추가 |

---

### 📌 6. 북마크 (Bookmark)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 북마크 추가 | `/innertalk/bookmark/{postId}` | POST | 북마크 등록 |

---

### 🚨 7. 신고 (Report)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 글 신고 | `/innertalk/report/posts/{postId}` | POST | 부적절한 글 신고 |
| 신고 승인(관리자) | `/innertalk/approve/report/{reportId}` | POST | 관리자가 신고 승인 |
| 신고 거절(관리자) | `/innertalk/reject/report/{reportId}` | POST | 관리자가 신고 거절 |

---

### 👦🏻 8.관리자 화면(Admin)

| 기능 | URL | 방식 | 설명 |
| --- | --- | --- | --- |
| 관리자 페이지 | `/innertalk/admin` | GET | 관리자 페이지 보기 |
