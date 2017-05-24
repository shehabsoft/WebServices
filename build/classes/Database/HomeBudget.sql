drop  DATABASE  IF EXISTS `homebudgetProduction`     ;
CREATE DATABASE `homebudgetProduction`;
DROP TABLE IF EXISTS `homebudgetProduction`.`users`;
DROP TABLE IF EXISTS `homebudgetProduction`.`currency`;
CREATE TABLE  `homebudgetProduction`.`currency` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `homebudgetProduction`.`country`;
CREATE TABLE  `homebudgetProduction`.`country` (
  `id` int(11) NOT NULL auto_increment,
  `arabic_name` varchar(255) NOT NULL,
  `english_name` varchar(45) character set latin1 NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `homebudgetProduction`.`location`;
CREATE TABLE  `homebudgetProduction`.`location` (
  `ID` int(11) NOT NULL auto_increment,
  `arabic_name` varchar(255) NOT NULL,
  `english_name` varchar(255) character set latin1 default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE  `homebudgetProduction`.`users` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `email` varchar(45) character set latin1 NOT NULL default '',
  `password` varchar(45) character set latin1 NOT NULL default '',
  `Address` varchar(45) character set latin1 NOT NULL default '',
  `country_id` int(11) NOT NULL default '0',
  `mobile_number` int(11) NOT NULL default '0',
  `gender_id` int(11) NOT NULL default '0',
  `status_id` int(11) NOT NULL default '0',
  `currency_id` int(11) NOT NULL default '0',
  `creation_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `users_Unique` (`name`,`email`,`password`,`mobile_number`),
  KEY `FK_currency` (`currency_id`),
  KEY `FK_country` (`country_id`),
  CONSTRAINT `FK_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FK_currency` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `homebudgetProduction`.`info_trace_log`;
CREATE TABLE  `homebudgetProduction`.`info_trace_log` (
  `id` int(11) NOT NULL auto_increment,
  `request_data` varchar(2000) NOT NULL default '',
  `response_data` varchar(500) NOT NULL default '',
  `request_date` datetime default NULL,
  `response_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `homebudgetProduction`.`category`;
CREATE TABLE  `homebudgetProduction`.`category` (
  `ID` int(11) NOT NULL auto_increment,
  `actual_value` double default NULL,
  `arabic_description` varchar(255) NOT NULL,
  `CATEGORY_STATUS` int(11) NOT NULL default '0',
  `category_type_id` int(11) NOT NULL default '0',
  `english_description` varchar(255) character set latin1 NOT NULL default '',
  `limit_value` double NOT NULL default '0',
  `PARENT_CATEGORY_ID` int(11) default NULL,
  `planed_value` double default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_user_ID` (`user_id`),
  CONSTRAINT `FK_user_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `homebudgetProduction`.`monthly_budget`;
DROP TABLE IF EXISTS `homebudgetProduction`.`category`;
CREATE TABLE  `homebudgetProduction`.`category` (
  `ID` int(11) NOT NULL auto_increment,
  `actual_value` double default NULL,
  `arabic_description` varchar(255) NOT NULL,
  `CATEGORY_STATUS` int(11) NOT NULL default '0',
  `category_type_id` int(11) NOT NULL default '0',
  `english_description` varchar(255) character set latin1 NOT NULL default '',
  `limit_value` double NOT NULL default '0',
  `PARENT_CATEGORY_ID` int(11) default NULL,
  `planed_value` double default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_user_ID` (`user_id`),
  CONSTRAINT `FK_user_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
CREATE TABLE  `homebudgetProduction`.`monthly_budget` (
  `ID` int(11) NOT NULL auto_increment,
  `start_date` datetime default NULL,
  `total_income` double default NULL,
  `total_expenses` double NOT NULL default '0',
  `user_id` int(11) default NULL,
  `end_date` datetime default NULL,
  `creation_date` datetime default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_userID` (`user_id`),
  CONSTRAINT `FK_userID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
DROP TABLE IF EXISTS `homebudgetProduction`.`monthly_budget_category`;
CREATE TABLE  `homebudgetProduction`.`monthly_budget_category` (
  `MonthlyBudget_ID` int(11) NOT NULL default '0',
  `categories_ID` int(11) NOT NULL,
  `planed_value` double default NULL,
  `actual_value` double default NULL,
  `limit_value` double default NULL,
  PRIMARY KEY  (`MonthlyBudget_ID`,`categories_ID`),
  KEY `FK_monthly_budget_CATEGORY_categories_ID` (`categories_ID`),
  CONSTRAINT `FK_monthly_budget_CATEGORY_categories_ID` FOREIGN KEY (`categories_ID`) REFERENCES `category` (`ID`),
  CONSTRAINT `FK_monthly_budget_CATEGORY_MonthlyBudget_ID` FOREIGN KEY (`MonthlyBudget_ID`) REFERENCES `monthly_budget` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `homebudgetProduction`.`purchase`;
CREATE TABLE  `homebudgetProduction`.`purchase` (
  `ID` int(11) NOT NULL auto_increment,
  `arabic_description` varchar(255) NOT NULL,
  `creation_date` datetime default NULL,
  `details` varchar(500) NOT NULL,
  `english_description` varchar(255) character set latin1 default NULL,
  `PRICE` double default NULL,
  `CATEGORY_ID` int(11) default NULL,
  `LOCATION_ID` int(11) default NULL,
  `monthlyBudget_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_PURCHASE_CATEGORY_ID` (`CATEGORY_ID`),
  KEY `FK_PURCHASE_LOCATION_ID` (`LOCATION_ID`),
  KEY `FK_Monthly_Budget` (`monthlyBudget_ID`),
  CONSTRAINT `FK_Monthly_Budget` FOREIGN KEY (`monthlyBudget_ID`) REFERENCES `monthly_budget` (`ID`),
  CONSTRAINT `FK_PURCHASE_CATEGORY_ID` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`),
  CONSTRAINT `FK_PURCHASE_LOCATION_ID` FOREIGN KEY (`LOCATION_ID`) REFERENCES `location` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `homebudgetProduction`.`purchase_history`;
CREATE TABLE  `homebudgetProduction`.`purchase_history` (
  `ID` int(11) NOT NULL auto_increment,
  `creation_date` datetime default NULL,
  `details` varchar(500) default NULL,
  `PRICE` double default NULL,
  `Purchase_ID` int(11) NOT NULL default '0',
  `Location_id` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_Purchase_ID_history` (`Purchase_ID`),
  KEY `FK_location_history_2` (`Location_id`),
  CONSTRAINT `FK_location_history_2` FOREIGN KEY (`Location_id`) REFERENCES `location` (`ID`),
  CONSTRAINT `FK_PurchaseID_history` FOREIGN KEY (`Purchase_ID`) REFERENCES `purchase` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `homebudgetProduction`.`category_history`;
CREATE TABLE  `homebudgetProduction`.`category_history` (
  `ID` int(11) NOT NULL auto_increment,
  `actual_value` double default NULL,
  `CATEGORY_STATUS` int(11) NOT NULL default '0',
  `category_type_id` int(11) NOT NULL default '0',
  `limit_value` double NOT NULL default '0',
  `PARENT_CATEGORY_ID` int(11) default NULL,
  `planed_value` double default NULL,
  `user_id` int(11) default NULL,
  `category_id` int(11) default NULL,
  `creation_date` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_user_ID_history` (`user_id`),
  KEY `FK_category_ID_history` (`category_id`),
  CONSTRAINT `FK_category_ID_history` FOREIGN KEY (`category_id`) REFERENCES `category` (`ID`),
  CONSTRAINT `FK_user_ID_history` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

insert into `homebudgetProduction`.`country`(`arabic_name` ,`english_name`)values('Egypt','Egypt');
insert into `homebudgetProduction`.`currency`(`name` )values('EGP');