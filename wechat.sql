/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/4 14:48:01                            */
/*==============================================================*/


drop table if exists Answer;

drop table if exists Choice;

drop table if exists CorrectAnswer;

drop table if exists Homework;

drop table if exists HwGrade;

drop table if exists Notification;

drop table if exists Question;

drop table if exists SignIn;

drop table if exists SignInRecord;

drop table if exists Student;

drop table if exists TA;

drop table if exists Teacher;

drop table if exists Test;

drop table if exists Vote;

drop table if exists VoteChoice;

drop table if exists VoteChoose;

/*==============================================================*/
/* Table: Answer                                                */
/*==============================================================*/
create table Answer
(
   studentID            varchar(28) not null,
   studentGroupID       varchar(28) not null,
   testID               int not null,
   questionID           int not null,
   answer               varchar(8),
   primary key (testID, studentID, studentGroupID, questionID)
);

/*==============================================================*/
/* Table: Choice                                                */
/*==============================================================*/
create table Choice
(
   testID               int not null,
   questionID           int not null,
   choiceNo             varchar(1) not null,
   choiceContent        varchar(1024),
   primary key (testID, questionID, choiceNo)
);

/*==============================================================*/
/* Table: CorrectAnswer                                         */
/*==============================================================*/
create table CorrectAnswer
(
   testID               int not null,
   questionID           int not null,
   correctAns           varchar(8),
   primary key (testID, questionID)
);

/*==============================================================*/
/* Table: Homework                                              */
/*==============================================================*/
create table Homework
(
   HomeworkID           int not null,
   homeworkGroupID      varchar(28),
   homeworkContent      varchar(1024),
   hwdate               datetime,
   deadline             datetime,
   publisherID          varchar(28),
   primary key (HomeworkID)
);

/*==============================================================*/
/* Table: HwGrade                                               */
/*==============================================================*/
create table HwGrade
(
   studentID            varchar(28) not null,
   studentGroupID       varchar(28) not null,
   HomeworkID           int not null,
   grade                varchar(5),
   primary key (studentID, studentGroupID, HomeworkID)
);

/*==============================================================*/
/* Table: Notification                                          */
/*==============================================================*/
create table Notification
(
   notificationID       int not null,
   notificationGroupID  varchar(28),
   notificationContent  varchar(1024),
   notificationDate     datetime,
   notificationPublisherID varchar(28),
   primary key (notificationID)
);

/*==============================================================*/
/* Table: Question                                              */
/*==============================================================*/
create table Question
(
   testID               int not null,
   questionID           int not null,
   questionContent      varchar(1024),
   primary key (testID, questionID)
);

/*==============================================================*/
/* Table: SignIn                                                */
/*==============================================================*/
create table SignIn
(
   signInID             int not null,
   signInGroupID        varchar(28),
   primary key (signInID)
);

/*==============================================================*/
/* Table: SignInRecord                                          */
/*==============================================================*/
create table SignInRecord
(
   studentID            varchar(28) not null,
   studentGroupID       varchar(28) not null,
   signInID             int not null,
   present              bool,
   primary key (studentID, studentGroupID, signInID)
);

/*==============================================================*/
/* Table: Student                                               */
/*==============================================================*/
create table Student
(
   studentID            varchar(28) not null,
   studentGroupID       varchar(28) not null,
   sno                  varchar(20),
   sname                varchar(20),
   primary key (studentID, studentGroupID)
);

/*==============================================================*/
/* Table: TA                                                    */
/*==============================================================*/
create table TA
(
   TAID                 varchar(28) not null,
   TAGroupID            varchar(28) not null,
   primary key (TAID, TAGroupID)
);

/*==============================================================*/
/* Table: Teacher                                               */
/*==============================================================*/
create table Teacher
(
   teacherID            varchar(28) not null,
   teacherGroupID       varchar(28) not null,
   primary key (teacherID, teacherGroupID)
);

/*==============================================================*/
/* Table: Test                                                  */
/*==============================================================*/
create table Test
(
   testID               int not null,
   testGroupID          varchar(28),
   testContent          varchar(1024),
   primary key (testID)
);

/*==============================================================*/
/* Table: Vote                                                  */
/*==============================================================*/
create table Vote
(
   voteID               int not null,
   voteGroupID          varchar(28),
   voteContent          varchar(1024),
   primary key (voteID)
);

/*==============================================================*/
/* Table: VoteChoice                                            */
/*==============================================================*/
create table VoteChoice
(
   voteID               int not null,
   voteChoiceNo         varchar(1) not null,
   voteChoiceContent    varchar(1024),
   primary key (voteID, voteChoiceNo)
);

/*==============================================================*/
/* Table: VoteChoose                                            */
/*==============================================================*/
create table VoteChoose
(
   studentID            varchar(28) not null,
   studentGroupID       varchar(28) not null,
   voteID               int not null,
   voteChoose           varchar(8),
   primary key (studentID, studentGroupID, voteID)
);

alter table Answer add constraint FK_Answer foreign key (studentID, studentGroupID)
      references Student (studentID, studentGroupID) on delete restrict on update restrict;

alter table Answer add constraint FK_Answer2 foreign key (testID, questionID)
      references Question (testID, questionID) on delete restrict on update restrict;

alter table Choice add constraint FK_Relationship_2 foreign key (testID, questionID)
      references Question (testID, questionID) on delete restrict on update restrict;

alter table CorrectAnswer add constraint FK_Relationship_3 foreign key (testID, questionID)
      references Question (testID, questionID) on delete restrict on update restrict;

alter table HwGrade add constraint FK_HwGrade foreign key (studentID, studentGroupID)
      references Student (studentID, studentGroupID) on delete restrict on update restrict;

alter table HwGrade add constraint FK_HwGrade2 foreign key (HomeworkID)
      references Homework (HomeworkID) on delete restrict on update restrict;

alter table Question add constraint FK_Relationship_1 foreign key (testID)
      references Test (testID) on delete restrict on update restrict;

alter table SignInRecord add constraint FK_SignInRecord foreign key (studentID, studentGroupID)
      references Student (studentID, studentGroupID) on delete restrict on update restrict;

alter table SignInRecord add constraint FK_SignInRecord2 foreign key (signInID)
      references SignIn (signInID) on delete restrict on update restrict;

alter table VoteChoice add constraint FK_Relationship_4 foreign key (voteID)
      references Vote (voteID) on delete restrict on update restrict;

alter table VoteChoose add constraint FK_VoteChoose foreign key (studentID, studentGroupID)
      references Student (studentID, studentGroupID) on delete restrict on update restrict;

alter table VoteChoose add constraint FK_VoteChoose2 foreign key (voteID)
      references Vote (voteID) on delete restrict on update restrict;

