delete from category;
delete from category_category;

insert into category values(0, 'Top', 'header', 0, 'n/a');
insert into category values(1000, 'Transportation', 'header', 0, 'n/a');
insert into category values(1001, 'Bikes', 'header', 0, 'n/a');
insert into category values(1002, 'Automobiles', 'header', 0, 'n/a');
insert into category values(1003, 'Road Bikes', 'header', 0, 'n/a');
insert into category values(1004, 'Mountain Bikes', 'header', 0, 'n/a');
insert into category values(1005, 'Cannondale Road', 'header', 0, 'n/a');
insert into category values(1006, 'Schwinn Road', 'header', 0, 'n/a');
insert into category values(1007, 'Cannondale Mountain', 'header', 0, 'n/a');
insert into category values(1008, 'Schwinn Mountain', 'header', 0, 'n/a');
insert into category values(1009, 'Cars', 'header', 0, 'n/a');
insert into category values(1010, 'Trucks', 'header', 0, 'n/a');
insert into category values(1011, 'SUVs', 'header', 0, 'n/a');
insert into category values(1012, 'Ford Cars', 'header', 0, 'n/a');
insert into category values(1013, 'Ford Trucks', 'header', 0, 'n/a');
insert into category values(1014, 'Ford SUVs', 'header', 0, 'n/a');
insert into category values(1015, 'Toyota Cars', 'header', 0, 'n/a');
insert into category values(1016, 'Toyota Trucks', 'header', 0, 'n/a');
insert into category values(1017, 'Toyota SUVs', 'header', 0, 'n/a');
insert into category values(1018, 'Audi', 'header', 0, 'n/a');
insert into category values(1019, 'Porsche', 'header', 0, 'n/a');

insert into category_category (parent_id, category_id) values(0,1000);
insert into category_category (parent_id, category_id) values(1000,1001);
insert into category_category (parent_id, category_id) values(1000,1002);
insert into category_category (parent_id, category_id) values(1001,1003);
insert into category_category (parent_id, category_id) values(1001,1004);
insert into category_category (parent_id, category_id) values(1003,1005);
insert into category_category (parent_id, category_id) values(1003,1006);
insert into category_category (parent_id, category_id) values(1004,1007);
insert into category_category (parent_id, category_id) values(1004,1008);
insert into category_category (parent_id, category_id) values(1002,1009);
insert into category_category (parent_id, category_id) values(1002,1010);
insert into category_category (parent_id, category_id) values(1002,1011);
insert into category_category (parent_id, category_id) values(1009,1012);
insert into category_category (parent_id, category_id) values(1010,1013);
insert into category_category (parent_id, category_id) values(1011,1014);
insert into category_category (parent_id, category_id) values(1009,1015);
insert into category_category (parent_id, category_id) values(1010,1016);
insert into category_category (parent_id, category_id) values(1011,1017);
insert into category_category (parent_id, category_id) values(1009,1018);
insert into category_category (parent_id, category_id) values(1009,1019);

insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(500, 'Cannondale C250', 'Sport Road Bike with good performance and good components', 499.99, 'n/a', 'c25012345', 449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(501, 'Cannondale C25000', 'Racing Road Bike ridden by Lance Armstrong', 4499.99, 'n/a', 'c25012345', 4449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(502, 'Cannondale M250', 'Sport Mountain Bike with good performance and good components', 499.99, 'n/a', 'c25012346', 449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(503, 'Cannondale M350', 'Sport Mountain Bike with great performance and great components', 1499.99, 'n/a', 'c25012347', 1449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(504, 'Cannondale M1250', 'Racing Mountain Bike that one the World Cup', 3499.99, 'n/a', 'c2501239', 3449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(505, '2002 Audi TT', 'Cool new sports car', 34999.99, 'n/a', 'c25012345', 32449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(506, '2002 Audi A4', 'Stylish new every day car', 24999.99, 'n/a', 'c25012345', 22449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(507, '2002 Audi A4 Avant', 'The best wagon in its class', 26999.99, 'n/a', 'c25012345', 24449.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(508, '2002 Audi A6', 'Luxury at a low price', 49999.99, 'n/a', 'c25012345', 44999.99, 1, 0);
insert into product (product_id, title, description, price, image, sku, sale_price, on_sale, visible)
values(509, '2002 Audi A8', 'Luxury car, best in its class', 89999.99, 'n/a', 'c25012345', 84499.99, 1, 0);

insert into product_category (product_id, category_id)
values(500, 1005);
insert into product_category (product_id, category_id)
values(501, 1005);
insert into product_category (product_id, category_id)
values(502, 1007);
insert into product_category (product_id, category_id)
values(503, 1007);
insert into product_category (product_id, category_id)
values(504, 1007);
insert into product_category (product_id, category_id)
values(505, 1018);
insert into product_category (product_id, category_id)
values(506, 1018);
insert into product_category (product_id, category_id)
values(507, 1018);
insert into product_category (product_id, category_id)
values(508, 1018);
insert into product_category (product_id, category_id)
values(509, 1018);

insert into order_statuses (id, name)
values(1, "Open");
