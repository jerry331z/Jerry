/* 회원가입 테이블 */

drop table jerry_user;
create table jerry_user
(
    user_no                   number primary key,
    user_id                   varchar2(100) not null unique,
    user_pw                   varchar2(200) not null,
    user_nickname             varchar2(20)  not null unique,
    user_image                varchar2(100) default 'default-user-image.jpg',
    user_gender               varchar2(100) default 'N',
    user_birth                date          not null,
    user_phone                varchar2(20)  not null unique,
    user_email                varchar2(200) not null unique,
    user_status               varchar2(30)  default 'active',
    user_join_date            date          default sysdate,
    user_last_connection_date date          default sysdate
);

/* 회원가입 테이블 시퀸스 */
drop sequence jerry_user_seq;
create sequence jerry_user_seq;

commit;