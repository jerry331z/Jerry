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

--  게시글 조회수 테이블
drop table jerry_view_page;
create table jerry_view_page
(
    view_page_no      number primary key,
    board_no          number,
    lockup_ip         varchar(200),
    view_inquiry_time date default sysdate,
    constraint jerry_view_board_no foreign key (board_no) references jerry_board (board_no)
);

--  게시글 조회수 시퀸스
drop sequence jerry_view_page_seq;
create sequence jerry_view_page_seq;

create table jerry_board_like
(
    like_no   number primary key,
    user_no   number,
    board_no  number,
    like_date date default sysdate,
    constraint jerry_like_userNo foreign key (user_no) references jerry_user (user_no),
    constraint jerry_like_boardNo foreign key (board_no) references jerry_board (board_no)
);

create sequence jerry_board_like_seq;

--  게시글 북마크
drop table jerry_book_mark;
create table jerry_book_mark
(
    book_mark_no number primary key,
    board_no     number not null,
    user_no      number not null,
    reg_date     date default sysdate,
    constraint bookMark_boardNo foreign key (board_no) references jerry_board (board_no),
    constraint bookMark_userNo foreign key (user_no) references jerry_user (user_no)
);

--  게시글 북마크 시퀸스
drop sequence jerry_book_mark_seq;
create sequence jerry_book_mark_seq;


create table jerry_board_search_category
(
    search_category_no number primary key,
    search_type        varchar2(200) not null
);

--  게시글 검색 카테고리 시퀸스
drop sequence jerry_search_category_seq;
create sequence jerry_search_category_seq;

insert into JERRY_BOARD_SEARCH_CATEGORY (search_category_no, search_type)
values (jerry_search_category_seq.nextval, '제목');
insert into JERRY_BOARD_SEARCH_CATEGORY (search_category_no, search_type)
values (jerry_search_category_seq.nextval, '내용');
insert into JERRY_BOARD_SEARCH_CATEGORY (search_category_no, search_type)
values (jerry_search_category_seq.nextval, '작성자');
insert into JERRY_BOARD_SEARCH_CATEGORY (search_category_no, search_type)
values (jerry_search_category_seq.nextval, '제목+내용');
insert into JERRY_BOARD_SEARCH_CATEGORY (search_category_no, search_type)
values (jerry_search_category_seq.nextval, '내용+작성자');
insert into JERRY_BOARD_SEARCH_CATEGORY (search_category_no, search_type)
values (jerry_search_category_seq.nextval, '전체');

drop table jerry_board_comment;
create table jerry_board_comment(
                                   comment_no number primary key,
                                   board_no number,
                                   user_no number,
                                   comment_content varchar2(4000) default '',
                                   comment_write_date date default sysdate,
                                   constraint comment_boardNo foreign key(board_no) references jerry_board(board_no),
                                   constraint comment_userNo foreign key(user_no) references jerry_user(user_no)
);

--  게시글 코멘트 시퀸스
drop sequence jerry_board_comment_seq;
create sequence jerry_board_comment_seq;

-- 댓글 좋아요 테이블
create table JERRY_BOARD_COMMENT_LIKE
(
    comment_like_no number  primary key,
    comment_no      number,
    user_no         number,
    comment_like_date date,
    constraint comment_like_commentNo foreign key (comment_no) references jerry_board_comment (comment_no),
    constraint comment_like_userNo foreign key (user_no) references jerry_user (user_no)
);

create sequence jerry_board_comment_like_seq;

DROP TABLE jerry_file;
CREATE TABLE jerry_file(
                           file_no NUMBER PRIMARY KEY,
                           board_no NUMBER,
                           org_file_name VARCHAR2(260) NOT NULL,
                           stored_file_name VARCHAR2(100) NOT NULL,
                           file_size NUMBER,
                           upload_write_date DATE,
                           file_del VARCHAR2(1) NOT NULL,
                           CONSTRAINT file_board_no FOREIGN KEY(board_no) REFERENCES jerry_board(board_no)
);

DROP SEQUENCE jerry_file_seq;
CREATE SEQUENCE jerry_file_seq;