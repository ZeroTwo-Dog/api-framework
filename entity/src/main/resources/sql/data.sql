
delete from USER;
insert into USER (USER_NO, USER_ID, EMAIL)values (1, "wl507","wl5407@gmail.com");
insert into USER (USER_NO, USER_ID, EMAIL) values (2, "283po1","283po1@naver.com");
insert into USER (USER_NO, USER_ID, EMAIL)values (3, "wls507","wls5047@naver.com");

delete from ROLE;
insert into ROLE (ROLE_NO, ROLE_TYPE)values (100, "ROLE_USER");
insert into ROLE (ROLE_NO, ROLE_TYPE)values (200, "ROLE_SYS_ADMIN");

delete from USER_ROLES;
insert into USER_ROLES (USER_NO, ROLE_NO)values (1, 100);
insert into USER_ROLES (USER_NO, ROLE_NO)values (2, 200);
insert into USER_ROLES (USER_NO, ROLE_NO)values (3, 100);
insert into USER_ROLES (USER_NO, ROLE_NO)values (3, 200);
