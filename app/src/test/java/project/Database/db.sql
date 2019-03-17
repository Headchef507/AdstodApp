/*
 * Creating the Database for use in the project adstod
 * For Group 5
 */
DROP DATABASE IF EXISTS adstodQuestions;  # Drop database first if it exists to prevent conflicts
CREATE DATABASE adstodQuestions;

USE adstodQuestions;                      # Make sure to indicate what database to use

CREATE TABLE QuestionsICE(                   # Table holding all questions in icelandic
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  QuestionText VARCHAR(255) NOT NULL,
  OptionCount INT(2) NOT NULL
);

CREATE TABLE QuestionsENG(                   # Table holding all questions in english
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  QuestionText VARCHAR(255) NOT NULL,
  OptionCount INT(2) NOT NULL
);

CREATE TABLE QuestionsPOL(                   # Table holding all questions in polish
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  QuestionText VARCHAR(255) NOT NULL,
  OptionCount INT(2) NOT NULL
);

CREATE TABLE OptionsICE(                     # Table holding all possible answers to questions
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  OptionText VARCHAR(255) NOT NULL
);

CREATE TABLE OptionsENG(                     # Table holding all possible answers to questions
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  OptionText VARCHAR(255) NOT NULL
);

CREATE TABLE OptionsPOL(                     # Table holding all possible answers to questions
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  OptionText VARCHAR(255) NOT NULL
);

CREATE TABLE PhoneNumbers(                # Table holding phone numbers for all assistance resources
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  Number VARCHAR(100) NOT NULL
);

CREATE TABLE AssistanceResourcesICE(         # Table holding names and links of all assistance resources that can pop up in results - Icelandic
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  Title VARCHAR(255) NOT NULL,
  Link TEXT NOT NULL,
  Description TEXT NOT NULL,
  PhoneNumberCount INT NOT NULL
);

CREATE TABLE AssistanceResourcesENG(         # Table holding names and links of all assistance resources that can pop up in results - English
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  Title VARCHAR(255) NOT NULL,
  Link TEXT NOT NULL,
  Description TEXT NOT NULL,
  PhoneNumberCount INT NOT NULL
);

CREATE TABLE AssistanceResourcesPOL(         # Table holding names and links of all assistance resources that can pop up in results - Polish
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  Title VARCHAR(255) NOT NULL,
  Link TEXT NOT NULL,
  Description TEXT NOT NULL,
  PhoneNumberCount INT NOT NULL
);

/**
 * This table connects the PhoneNumbers table to the AssistanceResources tables
 * Each assistance resource can have many phone numbers
 * Each phone number can belong to multiple assistance resources
 * This makes the connection table useful for reducing redundant data
 */
CREATE TABLE PhoneNumbersForResourcesICE(
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  PhoneNumberID BIGINT(20) NOT NULL,
  AssistanceResourceID BIGINT(20) NOT NULL,
  CONSTRAINT FOREIGN KEY (PhoneNumberID) REFERENCES PhoneNumbers(ID),
  CONSTRAINT FOREIGN KEY (AssistanceResourceID) REFERENCES AssistanceResourcesICE(ID)
);

CREATE TABLE PhoneNumbersForResourcesENG(
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  PhoneNumberID BIGINT(20) NOT NULL,
  AssistanceResourceID BIGINT(20) NOT NULL,
  CONSTRAINT FOREIGN KEY (PhoneNumberID) REFERENCES PhoneNumbers(ID),
  CONSTRAINT FOREIGN KEY (AssistanceResourceID) REFERENCES AssistanceResourcesENG(ID)
);

CREATE TABLE PhoneNumbersForResourcesPOL(
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  PhoneNumberID BIGINT(20) NOT NULL,
  AssistanceResourceID BIGINT(20) NOT NULL,
  CONSTRAINT FOREIGN KEY (PhoneNumberID) REFERENCES PhoneNumbers(ID),
  CONSTRAINT FOREIGN KEY (AssistanceResourceID) REFERENCES AssistanceResourcesPOL(ID)
);

/*
 * These tables connect the Options tables to the Questions tables
 * Each questionText has multiple options
 * Each option can be used in multiple questions
 * This makes the connection tables useful for reducing redundant data
 */
CREATE TABLE OptionsForAnswersICE(
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  QuestionID BIGINT(20) NOT NULL,
  OptionID BIGINT(20) NOT NULL,
  NextQuestionID BIGINT(20),
  CONSTRAINT FOREIGN KEY (QuestionID) REFERENCES QuestionsICE(ID),
  CONSTRAINT FOREIGN KEY (OptionID) REFERENCES OptionsICE(ID),
  CONSTRAINT FOREIGN KEY (NextQuestionID) REFERENCES QuestionsICE(ID)
);

CREATE TABLE OptionsForAnswersENG(
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  QuestionID BIGINT(20) NOT NULL,
  OptionID BIGINT(20) NOT NULL,
  NextQuestionID BIGINT(20),
  CONSTRAINT FOREIGN KEY (QuestionID) REFERENCES QuestionsENG(ID),
  CONSTRAINT FOREIGN KEY (OptionID) REFERENCES OptionsENG(ID),
  CONSTRAINT FOREIGN KEY (NextQuestionID) REFERENCES QuestionsENG(ID)
);

CREATE TABLE OptionsForAnswersPOL(
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  QuestionID BIGINT(20) NOT NULL,
  OptionID BIGINT(20) NOT NULL,
  NextQuestionID BIGINT(20),
  CONSTRAINT FOREIGN KEY (QuestionID) REFERENCES QuestionsPOL(ID),
  CONSTRAINT FOREIGN KEY (OptionID) REFERENCES OptionsPOL(ID),
  CONSTRAINT FOREIGN KEY (NextQuestionID) REFERENCES QuestionsPOL(ID)
);

-- Inserts
-- Inserting questions in icelandic
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Kyn?", 3);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Aldursbil?", 5);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hjúskaparstaða?", 3);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hefurðu neytt áfengis?", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Í síðast liðnum mánuði, hversu oft hefurðu neytt áfengis?", 5);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hefur þú orðið fyrir andlegu eða líkamlegu ofbeldi af hálfu núverandi maka?", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hefur þú orðið fyrir andlegu eða líkamlegu ofbeldi af hálfu samstarfsaðila á síðast liðnum 2 mánuðum? \n (til dæmis einelti á vinnustað, hópþrýstingur í eitthvað sem þú sást eftir o.s.frv)", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hefur þú orðið fyrir alvarlegu andlegu eða líkamlegu ofbeldu af hálfu foreldra, systkyna eða annarra fjölskyldu meðlima á síðustu 2 mánuðum?", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hefur þú neytt vímuefna (heróin eða stek verkjalyf, kannabis efni, amfetamín, alsæla, kókaín, sveppir eða sambærileg efni) á síðustu 2 mánuðum?", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hversu oft hefur þú neytt vímuefna á síðastliðnum mánuði?", 6);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Finnst þér þú finna fyrir mismunun í samfélaginu vegna uppruna þíns?", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hefur einhver náin þér dáið á síðastliðnum 2 mánuðum?", 2);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hversu vel á eftirfarandi fullyrðing við um þig: \n -'Ég hef fundið fyrir depurð á síðustu 2 mánuðum'?", 5);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hversu vel á eftirfarandi fullyrðing við um þig: \n -'Ég hef hugsað um líflát á síðustu 2 mánuðum'?", 5);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hversu vel á eftirfarandi fullyrðing við um þig: \n -'Ég hef fundið fyrir einmanaleika á síðustu 2 mánuðum'?", 5);
INSERT INTO QuestionsICE (QuestionText, OptionCount) VALUES ("Hversu vel á eftirfarandi fullyrðing við um þig: \n -'Ég hef fundið fyrir óánægju í einu eða fleirum af mínum samböndum á síðustu 2 mánuðum'?", 5);

-- Inserting questions in english
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Gender?", 3);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Age range?", 5);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Relationship Status?", 3);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Have you consumed alcohol?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("How often have you consumed alcohol in the last month?", 5);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Have you been subjected to mental or physical abuse by your current spouse?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Have you been subjected to mental or physical abuse by a coworker/s in the last 2 months?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Have you been subjected to severe mental or physical abuse by a parent, sibling or other family member in the last 2 months?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Have you used drugs (heroin or strong pain meidcation, kannabis, amfetamin, cocaine, mushrooms or other comparable substances) in the last 2 months?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("How often have you used drugs in the last month", 6);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Do you feel oppressed in the community due to your origin?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("Has anyone close to you died in the last 2 months?", 2);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("How well does the following statement apply to you: 'I have felt sad in the last 2 months'?", 5);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("How well does the following statement apply to you: 'I have considered suicide in the last 2 months'?", 5);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("How well does the following statement apply to you: 'I have felt lonely in the last 2 months'?", 5);
INSERT INTO QuestionsENG (QuestionText, OptionCount) VALUES ("How well does the following statement apply to you: 'I have felt unhappy in one or more of my relationships in the last 2 months'?", 5);

-- Inserting questions in polish
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER1?", 3); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER2?", 5); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER3?", 3); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER4?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER5?", 5); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER6?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER7?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER8?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER9?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER10?", 6); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER11?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER12?", 2); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER13?", 5); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER14?", 5); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER15?", 5); -- Polish translation needed
INSERT INTO QuestionsPOL (QuestionText, OptionCount) VALUES ("PLACEHOLDER16?", 5); -- Polish translation needed

-- Inserting all options for all questions in icelandic
INSERT INTO OptionsICE (OptionText) VALUES ("Karlkyns");
INSERT INTO OptionsICE (OptionText) VALUES ("Kvenkyns");
INSERT INTO OptionsICE (OptionText) VALUES ("Annað");
INSERT INTO OptionsICE (OptionText) VALUES ("< 18");
INSERT INTO OptionsICE (OptionText) VALUES ("18 - 35");
INSERT INTO OptionsICE (OptionText) VALUES ("35 - 50");
INSERT INTO OptionsICE (OptionText) VALUES ("50 - 67");
INSERT INTO OptionsICE (OptionText) VALUES ("> 67");
INSERT INTO OptionsICE (OptionText) VALUES ("Einhleypur");
INSERT INTO OptionsICE (OptionText) VALUES ("Sambúð");
INSERT INTO OptionsICE (OptionText) VALUES ("Kvænt/ur");
INSERT INTO OptionsICE (OptionText) VALUES ("Já");
INSERT INTO OptionsICE (OptionText) VALUES ("Nei");
INSERT INTO OptionsICE (OptionText) VALUES ("Ekkert");
INSERT INTO OptionsICE (OptionText) VALUES ("Einu sinni til tvisvar");
INSERT INTO OptionsICE (OptionText) VALUES ("Þrisvar til fjórum sinnum");
INSERT INTO OptionsICE (OptionText) VALUES ("Fjórum til sjö sinnum");
INSERT INTO OptionsICE (OptionText) VALUES ("Oftar en átta sinnum");
INSERT INTO OptionsICE (OptionText) VALUES ("Einu sinni til þrisvar á mánuði");
INSERT INTO OptionsICE (OptionText) VALUES ("Einu sinni á tveggja vikna fresti");
INSERT INTO OptionsICE (OptionText) VALUES ("Einu sinni til þrisvar í viku");
INSERT INTO OptionsICE (OptionText) VALUES ("Næstum alla daga");
INSERT INTO OptionsICE (OptionText) VALUES ("Alla daga");
INSERT INTO OptionsICE (OptionText) VALUES ("Mjög sammála");
INSERT INTO OptionsICE (OptionText) VALUES ("Frekar sammála");
INSERT INTO OptionsICE (OptionText) VALUES ("Hvorki né");
INSERT INTO OptionsICE (OptionText) VALUES ("Frekar ósammála");
INSERT INTO OptionsICE (OptionText) VALUES ("Mjög ósammála");

-- Inserting all options for all questions in english
INSERT INTO OptionsENG (OptionText) VALUES ("Male");
INSERT INTO OptionsENG (OptionText) VALUES ("Female");
INSERT INTO OptionsENG (OptionText) VALUES ("Other");
INSERT INTO OptionsENG (OptionText) VALUES ("< 18");
INSERT INTO OptionsENG (OptionText) VALUES ("18 - 35");
INSERT INTO OptionsENG (OptionText) VALUES ("35 - 50");
INSERT INTO OptionsENG (OptionText) VALUES ("50 - 67");
INSERT INTO OptionsENG (OptionText) VALUES ("> 67");
INSERT INTO OptionsENG (OptionText) VALUES ("Single");
INSERT INTO OptionsENG (OptionText) VALUES ("Living together");
INSERT INTO OptionsENG (OptionText) VALUES ("Married");
INSERT INTO OptionsENG (OptionText) VALUES ("Yes");
INSERT INTO OptionsENG (OptionText) VALUES ("No");
INSERT INTO OptionsENG (OptionText) VALUES ("Never");
INSERT INTO OptionsENG (OptionText) VALUES ("Once or twice");
INSERT INTO OptionsENG (OptionText) VALUES ("Three or four times");
INSERT INTO OptionsENG (OptionText) VALUES ("Four to seven times");
INSERT INTO OptionsENG (OptionText) VALUES ("More than eight times");
INSERT INTO OptionsENG (OptionText) VALUES ("Once to three times a month");
INSERT INTO OptionsENG (OptionText) VALUES ("Once every two weeks");
INSERT INTO OptionsENG (OptionText) VALUES ("Once to three times a week");
INSERT INTO OptionsENG (OptionText) VALUES ("Almost every day");
INSERT INTO OptionsENG (OptionText) VALUES ("Every day");
INSERT INTO OptionsENG (OptionText) VALUES ("Highly agree");
INSERT INTO OptionsENG (OptionText) VALUES ("Somewhat agree");
INSERT INTO OptionsENG (OptionText) VALUES ("Impartial");
INSERT INTO OptionsENG (OptionText) VALUES ("Somewhat disagree");
INSERT INTO OptionsENG (OptionText) VALUES ("Highly disagree");

-- Inserting all options for all questions in polish
-- Translation needed
INSERT INTO OptionsPOL (OptionText) VALUES ("PHMale");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHFemale");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHOther");
INSERT INTO OptionsPOL (OptionText) VALUES ("< 18");
INSERT INTO OptionsPOL (OptionText) VALUES ("18 - 35");
INSERT INTO OptionsPOL (OptionText) VALUES ("35 - 50");
INSERT INTO OptionsPOL (OptionText) VALUES ("50 - 67");
INSERT INTO OptionsPOL (OptionText) VALUES ("> 67");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHEinhleypur");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHSambúð");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHKvænt/ur");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHJá");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHNei");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHEkkert");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHEinu sinni til tvisvar");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHÞrisvar til fjórum sinnum");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHFjórum til sjö sinnum");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHOftar en átta sinnum");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHEinu sinni til þrisvar á mánuði");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHEinu sinni á tveggja vikna fresti");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHEinu sinni til þrisvar í viku");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHNæstum alla daga");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHAlla daga");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHMjög sammála");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHFrekar sammála");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHHvorki né");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHFrekar ósammála");
INSERT INTO OptionsPOL (OptionText) VALUES ("PHMjög ósammála");

-- Connecting the questions to options and the next questionText in line - Icelandic
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (1, 1, 2);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (1, 2, 2);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (1, 3, 2);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (2, 4, 3);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (2, 5, 3);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (2, 6, 3);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (2, 7, 3);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (2, 8, 3);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (3, 9, 4);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (3, 10, 4);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (3, 11, 4);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (4, 12, 5);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (4, 13, 6);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (5, 14, 6);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (5, 15, 6);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (5, 16, 6);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (5, 17, 6);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (5, 18, 6);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (6, 12, 7);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (6, 13, 7);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (7, 12, 8);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (7, 13, 8);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (8, 12, 9);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (8, 13, 9);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (9, 12, 10);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (9, 13, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (10, 14, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (10, 19, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (10, 20, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (10, 21, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (10, 22, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (10, 23, 11);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (11, 12, 12);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (11, 13, 12);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (12, 12, 13);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (12, 13, 13);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (13, 24, 14);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (13, 25, 14);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (13, 26, 14);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (13, 27, 14);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (13, 28, 14);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (14, 24, 15);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (14, 25, 15);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (14, 26, 15);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (14, 27, 15);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (14, 28, 15);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (15, 24, 16);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (15, 25, 16);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (15, 26, 16);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (15, 27, 16);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID, NextQuestionID) VALUES (15, 28, 16);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID) VALUES (16, 24);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID) VALUES (16, 25);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID) VALUES (16, 26);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID) VALUES (16, 27);
INSERT INTO OptionsForAnswersICE (QuestionID, OptionID) VALUES (16, 28);

-- Connecting the questions to options and the next questionText in line - English
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (1, 1, 2);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (1, 2, 2);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (1, 3, 2);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (2, 4, 3);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (2, 5, 3);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (2, 6, 3);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (2, 7, 3);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (2, 8, 3);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (3, 9, 4);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (3, 10, 4);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (3, 11, 4);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (4, 12, 5);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (4, 13, 6);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (5, 14, 6);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (5, 15, 6);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (5, 16, 6);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (5, 17, 6);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (5, 18, 6);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (6, 12, 7);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (6, 13, 7);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (7, 12, 1);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (7, 13, 1);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (8, 12, 9);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (8, 13, 9);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (9, 12, 10);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (9, 13, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (10, 14, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (10, 19, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (10, 20, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (10, 21, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (10, 22, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (10, 23, 11);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (11, 12, 12);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (11, 13, 12);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (12, 12, 13);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (12, 13, 13);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (13, 24, 14);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (13, 25, 14);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (13, 26, 14);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (13, 27, 14);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (13, 28, 14);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (14, 24, 15);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (14, 25, 15);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (14, 26, 15);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (14, 27, 15);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (14, 28, 15);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (15, 24, 16);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (15, 25, 16);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (15, 26, 16);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (15, 27, 16);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID, NextQuestionID) VALUES (15, 28, 16);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID) VALUES (16, 24);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID) VALUES (16, 25);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID) VALUES (16, 26);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID) VALUES (16, 27);
INSERT INTO OptionsForAnswersENG (QuestionID, OptionID) VALUES (16, 28);

-- Connecting the questions to options and the next questionText in line - Polish
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (1, 1, 2);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (1, 2, 2);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (1, 3, 2);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (2, 4, 3);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (2, 5, 3);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (2, 6, 3);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (2, 7, 3);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (2, 8, 3);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (3, 9, 4);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (3, 10, 4);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (3, 11, 4);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (4, 12, 5);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (4, 13, 6);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (5, 14, 6);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (5, 15, 6);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (5, 16, 6);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (5, 17, 6);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (5, 18, 6);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (6, 12, 7);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (6, 13, 7);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (7, 12, 1);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (7, 13, 1);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (8, 12, 9);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (8, 13, 9);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (9, 12, 10);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (9, 13, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (10, 14, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (10, 19, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (10, 20, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (10, 21, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (10, 22, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (10, 23, 11);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (11, 12, 12);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (11, 13, 12);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (12, 12, 13);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (12, 13, 13);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (13, 24, 14);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (13, 25, 14);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (13, 26, 14);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (13, 27, 14);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (13, 28, 14);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (14, 24, 15);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (14, 25, 15);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (14, 26, 15);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (14, 27, 15);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (14, 28, 15);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (15, 24, 16);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (15, 25, 16);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (15, 26, 16);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (15, 27, 16);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID, NextQuestionID) VALUES (15, 28, 16);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID) VALUES (16, 24);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID) VALUES (16, 25);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID) VALUES (16, 26);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID) VALUES (16, 27);
INSERT INTO OptionsForAnswersPOL (QuestionID, OptionID) VALUES (16, 28);

-- Inserting assistance resources in icelandic
INSERT INTO AssistanceResourcesICE(Title, Link, Description, PhoneNumberCount) VALUES ("Kvennaathvarf", "https://www.kvennaathvarf.is", "Stofnfundur Samtaka um kvennaathvarf var haldinn 2. júní 1982. Þar var ákveðið að opna athvarf fyrir konur sem ekki gætu búið heima hjá sér vegna ofbeldis og 6. desember sama ár var Kvennaathvarfið opnað. Samtök um kvennaathvarf voru í upphafi grasrótarsamtök en árið 1995 var horfið frá því fyrirkomulagi og mynduð formleg samtök. Árið 2010 var stofnuð sjálfseignarstofnun um húseign Kvennaathvarfsins en rekstur athvarfsins hélst óbreyttur.", 1);
INSERT INTO AssistanceResourcesICE(Title, Link, Description, PhoneNumberCount) VALUES ("Stígamót", "https://www.stigamot.is/is/um-stigamot", "Meginmarkmiðin með stofnun Stígamóta eru annars vegar að þau séu staður, sem konur og karlar, sem beitt hafa verið kynferðisofbeldi, geti leitað til, fengið stuðning og deilt reynslu sinni með öðrum, sem einnig hafa verið beittir slíku ofbeldi eða þekkja það vel. Með kynferðisofbeldi er, auk sifjaspella, nauðgana og kynferðislegrar áreitni, einnig átt við klám, vændi og mansal enda er það reynsla Stígamóta jafnt og annarra kvennasamtaka í heiminum að það tekur langan tíma að vinna úr þeirri erfiðu ofbeldisreynslu sem vændi er og að klám er ein birtingarmynd vændis. Mansal er nútíma þrælasala, og algengasta form mansals er mansal til kynlífsþjónustu. ", 2);
INSERT INTO AssistanceResourcesICE(Title, Link, Description, PhoneNumberCount) VALUES ("Frú Ragnheiður", "https://www.raudikrossinn.is/hvad-gerum-vid/fru-ragnheidur/", "Frú Ragnheiður – skaðaminnkun  er verkefni sem hefur það markmið að ná til jaðarsetta hópa í samfélaginu eins og heimilislausra einstaklinga og einstaklinga sem nota vímuefni um æð. Frú Ragnheiður er sérinnréttaður bíll sem er ekið um götur höfuðborgarsvæðisins á kvöldin, sex kvöld í viku.", 1);
INSERT INTO AssistanceResourcesICE(Title, Link, Description, PhoneNumberCount) VALUES ("Heimsóknarvinir", "https://www.raudikrossinn.is/hvad-gerum-vid/adstod-i-bodi/heimsoknarvinur/", "Heimsóknavinir eru sjálfboðaliðar sem heimsækja fólk á heimili þess, á stofnanir, sambýli og dvalar- og hjúkrunarheimili. Misjafnt er hvað felst í heimsókn en það getur t.d. verið spjall, gönguferð, ökuferð, upplestur, aðstoð við handavinnu og svo framvegis.  Beiðnir eru breytilegar, en reynt er að mæta þörfum og óskum gestgjafanna  eins og kostur er. Viðmið er að heimsóknartími sé um klukkustund einu sinni í viku. Hvenær og hvar heimsóknin á sér stað er samkomulagsatriði hjá gestgjafa og heimsóknavini.", 1);
INSERT INTO AssistanceResourcesICE(Title, Link, Description, PhoneNumberCount) VALUES ("Heilahristingur", "https://www.raudikrossinn.is/hvad-gerum-vid/heilahristingur/", "Heilahristingur er heimanámsaðstoð fyrir grunnskólanema frá 4.-10 bekk sem og framhaldsskólanema á höfuðborgarsvæðinu. Áhersla er lögð á að virkja nemendur í námi og hafa það skemmtilegt saman. Markmiðið er að styðja og styrkja nemendur í námi en samhliða því fái þeir tækifæri til að kynnast þeirri þjónustu sem bókasöfn bjóða upp á í tengslum við nám, áhugamál, tómstundir o.fl.", 1);
INSERT INTO AssistanceResourcesICE(Title, Link, Description, PhoneNumberCount) VALUES ("Engin niðurstaða", "https://reykjavik.is/stadir/thjonustumidstod-laugardals-og-haaleitis", "Því miður þá höfum við ekki til staðar þjónustu sem hentar þínum valkostum til þessa. Hins vegar, ef þér finnst að eitthvað sé að valda þér áhyggjum eða ert í vanda. Vinsamlegast hafðu samband við þjónustu miðstöð í þínu hverfi og biddu um ráðgjöf.", 1);

-- Inserting assistance resources in english
INSERT INTO AssistanceResourcesENG(Title, Link, Description, PhoneNumberCount) VALUES ("The Women's Shelter", "https://www.kvennaathvarf.is", "The Women’s Shelter was established on June 2nd 1982. It was decided on the inaugural meeting to open a shelter for women who were not able to stay in their own homes due to violence. On December the 6th on the same year the Women’s Shelter was opened.", 1);
INSERT INTO AssistanceResourcesENG(Title, Link, Description, PhoneNumberCount) VALUES ("Stígamót", "https://www.stigamot.is/is/um-stigamot", "Free individual counseling for survivors of rape, sexual molestation, sexual harassment, pornographic exploitation and prostitution. Service is for women and men. Services for children are offered by Children’s Protective Services (Barnahús).Teenagers under the age of 18 are welcome once a report has been filed with the Children’s Protective Services. In the event a report has not been filed, please be aware that according to Icelandic law we must report any instances of sexual abuse of a minor, suspected or otherwise. Questions can be sent by email or information requested by phone.", 2);
INSERT INTO AssistanceResourcesENG(Title, Link, Description, PhoneNumberCount) VALUES ("Frú Ragnheiður", "https://www.raudikrossinn.is/hvad-gerum-vid/fru-ragnheidur/", "Frú Ragnheiður – skaðaminnkun  er verkefni sem hefur það markmið að ná til jaðarsetta hópa í samfélaginu eins og heimilislausra einstaklinga og einstaklinga sem nota vímuefni um æð. Frú Ragnheiður er sérinnréttaður bíll sem er ekið um götur höfuðborgarsvæðisins á kvöldin, sex kvöld í viku.", 1);
INSERT INTO AssistanceResourcesENG(Title, Link, Description, PhoneNumberCount) VALUES ("Heimsóknarvinir", "https://www.raudikrossinn.is/hvad-gerum-vid/adstod-i-bodi/heimsoknarvinur/", "Heimsóknavinir eru sjálfboðaliðar sem heimsækja fólk á heimili þess, á stofnanir, sambýli og dvalar- og hjúkrunarheimili. Misjafnt er hvað felst í heimsókn en það getur t.d. verið spjall, gönguferð, ökuferð, upplestur, aðstoð við handavinnu og svo framvegis.  Beiðnir eru breytilegar, en reynt er að mæta þörfum og óskum gestgjafanna  eins og kostur er. Viðmið er að heimsóknartími sé um klukkustund einu sinni í viku. Hvenær og hvar heimsóknin á sér stað er samkomulagsatriði hjá gestgjafa og heimsóknavini.", 1);
INSERT INTO AssistanceResourcesENG(Title, Link, Description, PhoneNumberCount) VALUES ("Heilahristingur", "https://www.raudikrossinn.is/hvad-gerum-vid/heilahristingur/", "Heilahristingur er heimanámsaðstoð fyrir grunnskólanema frá 4.-10 bekk sem og framhaldsskólanema á höfuðborgarsvæðinu. Áhersla er lögð á að virkja nemendur í námi og hafa það skemmtilegt saman. Markmiðið er að styðja og styrkja nemendur í námi en samhliða því fái þeir tækifæri til að kynnast þeirri þjónustu sem bókasöfn bjóða upp á í tengslum við nám, áhugamál, tómstundir o.fl.", 1);
INSERT INTO AssistanceResourcesENG(Title, Link, Description, PhoneNumberCount) VALUES ("No Result", "https://reykjavik.is/stadir/thjonustumidstod-laugardals-og-haaleitis", "Því miður þá höfum við ekki til staðar þjónustu sem hentar þínum valkostum til þessa. Hins vegar, ef þér finnst að eitthvað sé að valda þér áhyggjum eða ert í vanda. Vinsamlegast hafðu samband við þjónustu miðstöð í þínu hverfi og biddu um ráðgjöf.", 1);

-- Inserting assistance resources in polish
INSERT INTO AssistanceResourcesPOL(Title, Link, Description, PhoneNumberCount) VALUES ("PHKvennaathvarf", "https://www.kvennaathvarf.is", "PHStofnfundur Samtaka um kvennaathvarf var haldinn 2. júní 1982. Þar var ákveðið að opna athvarf fyrir konur sem ekki gætu búið heima hjá sér vegna ofbeldis og 6. desember sama ár var Kvennaathvarfið opnað. Samtök um kvennaathvarf voru í upphafi grasrótarsamtök en árið 1995 var horfið frá því fyrirkomulagi og mynduð formleg samtök. Árið 2010 var stofnuð sjálfseignarstofnun um húseign Kvennaathvarfsins en rekstur athvarfsins hélst óbreyttur.", 1);
INSERT INTO AssistanceResourcesPOL(Title, Link, Description, PhoneNumberCount) VALUES ("PHStígamót", "https://www.stigamot.is/is/um-stigamot", "PHMeginmarkmiðin með stofnun Stígamóta eru annars vegar að þau séu staður, sem konur og karlar, sem beitt hafa verið kynferðisofbeldi, geti leitað til, fengið stuðning og deilt reynslu sinni með öðrum, sem einnig hafa verið beittir slíku ofbeldi eða þekkja það vel. Með kynferðisofbeldi er, auk sifjaspella, nauðgana og kynferðislegrar áreitni, einnig átt við klám, vændi og mansal enda er það reynsla Stígamóta jafnt og annarra kvennasamtaka í heiminum að það tekur langan tíma að vinna úr þeirri erfiðu ofbeldisreynslu sem vændi er og að klám er ein birtingarmynd vændis. Mansal er nútíma þrælasala, og algengasta form mansals er mansal til kynlífsþjónustu. ", 2);
INSERT INTO AssistanceResourcesPOL(Title, Link, Description, PhoneNumberCount) VALUES ("PHFrú Ragnheiður", "https://www.raudikrossinn.is/hvad-gerum-vid/fru-ragnheidur/", "PHFrú Ragnheiður – skaðaminnkun  er verkefni sem hefur það markmið að ná til jaðarsetta hópa í samfélaginu eins og heimilislausra einstaklinga og einstaklinga sem nota vímuefni um æð. Frú Ragnheiður er sérinnréttaður bíll sem er ekið um götur höfuðborgarsvæðisins á kvöldin, sex kvöld í viku.", 1);
INSERT INTO AssistanceResourcesPOL(Title, Link, Description, PhoneNumberCount) VALUES ("PHHeimsóknarvinir", "https://www.raudikrossinn.is/hvad-gerum-vid/adstod-i-bodi/heimsoknarvinur/", "PHHeimsóknavinir eru sjálfboðaliðar sem heimsækja fólk á heimili þess, á stofnanir, sambýli og dvalar- og hjúkrunarheimili. Misjafnt er hvað felst í heimsókn en það getur t.d. verið spjall, gönguferð, ökuferð, upplestur, aðstoð við handavinnu og svo framvegis.  Beiðnir eru breytilegar, en reynt er að mæta þörfum og óskum gestgjafanna  eins og kostur er. Viðmið er að heimsóknartími sé um klukkustund einu sinni í viku. Hvenær og hvar heimsóknin á sér stað er samkomulagsatriði hjá gestgjafa og heimsóknavini.", 1);
INSERT INTO AssistanceResourcesPOL(Title, Link, Description, PhoneNumberCount) VALUES ("PHHeilahristingur", "https://www.raudikrossinn.is/hvad-gerum-vid/heilahristingur/", "PHHeilahristingur er heimanámsaðstoð fyrir grunnskólanema frá 4.-10 bekk sem og framhaldsskólanema á höfuðborgarsvæðinu. Áhersla er lögð á að virkja nemendur í námi og hafa það skemmtilegt saman. Markmiðið er að styðja og styrkja nemendur í námi en samhliða því fái þeir tækifæri til að kynnast þeirri þjónustu sem bókasöfn bjóða upp á í tengslum við nám, áhugamál, tómstundir o.fl.", 1);
INSERT INTO AssistanceResourcesPOL(Title, Link, Description, PhoneNumberCount) VALUES ("PHEngin niðurstaða", "https://reykjavik.is/stadir/thjonustumidstod-laugardals-og-haaleitis", "PHÞví miður þá höfum við ekki til staðar þjónustu sem hentar þínum valkostum til þessa. Hins vegar, ef þér finnst að eitthvað sé að valda þér áhyggjum eða ert í vanda. Vinsamlegast hafðu samband við þjónustu miðstöð í þínu hverfi og biddu um ráðgjöf.", 1);

-- Inserting phone numbers for assistance resources
INSERT INTO PhoneNumbers(Number) VALUES ("5611205");
INSERT INTO PhoneNumbers(Number) VALUES ("5626868");
INSERT INTO PhoneNumbers(Number) VALUES ("8006868");
INSERT INTO PhoneNumbers(Number) VALUES ("7887123");
INSERT INTO PhoneNumbers(Number) VALUES ("Panta símatíma á vefsíðunni");
INSERT INTO PhoneNumbers(Number) VALUES ("5704000");
INSERT INTO PhoneNumbers(Number) VALUES ("4111500");

-- Connecting phone numbers to assistance resources
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (1, 1);
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (2, 2);
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (2, 3);
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (3, 4);
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (4, 5);
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (5, 6);
INSERT INTO PhoneNumbersForResourcesICE(AssistanceResourceID, PhoneNumberID) VALUES (6, 7);

INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (1, 1);
INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (2, 2);
INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (2, 3);
INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (3, 4);
INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (4, 5);
INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (5, 6);
INSERT INTO PhoneNumbersForResourcesENG(AssistanceResourceID, PhoneNumberID) VALUES (6, 7);

INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (1, 1);
INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (2, 2);
INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (2, 3);
INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (3, 4);
INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (4, 5);
INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (5, 6);
INSERT INTO PhoneNumbersForResourcesPOL(AssistanceResourceID, PhoneNumberID) VALUES (6, 7);
