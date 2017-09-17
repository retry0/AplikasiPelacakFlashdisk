create table akun(
id varchar2(20),
password varchar2(20),
primary key(id));

create table flashdisk(
flashdisk_name varchar2(50),
product_id varchar2(50),
id varchar2(20),
primary key(product_id),
foreign key(id) references akun(id));