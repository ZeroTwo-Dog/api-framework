
SET FOREIGN_KEY_CHECKS = 0;


delete from USER;
insert into USER (USER_NO, USER_ID, EMAIL, PASSWORD, FAIL_CNT, LOCK_YN, STATUS) values (1, "wl507","wl5407@gmail.com", "test", 0, "N", "ACTIVE" );
insert into USER (USER_NO, USER_ID, EMAIL, PASSWORD, FAIL_CNT, LOCK_YN, STATUS) values (2, "283po1","283po1@naver.com", "test", 0, "N", "ACTIVE" );
insert into USER (USER_NO, USER_ID, EMAIL, PASSWORD, FAIL_CNT, LOCK_YN, STATUS) values (3, "wls507","wls5047@naver.com", "test", 0, "N", "ACTIVE" );

delete from ROLE;
insert into ROLE (ROLE_NO, ROLE_TYPE)values (100, "ROLE_USER");
insert into ROLE (ROLE_NO, ROLE_TYPE)values (200, "ROLE_SYS_ADMIN");

delete from USER_ROLES;
insert into USER_ROLES (USER_NO, ROLE_NO)values (1, 100);
insert into USER_ROLES (USER_NO, ROLE_NO)values (2, 200);
insert into USER_ROLES (USER_NO, ROLE_NO)values (3, 100);
insert into USER_ROLES (USER_NO, ROLE_NO)values (3, 200);


SET FOREIGN_KEY_CHECKS = 1;

