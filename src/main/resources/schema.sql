DROP TABLE IF EXISTS task;

CREATE TABLE task(
	id INT AUTO_INCREMENT PRIMARY KEY,
	taskDescription VARCHAR(250) NOT NULL,
	taskState VARCHAR(50) NOT NULL
);