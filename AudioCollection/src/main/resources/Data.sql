create database Data;
create table userDetails(
    id varchar(20) primary key unique not null,
    password varchar(20) not null,
    registrationDate date not null,
    userType varchar(20) not null
);

create table userSetting(
    id varchar(20) references userDetails(id) not null unique,
    font varchar(20) not null,
    themeType varchar(20) not null
);

create table subscription(
    id varchar(20) references userDetails(id) unique,
    subscriptionType varchar(20) primary key not null,
    beginning date,
    expiration date
);

create table music(
    id varchar(20) not null primary key,
    audioType varchar(20) not null,
    releaseYear int not null,
    availableOnLevel varchar(20) references subscription(subscriptionType) not null,
    genre varchar(20) not null
);

create table album(
    id varchar(20) not null primary key,
    musicId varchar(20) references music(id) not null unique
);

create table userOwned(
    id varchar(20) references userDetails(id) not null,
    musicId varchar(20) references music(id) not null,
    albumId varchar(20) references album(id) not null
);

create table review(
    reviewOnMusicId varchar(20) references music(id) not null,
    reviewByUserId varchar(20) references userDetails(id) not null,
    rating decimal not null check ( rating >= 0 or rating <= 10 ),
    description varchar(256),
    dateOfReview date not null
);

create table playbackHistory(
    userId varchar(20) references userDetails(id) not null,
    musicId varchar(20) references music(id) not null
);

create table settings(
    userId varchar(20) references userDetails(id) not null unique,
    font varchar(20) not null,
    theme varchar(20) not null
);

create table Library(
    userId varchar(20) references userDetails(id) not null,
    musicId varchar(20) references music(id) not null unique,
    albumId varchar(20) references album(id) not null
);

create table favourite(
    userId varchar(20) references userDetails(id) not null,
    musicId varchar(20) references music(id) not null,
    albumId varchar(20) references album(id) not null
);

