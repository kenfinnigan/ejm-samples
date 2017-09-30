# Database : `cayambe`
# --------------------------------------------------------

#
# Table structure for table `billing_info`
#

DROP TABLE IF EXISTS billing_info;
CREATE TABLE billing_info (
  billing_id int(11) NOT NULL auto_increment PRIMARY KEY,
  order_id int(11) NOT NULL default '0',
  name varchar(50) default NULL,
  address1 varchar(128) default NULL,
  address2 varchar(128) default NULL,
  city varchar(32) default NULL,
  state varchar(16) default NULL,
  zipcode varchar(10) default NULL,
  country varchar(32) default NULL,
  name_on_card varchar(50) default NULL,
  card_type varchar(16) default NULL,
  card_number varchar(19) default NULL,
  card_expiration_month int(2) default NULL,
  card_expiration_year int(4) default NULL,
  authorization_code varchar(32) default NULL,
  phone varchar(64) default NULL,
  email varchar(64) default NULL
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `cart_line_items`
#

DROP TABLE IF EXISTS cart_line_items;
CREATE TABLE cart_line_items (
  cart_id varchar(255) NOT NULL default '',
  product_id varchar(255) NOT NULL default '',
  quantity int(11) NOT NULL default '0'
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `category`
#

DROP TABLE IF EXISTS category;
CREATE TABLE category (
  category_id int(11) NOT NULL auto_increment PRIMARY KEY,
  name varchar(50) NOT NULL default '',
  header text,
  visible tinyint(4) default NULL,
  image varchar(120) default NULL
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `category_category`
#

DROP TABLE IF EXISTS category_category;
CREATE TABLE category_category (
  category_id int(11) NOT NULL default '0',
  parent_id int(11) NOT NULL default '0'
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `order_line_items`
#

DROP TABLE IF EXISTS order_line_items;
CREATE TABLE order_line_items (
  order_line_item_id int(11) NOT NULL auto_increment PRIMARY KEY,
  order_id int(11) NOT NULL default '0',
  product_id int(11) NOT NULL default '0',
  sale_price double NOT NULL default '0',
  quantity int(11) NOT NULL default '0'
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `order_statuses`
#

DROP TABLE IF EXISTS order_statuses;
CREATE TABLE order_statuses (
  id int(11) NOT NULL auto_increment PRIMARY KEY,
  name varchar(255) NOT NULL default ''
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `orders`
#

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  order_id int(11) NOT NULL auto_increment PRIMARY KEY,
  date timestamp NOT NULL,
  status varchar(24) default '1',
  sales_tax_desc varchar(255) default NULL,
  sales_tax_total double default NULL
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `product`
#

DROP TABLE IF EXISTS product;
CREATE TABLE product (
  product_id int(11) NOT NULL auto_increment PRIMARY KEY,
  title varchar(120) NOT NULL default '',
  description blob,
  price double default NULL,
  image varchar(120) default NULL,
  sku varchar(24) default NULL,
  sale_price double default NULL,
  on_sale tinyint(1) NOT NULL default '0',
  visible tinyint(1) NOT NULL default '0'
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `product_category`
#

DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category (
  product_id int(11) NOT NULL default '0',
  category_id int(11) NOT NULL default '0'
) ENGINE=MyISAM;
# --------------------------------------------------------

#
# Table structure for table `shipping_info`
#

DROP TABLE IF EXISTS shipping_info;
CREATE TABLE shipping_info (
  shipping_id int(11) NOT NULL auto_increment PRIMARY KEY,
  order_id int(11) NOT NULL default '0',
  name varchar(50) default NULL,
  address1 varchar(128) default NULL,
  address2 varchar(128) default NULL,
  city varchar(32) default NULL,
  zipcode varchar(10) default NULL,
  state varchar(16) default NULL,
  country varchar(32) default NULL,
  method varchar(64) default NULL,
  amount double default NULL,
  instructions varchar(255) default NULL,
  phone varchar(64) default NULL
) ENGINE=MyISAM;

    
