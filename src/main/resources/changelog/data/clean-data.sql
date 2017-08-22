SET FOREIGN_KEY_CHECKS=0;
DELETE FROM group_teacher;
DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT=1;
DELETE FROM role;
ALTER TABLE role AUTO_INCREMENT=1;
DELETE FROM role_category;
ALTER TABLE role_category AUTO_INCREMENT=1;
DELETE FROM educational_group;
ALTER TABLE educational_group AUTO_INCREMENT=1;
DELETE FROM status;
ALTER TABLE status AUTO_INCREMENT=1;
DELETE FROM status_category;
ALTER TABLE status_category AUTO_INCREMENT=1;
DELETE FROM specialization;
ALTER TABLE specialization AUTO_INCREMENT=1;
DELETE FROM location;
ALTER TABLE location AUTO_INCREMENT=1;
DELETE FROM budget_owner;
ALTER TABLE budget_owner AUTO_INCREMENT=1;
DELETE FROM expert;
ALTER TABLE expert AUTO_INCREMENT=1;
DELETE FROM room;
ALTER TABLE room AUTO_INCREMENT=1;
DELETE FROM event;
ALTER TABLE event AUTO_INCREMENT=1;
DELETE FROM event_type;
ALTER TABLE event_type AUTO_INCREMENT=1;
DELETE FROM student;
ALTER TABLE student AUTO_INCREMENT=1;
DELETE FROM english_level;
ALTER TABLE english_level AUTO_INCREMENT=1;
DELETE FROM strategy;
ALTER TABLE strategy AUTO_INCREMENT=1;
DELETE FROM key_event_template;
ALTER TABLE key_event_template AUTO_INCREMENT=1;
DELETE FROM status_template;
ALTER TABLE status_template AUTO_INCREMENT=1;
DELETE FROM task_scheduler;
ALTER TABLE task_scheduler AUTO_INCREMENT=1;
SET FOREIGN_KEY_CHECKS=1;