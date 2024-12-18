-- CREATE SCHEMA `fitness_journal` ;
-- Order Matter for which tables first.:
create table if not exists workoutrecord(
    workoutrecordid bigInt auto_increment,
    workoutname varchar(255) default '',
    workoutdate datetime default Now(),
    primary key(workoutRecordId)
);

CREATE TABLE IF NOT EXISTS workout (
    id BIGINT AUTO_INCREMENT,
    sessions INT DEFAULT 0,
    reps INT DEFAULT 0,
    weight FLOAT DEFAULT 0.0,
    workoutrecordid BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (workoutrecordId)
        REFERENCES workoutrecord (workoutrecordid)
);