Create database JV250321_ProductManagement;
use JV250321_ProductManagement;
create table Product
(
    product_id      int primary key auto_increment,
    product_name    varchar(100) not null unique,
    product_price   float        not null check (product_price > 0),
    product_title   varchar(200) not null,
    product_created date         not null,
    product_catalog varchar(100) not null,
    product_status  bit default 1
);
DELIMITER &&
create procedure find_all_product()
begin
    select * from product;
end;
DELIMITER &&
DELIMITER &&
create procedure is_exist_product_name(
    name_in varchar(100),
    OUT is_exist int
)
begin
    set is_exist = (select count(p.product_id) from product p where p.product_name like name_in);
end;
DELIMITER &&
DELIMITER &&
create procedure create_product(
    name_in varchar(100),
    price_in float,
    title_in varchar(200),
    created_in date,
    catalog_in varchar(100),
    status_in bit
)
begin
    insert into Product(product_name, product_price, product_title, product_created, product_catalog, product_status)
    values (name_in, price_in, title_in, created_in, catalog_in, status_in);
end;
DELIMITER &&
DELIMITER &&
create procedure update_product(
    id_in int,
    name_in varchar(100),
    price_in float,
    title_in varchar(200),
    created_in date,
    catalog_in varchar(100),
    status_in bit
)
begin
    update product
    set product_name    = name_in,
        product_price   = price_in,
        product_title   = title_in,
        product_created = created_in,
        product_catalog = catalog_in,
        product_status  = status_in
    where product_id = id_in;
end;
DELIMITER &&
DELIMITER &&
create procedure delete_product(id_in int)
begin
    delete from Product where product_id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure find_product_by_id(id_in int)
begin
    select * from Product where product_id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure find_product_by_name(name_in varchar(100))
begin
    declare name_seach varchar(100);
    set name_seach = concat(concat('%', name_in), '%');
    select * from Product where product_name like name_seach;
end &&
DELIMITER &&

DELIMITER &&
create procedure statitic_product_by_catalog()
begin
    select p.product_catalog, count(p.product_id) as 'count_product'
    from Product p
    group by p.product_catalog;
end &&
DELIMITER &&
