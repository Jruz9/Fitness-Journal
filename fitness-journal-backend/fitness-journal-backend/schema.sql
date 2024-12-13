create table if not exists workout(
    id  bigserial,
    sessions int default 0,
    reps int default 0,
    weight float default 0.0,
    workoutRecordId bigint not null,
    primary key(id),
    foreign key (workoutRecordId) references workoutRecord(workoutRecordId)
);

create table if not exists workoutRecord(
    workoutRecordId bigserial,
    workoutName var(255) default '',
    workoutDate datetime  default getDate(),
    primary key(workoutRecordId)
);
