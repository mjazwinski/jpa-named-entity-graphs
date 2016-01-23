create sequence CRT_ORDER_ITEM_SEQ start with 1 increment by 50
create sequence ORDER_ID_SEQ start with 1 increment by 50
create sequence PHONE_ID_SEQ start with 1 increment by 50
create sequence PRODUCT_SEQ start with 1 increment by 50
create table CRT_CUSTOMER (CST_ID bigint not null, CST_PNR varchar(255) not null, CST_CITY varchar(255), CST_FLAT_NUBMER varchar(255), CST_HOUSE_NUMBER varchar(255), CST_POSTAL_CODE varchar(255), CST_STREET varchar(255), CST_EMAIL varchar(255), CST_FIRST_NAME varchar(255), CST_LAST_NAME varchar(255), primary key (CST_ID, CST_PNR))
create table CRT_ORDER (ORD_ID bigint not null, ORD_ORDERED_ON timestamp, ORD_CUSTOMER_ID bigint, ORD_CUSTOMER_PNR varchar(255), primary key (ORD_ID))
create table CRT_ORDER_ITEM (ORI_ID bigint not null, ORI_QUANTITY integer, ORI_ORDER bigint, ORI_PRODUCT bigint, primary key (ORI_ID))
create table CRT_PHONE (PHN_ID bigint not null, PHN_NUMBER varchar(255), PHN_TYPE varchar(255), PHN_CUSTOMER_ID bigint, PHN_CUSTOMER_PNR varchar(255), PHN_PRIM_CUSTOMER_ID bigint, PHN_PRIM_CUSTOMER_PNR varchar(255), primary key (PHN_ID))
create table CRT_PRODUCT (PRD_ID bigint not null, PRD_NAME varchar(255), primary key (PRD_ID))
alter table CRT_ORDER add constraint FK1tcm5lvikgdpxqk4jpieliols foreign key (ORD_CUSTOMER_ID, ORD_CUSTOMER_PNR) references CRT_CUSTOMER
alter table CRT_ORDER_ITEM add constraint FKij3o06ipg58qckd64gelbg13h foreign key (ORI_ORDER) references CRT_ORDER
alter table CRT_ORDER_ITEM add constraint FKrar8cxyayfhgbd5s27tf78nn8 foreign key (ORI_PRODUCT) references CRT_PRODUCT
alter table CRT_PHONE add constraint FK88cgpim1mtope0gee63xcfusa foreign key (PHN_CUSTOMER_ID, PHN_CUSTOMER_PNR) references CRT_CUSTOMER
alter table CRT_PHONE add constraint FK556fvndr6c7d0ka0auiwtif32 foreign key (PHN_PRIM_CUSTOMER_ID, PHN_PRIM_CUSTOMER_PNR) references CRT_CUSTOMER
