insert into role_category (`name`) values ('watcher');
insert into role_category (`name`) values ('itacademy');
insert into role_category (`name`) values ('tes');

insert into role (`name`,category_id) values ('recrouter',1);
insert into role (`name`,category_id) values ('guest',1);
insert into role (`name`,category_id) values ('teacher',2);
insert into role (`name`,category_id) values ('coordinator',2);
insert into role (`name`,category_id) values ('admin',2);
insert into role (`name`,category_id) values ('tes',3);

insert into status_category (`name`) values ('future');
insert into status_category (`name`) values ('current');
insert into status_category (`name`) values ('finished');

insert into `status` (`name`,category_id) values ('planned',1);
insert into `status` (`name`,category_id) values ('boarding',1);
insert into `status` (`name`,category_id) values ('before start',2);
insert into `status` (`name`,category_id) values ('in-process',2);
insert into `status` (`name`,category_id) values ('offering',2);
insert into `status` (`name`,category_id) values ('graduated',3);

insert into specialization (`name`) values ('oop_java_core');
insert into specialization (`name`) values ('js_core');
insert into specialization (`name`) values ('web_C#_.net');
insert into specialization (`name`) values ('testing_ISTQB');
insert into specialization (`name`) values ('HTML5_CSS3_JS_foundamentals');
insert into specialization (`name`) values ('iOS');
insert into specialization (`name`) values ('JAVA');
insert into specialization (`name`) values ('java_for_non_it');
insert into specialization (`name`) values ('.NET');
insert into specialization (`name`) values ('Database');
insert into specialization (`name`) values ('manual_quality_control');
insert into specialization (`name`) values ('ua_c++');
insert into specialization (`name`) values ('automation_testing_quality_control');
insert into specialization (`name`) values ('devops');
insert into specialization (`name`) values ('lamp');
insert into specialization (`name`) values ('web_ui');
insert into specialization (`name`) values ('web_ui_full_stack');
insert into specialization (`name`) values ('UX');
insert into specialization (`name`) values ('ua_web_ui_js');
insert into specialization (`name`) values ('quality_control_for_cloud_computing');
insert into specialization (`name`) values ('angular_boot_camp');
insert into specialization (`name`) values ('ua_go_boot_camp');
insert into specialization (`name`) values ('ua_test_automation_boot_camp_for_mobile_with_python');
insert into specialization (`name`) values ('ua_software_ingeneering_with_testing');

insert into location (`name`,coordinator_id) values ('Dnipro',NULL);
insert into location (`name`,coordinator_id) values ('Sofia', NULL);
insert into location (`name`,coordinator_id) values ('Odessa', NULL);

insert into budget_owner (`name`) values ('SOFTSERVE');
insert into budget_owner (`name`) values ('OPEN GROUP');

insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Oleg','Shvets',3,'OlegShvets','ghd22df',1);
insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Oleg','NoGroups',3,'NogroupsUser','ghd22df',1);
insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Oleg','NoShvets',3,'NoOlegShvets','ghd22df',3);
insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Dmytro','Petin',4,'DmytroPetin','fgdfg24sd',1);
insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Lucas','Lukichich',4,'LukasLukichich','fjgf24sd',2);

insert into `educational_group` (`name`,location_id,start_date,finish_date, status_id,specialization_id,budget_owner_id) values ('DP-115',1,'2017-04-29', '2017-08-18',1,7,1);
insert into `educational_group` (`name`,location_id,start_date,finish_date, status_id,specialization_id,budget_owner_id) values ('DP-116',1,'2017-04-29', '2017-08-18',6,6,1);
insert into `educational_group` (`name`,location_id,start_date,finish_date, status_id,specialization_id,budget_owner_id) values ('SO-115',2,'2017-04-29', '2017-08-18',4,7,1);
insert into `educational_group` (`name`,location_id,start_date,finish_date, status_id,specialization_id,budget_owner_id,is_deleted) values ('DP-1115',1,'2017-04-29', '2017-08-18',6,7,1,TRUE);

insert into expert (edu_group_id, expert_name) values (1, 'Sergey');

insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Lev','Bukhanets',2,'Myst1c','sdfhs332w',1);
insert into user (first_name,last_name,role_id,nick_name,password_hash_code,location_id) values ('Dmytro','Kholod',2,'DimaKh','sdasdfdew',1);

insert into group_teacher (teacher_id,group_id) values (1,1);
insert into group_teacher (teacher_id,group_id) values (1,2);

insert into event_type (`name`,`is_key_date`) values ('demo',TRUE);
insert into event_type (`name`) values ('lesson');

insert into room (`number`,location_id) values ('705',1);
insert into room (`number`,location_id) values ('703',1);

insert into `event` (`datetime`,duration,group_id,event_type_id,room_id) values ('2017-07-24 18:00:00',120,1,2,1);
insert into `event` (`datetime`,duration,group_id,event_type_id,room_id) values ('2017-07-25 18:00:00',120,1,1,1);
insert into `event` (`datetime`,duration,group_id,event_type_id,room_id) values ('2017-07-24 18:00:00',120,2,2,1);
insert into `event` (`datetime`,duration,group_id,event_type_id,room_id) values ('2017-07-25 18:00:00',120,2,1,1);

update location SET coordinator_id = 2 WHERE name='Dnipro';
update location SET coordinator_id = 3 WHERE name='Sofia';