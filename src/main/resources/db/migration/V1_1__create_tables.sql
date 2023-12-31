create table mobile_phone (status integer not null, id bigint generated by default as identity, model_id bigint not null, primary key (id));
create table mobile_phone_booking (action integer not null, status integer not null, client_id bigint not null, id bigint generated by default as identity, mobile_phone_id bigint not null, timestamp timestamp(6) not null, primary key (id));
create table mobile_phone_booking_client (id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), primary key (id));
create table mobile_phone_model (id bigint generated by default as identity, brand varchar(255) not null, device varchar(255) not null, version varchar(255), primary key (id));
alter table if exists mobile_phone add constraint FKiq39jsqqcitl1pp9uew34xcx4 foreign key (model_id) references mobile_phone_model;
alter table if exists mobile_phone_booking add constraint FKfqs9ta3ilc4760co2yph8d1d2 foreign key (client_id) references mobile_phone_booking_client;
alter table if exists mobile_phone_booking add constraint FK59t5tlq0phudyf6bve8e1xqjn foreign key (mobile_phone_id) references mobile_phone;
