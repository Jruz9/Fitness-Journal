create table if not exists workout(
    id  bigint auto_increment,
    session int default 0,
    reps int default 0,
    weight float default 0.0,
    workoutrecordid bigint not null,
    primary key(id),
    foreign key (workoutrecordId) references workoutrecord(workoutrecordid)
);

create table if not exists workoutrecord(
    workoutrecordid bigInt auto_increment,
    workoutname var(255) default '',
    workoutdate datetime default getDate(),
    primary key(workoutRecordId),
    -- decided not add foreign key to test if its needed to make the connection
);
