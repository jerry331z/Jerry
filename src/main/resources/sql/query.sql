/* 회원가입 테이블 */
drop table jerry_user;
create table jerry_user
(
    user_no                   number primary key,
    question_no               number,
    user_id                   varchar2(100) not null unique,
    user_pw                   varchar2(200) not null,
    user_nickname             varchar2(20)  not null unique,
    user_image                varchar2(100) default 'default-user-image.jpg',
    user_gender               varchar2(100) default 'N',
    user_birth                date          not null,
    user_phone                varchar2(20)  not null unique,
    user_email                varchar2(200) not null unique,
    user_findAnswer           varchar2(200) not null,
    user_status               varchar2(30)  default 'active',
    user_join_date            date          default sysdate,
    user_last_connection_date date          default sysdate
);

/* 회원가입 테이블 시퀸스 */
drop sequence jerry_user_seq;
create sequence jerry_user_seq;

-- 회원 테이블 (비밀번호 질문, 답변 추가, foreign key 추가 )
alter table jerry_user
    add question_no number;
alter table jerry_user
    add user_findAnswer varchar2(200);
alter table jerry_user
    add constraint user_find_question_no foreign key (question_no) references jerry_find_question (question_no);

--  회원 비밀번호 찾기 질문 테이블
drop table jerry_find_question;
create table jerry_find_question
(
    question_no      number primary key,
    question_content varchar2(200) not null
);

-- 회원 비밀번호 찾기 질문 테이블 시퀸스
drop sequence jerry_find_question_seq;
create sequence jerry_find_question_seq;

-- 회원 비밀번호 찾기 질문 테이블 데이터 생성
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '졸업한 초등학교의 이름은 무엇 입니까?');
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '태어난 고향은 어디 입니까?');
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '가장 친한 사촌의 이름은 무엇 입니까?');
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '어렷을적의 별명은 무엇 입니까?');
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '부모님의 고향은 어디 입니까?');
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '가장 좋아하는 색깔은 무엇 입니까?');
INSERT INTO jerry_find_question(question_no, question_content)
VALUES (jerry_find_question_seq.nextval, '가장 좋아하는 음식은 무엇 입니까?');

-- 자동 로그인 (세션아이디 / 세션리미트)
alter table jerry_user
    add sessionkey varchar2(50) default 'none';
alter table jerry_user
    add sessionlimit date default sysdate;
commit;

-- 게시판 테이블
drop table jerry_board;
create table jerry_board
(
    board_no         number primary key,
    user_no          number        not null,
    category_no      number        not null,
    board_title      varchar(2000) default '제목',
    board_content    varchar(4000) default '내용',
    board_view_count  number        default 0,
    board_write_date timestamp     default sysdate,
    constraint board_userNo foreign key (user_no) references jerry_user (user_no)
);

-- 게시판 테이블 시퀸스
drop sequence jerry_board_seq;
create sequence jerry_board_seq;

insert into jerry_board(board_no, user_no, category_no, board_title, board_content, board_view_count, board_write_date)
values (jerry_board_seq.nextval, 1, 1, '제목', '내용', 0, sysdate);

select * from jerry_board;

-- 카테고리 테이블
DROP TABLE jerry_board_category;
CREATE TABLE jerry_board_category(
                                     category_no      NUMBER PRIMARY KEY,
                                     category_name    varchar2(300)
);

-- 카테고리 시퀸스
DROP SEQUENCE jerry_board_category_seq;
CREATE SEQUENCE jerry_board_category_seq;

-- 게시판 카테고리 데이터
INSERT INTO JERRY_BOARD_CATEGORY(category_no, category_name) VALUES (jerry_board_category_seq.nextval, '사진게시판');