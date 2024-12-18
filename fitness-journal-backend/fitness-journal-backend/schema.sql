-- CREATE SCHEMA `fitness_journal` ;
-- Order Matter for which tables first.:
CREATE TABLE IF NOT EXISTS workoutrecord (
    workoutrecordid BIGINT AUTO_INCREMENT,
    workoutname VARCHAR(255) DEFAULT '',
    workoutdate DATETIME DEFAULT NOW(),
    PRIMARY KEY (workoutRecordId)
);

CREATE TABLE IF NOT EXISTS workout (
    id BIGINT AUTO_INCREMENT,
    sessions INT DEFAULT 0,
    reps INT DEFAULT 0,
    weight FLOAT DEFAULT 0.0,
    duration FLOAT DEFAULT 0.0,
    createdworkoutdate DATETIME,
    workoutrecordid BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (workoutrecordId)
        REFERENCES workoutrecord (workoutrecordid)
);