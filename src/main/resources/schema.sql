create table if not exists authorities (
	username CITEXT not null,
	authority CITEXT not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index if not exists ix_auth_username on authorities (username,authority);

create table if not exists words (
    id serial,
    username Text not null,
    word Text not null,
);