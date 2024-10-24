-- Fitness Journal Database schema

create table if not exists 'workout'(
    id  bigint auto_increment,
    set int default 0,
    reps int defualt 0,
    weight float default 0.0,
    workoutRecordId bigint not null,
    primary key(id),
    foreign key (workoutRecordId) references workoutRecord(workoutRecordId)
);

create table if not exists 'workoutRecord'(
    workoutRecordId bigInt auto_increment,
    workoutName var(255) defualt '',
    workoutDate datetime  default getDate(),
    primary key(workoutRecordId),
    -- decided not add foreign key to test if its needed to make the connection
);

