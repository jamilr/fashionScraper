DROP TABLE IF EXISTS [Brand];
CREATE TABLE [Brand] (
[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[name] VARCHAR(20)  NOT NULL,
[logourl] VARCHAR(200)
);

DROP TABLE IF EXISTS [Category];
CREATE TABLE [Category] (
[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[name] VARCHAR(20)  NOT NULL
);

DROP TABLE IF EXISTS [Color];
CREATE TABLE [Color] (
[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[name] VARCHAR(40)  NOT NULL
);

DROP TABLE IF EXISTS [Style];
CREATE TABLE [Style] (
  [id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
  [name] VARCHAR(40)  NOT NULL
);

DROP TABLE IF EXISTS [Season];
CREATE TABLE [Season] (
  [id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
  [name] VARCHAR(40)  NOT NULL
);

DROP TABLE IF EXISTS [Product];
CREATE TABLE [Product] (
[id]            INTEGER       PRIMARY KEY AUTOINCREMENT NOT NULL,
[url]           VARCHAR(400)  NOT NULL,
[title]         VARCHAR(200)  NOT NULL,
[description]   VARCHAR(400)  NOT NULL,
[sku]           VARCHAR(20)   NOT NULL,
[price]         NUMERIC   NOT NULL,
[gender]        INTEGER   NOT NULL,
[brand_id]      INTEGER   NOT NULL,
[store_id]      INTEGER   NOT NULL,
[style_id]      INTEGER,
[season_id]     INTEGER,
[category_id]   INTEGER   NOT NULL,
[liked]         INTEGER   NOT NULL,
[hasOffer]      INTEGER   NOT NULL
);

DROP TABLE IF EXISTS [Offer];
CREATE TABLE [Offer] (
  [id]          INTEGER NOT NULL,
  [product_id]  INTEGER NOT NULL,
  [offer_id]    INTEGER NOT NULL,
  [price]       NUMERIC NOT NULL
);

DROP TABLE IF EXISTS [ProductColor];
CREATE TABLE [ProductColor] (
[product_id]  INTEGER  NOT NULL,
[color_id]    INTEGER  NOT NULL
);

DROP TABLE IF EXISTS [ProductImage];
CREATE TABLE [ProductImage] (
[id]            INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[product_id]    INTEGER  NOT NULL,
[url]           VARCHAR(200)  NOT NULL
);

DROP TABLE IF EXISTS [ProductSize];
CREATE TABLE [ProductSize] (
[product_id]  INTEGER  NOT NULL,
[size_id]     INTEGER  NOT NULL
);

DROP TABLE IF EXISTS [Size];
CREATE TABLE [Size] (
[id]          INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,
[store_id]    INTEGER NOT NULL,
[category_id] INTEGER NOT NULL,
[value]       VARCHAR(10)  NOT NULL
);

DROP TABLE IF EXISTS [Store];
CREATE TABLE [Store] (
[id]          INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[shortName]   VARCHAR(10)  NOT NULL,
[longName]    VARCHAR(30)  NOT NULL,
[logourl]     VARCHAR(300) NOT NULL
);

DROP TABLE IF EXISTS [StoreCoordinate];
CREATE TABLE [StoreCoordinate] (
  [id]        INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
  [store_id]  INTEGER NOT NULL,
  [latitude]  NUMERIC NOT NULL,
  [longitude] NUMERIC NOT NULL,
  [postcode]  VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS [StoreCategory];
CREATE TABLE [StoreCategory] (
[id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
[store_id] INTEGER  NOT NULL,
[category_id] INTEGER  NOT NULL,
[gender] INTEGER NOT NULL,
[url] VARCHAR(300)  NOT NULL,
[quantity] INTEGER NOT NULL
);

/*Season*/
INSERT INTO Season(name) VALUES('Winter');          /*1*/
INSERT INTO Season(name) VALUES('Spring');          /*2*/
INSERT INTO Season(name) VALUES('Summer');          /*3*/
INSERT INTO Season(name) VALUES('Fall');            /*4*/

/*Store*/
INSERT INTO Store(shortName, longName, logourl) VALUES('LV', 'Louis Vuitton',     'http://static.louisvuitton.com/content/dam/lv/online/css/2.15.2.10//images/sprite_logo_print.png');
INSERT INTO Store(shortName, longName, logourl) VALUES('HR', 'Harrods',           'http://img1.wikia.nocookie.net/__cb20140830210140/logopedia/images/b/bc/Harrods.jpg');
INSERT INTO Store(shortName, longName, logourl) VALUES('JL', 'John Lewis',        'http://img2.wikia.nocookie.net/__cb20101206210113/logopedia/images/thumb/a/a2/John_Lewis_logo.svg/250px-John_Lewis_logo.svg.png');
INSERT INTO Store(shortName, longName, logourl) VALUES('SF', 'Selfridges',        'http://upload.wikimedia.org/wikipedia/en/e/e6/Selfridges.png');
INSERT INTO Store(shortName, longName, logourl) VALUES('MS', 'Marks and Spencer', 'http://www.lagourmet.co.uk/wp-content/uploads/2013/06/M-and-S-Logo.jpg');
INSERT INTO Store(shortName, longName, logourl) VALUES('BR', 'Burberry',          'http://upload.wikimedia.org/wikipedia/en/0/03/Burberry_logo.png');
INSERT INTO Store(shortName, longName, logourl) VALUES('PR', 'Prada',             'http://www.prada.com/etc/designs/prada/images/base/ipad_logo_bianco.png');
INSERT INTO Store(shortName, longName, logourl) VALUES('ZR', 'Zara',              'http://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Zara_Logo_2.svg/2000px-Zara_Logo_2.svg.png');

/*Category*/
INSERT INTO Category(name) VALUES('Shoes');         /*1*/
INSERT INTO Category(name) VALUES('Bags');          /*2*/
INSERT INTO Category(name) VALUES('Dresses');       /*3*/
INSERT INTO Category(name) VALUES('Shirts');        /*4*/
INSERT INTO Category(name) VALUES('Trousers');      /*5*/
INSERT INTO Category(name) VALUES('Jackets');       /*6*/
INSERT INTO Category(name) VALUES('Ponchos');       /*7*/
INSERT INTO Category(name) VALUES('Knitwear');      /*8*/
INSERT INTO Category(name) VALUES('T-Shirts');      /*9*/
INSERT INTO Category(name) VALUES('Denim');         /*10*/
INSERT INTO Category(name) VALUES('Swimwear');      /*11*/

/*StoreCoordinate*/

/*Louis Vuitton*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(1, 51.499418, -0.163191, 'SW1X 7XL');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(1, 51.514555, -0.152396, 'SW1X 7XL');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(1, 51.515194, -0.145135, 'W1C 1DX');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(1, 51.513599, -0.086123, 'W1C 1DX');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(1, 51.506670, -0.219224, 'W1C 1DX');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(1, 51.500318, -0.159348, 'W1C 1DX');

/*Harrods*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(2, 51.499418, -0.163191, 'SW1X 7XL');

/*John Lewis*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(3, 51.514701, -0.152916, 'W1A 1AB');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(3, 51.493145, -0.159063, 'SW1W 8EL');

/*Selfridges*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(4, 51.515194, -0.145135, 'W1C 1DX');

/*Marks and Spencer*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(5, 51.514306, -0.154369, 'W1C 1AP');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(5, 51.490583, -0.164305, 'SW3 4NX');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(5, 51.498677, -0.140423, 'SW1E 5JE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(5, 51.503417, -0.018496, 'E14 5NY');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(5, 51.482798, -0.010033, 'SE10 9EJ');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(5, 51.509507, -0.199661, 'W11 3QG');

/*Burberry*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(6, 51.511011, -0.138764, 'W1B');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(6, 51.511392, -0.142936, 'W1S');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(6, 51.512260, -0.123988, 'WC2E 8JS');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(6, 51.512200, -0.122830, 'WC2E 8RF');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(6, 51.501595, -0.159725, 'SW1X 7RJ');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(6, 51.501320, -0.161920, 'SW1X 7QN');

/*Prada*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(7, 51.498453, -0.152998, 'SW1X 9LU');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(7, 51.514266, -0.152740, 'W1A 1AB');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(7, 51.509379, -0.139265, 'W1S 4PS');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(7, 51.507322, -0.148706, 'W1J 8PE');

/*Zara*/
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.506797, -0.220370, 'W12 7GF');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.503691, -0.190013, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.514535, -0.188296, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.500079, -0.161848, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.491716, -0.160175, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.496552, -0.137902, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.509722, -0.137773, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.514824, -0.155197, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.512901, -0.121036, 'W1J 8PE');
INSERT INTO StoreCoordinate(store_id, latitude, longitude, postcode) VALUES(8, 51.505208, -0.017267, 'W1J 8PE');

/*Louis Vuitton*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity) VALUES(1, 1, 0, "http://uk.louisvuitton.com/eng-gb/women/shoes/cruise-2015", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity) VALUES(1, 2, 0, "http://uk.louisvuitton.com/eng-gb/women/handbags", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity) VALUES(1, 1, 1, "http://uk.louisvuitton.com/eng-gb/men/shoes", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity) VALUES(1, 1, 0, "http://uk.louisvuitton.com/eng-gb/women/shoes", 30);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity) VALUES(1, 2, 0, "http://uk.louisvuitton.com/eng-gb/women/handbags", 30);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity) VALUES(1, 1, 1, "http://uk.louisvuitton.com/eng-gb/men/shoes", 30);

/*Harrods Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 1, 0, "http://www.harrods.com/shoes/heels", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 2, 0, "http://www.harrods.com/accessories/handbags", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 3, 0, "http://www.harrods.com/womenswear/new-season-dresses", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 4, 0, "http://www.harrods.com/womenswear/new-season-shirts-and-blouses", 20);

/*Harrods Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 1, 1, "http://www.harrods.com/shoes/formal-shoes", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 4, 1, "http://www.harrods.com/men/casual-shirts", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 5, 1, "http://www.harrods.com/men/trousers", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(2, 6, 1, "http://www.harrods.com/men/coats-and-jackets", 20);

/*John Lewis Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 1, 0, "http://www.johnlewis.com/browse/women/womens-shoes-boots/_/N-fk6", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 2, 0, "http://www.johnlewis.com/browse/women/handbags/_/N-fjg", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 3, 0, "http://www.johnlewis.com/browse/women/womens-dresses/_/N-flw", 20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 4, 0, "http://www.johnlewis.com/browse/women/womens-tops/shirt/_/N-fm4Z1z0rot1", 20);

/*John Lewis Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 1, 1, "http://www.johnlewis.com/browse/men/mens-shoes-boots/_/N-ea5",    20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 4, 1, "http://www.johnlewis.com/browse/men/mens-shirts/_/N-eaf",         20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 5, 1, "http://www.johnlewis.com/browse/men/mens-trousers/_/N-ebi",       20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(3, 6, 1, "http://www.johnlewis.com/browse/men/mens-jackets-coats/_/N-ea9",  20);

/*Selfridges Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 1, 0, "http://www.selfridges.com/en/womens/shoes/",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 2, 0, "http://www.selfridges.com/en/bags/womens/",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 3, 0, "http://www.selfridges.com/en/womens/clothing/dresses/",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 4, 0, "http://www.selfridges.com/en/womens/clothing/tops/",  20);

/*Selfridges Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 1, 1, "http://www.selfridges.com/en/mens/shoes/",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 4, 1, "http://www.selfridges.com/en/mens/clothing/shirts/casual-shirts/",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 5, 1, "http://www.selfridges.com/en/mens/clothing/trousers/",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(4, 6, 1, "http://www.selfridges.com/en/mens/clothing/coats-jackets/",  20);

/*Marks And Spencers Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 1, 0, "http://www.marksandspencer.com/l/women/shoes-and-boots/all-shoes",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 2, 0, "http://www.marksandspencer.com/l/women/handbags",                   20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 3, 0, "http://www.marksandspencer.com/l/women/dresses",                    20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 4, 0, "http://www.marksandspencer.com/l/women/shirts-and-blouses",         20);

/*Marks And Spencers Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 1, 1, "http://www.marksandspencer.com/l/men/all-shoes-and-boots",        14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 4, 1, "http://www.marksandspencer.com/l/men/casual-shirts",              14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 5, 1, "http://www.marksandspencer.com/l/men/formal-trousers",            14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(5, 6, 1, "http://www.marksandspencer.com/l/men/coats-and-casual-jackets",   14);

/*Burberry Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 1, 0, "http://uk.burberry.com/womens-shoes/",                            14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 2, 0, "http://uk.burberry.com/womens-bags/",                             14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 3, 0, "http://uk.burberry.com/dresses/",                                 14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 4, 0, "http://uk.burberry.com/womens-shirts-tops/",                      14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 7, 0, "http://uk.burberry.com/womens-ponchos-wraps/",                      14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 8, 0, "http://uk.burberry.com/womens-knitwear/",                      14);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 9, 0, "http://uk.burberry.com/womens-polo-shirts-t-shirts/",                      20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 10, 0, "http://uk.burberry.com/womens-denim/",                      20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 11, 0, "http://uk.burberry.com/womens-swimwear/",                      20);

/*Burberry Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 1, 1, "http://uk.burberry.com/mens-shoes/",                              20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 4, 1, "http://uk.burberry.com/mens-shirts/",                             20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 5, 1, "http://uk.burberry.com/mens-trousers-shorts/",                    20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(6, 6, 1, "http://uk.burberry.com/mens-jackets/",                            20);

/*Prada Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(7, 1, 0, "http://www.prada.com/en/GB/e-store/department/woman/footwear.html",20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(7, 2, 0, "http://www.prada.com/en/GB/e-store/department/woman/handbags.html",20);

/*Prada Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(7, 1, 1, "http://www.prada.com/en/GB/e-store/department/man/footwear.html",20);

/*Zara Female*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 1, 0, "http://www.zara.com/uk/en/woman/shoes/view-all-c719531.html",  20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 2, 0, "http://www.zara.com/uk/en/woman/handbags/view-all-c719532.html",                   20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 3, 0, "http://www.zara.com/uk/en/woman/dresses/view-all-c719020.html",                    20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 4, 0, "http://www.zara.com/uk/en/woman/tops/view-all-c719021.html",         20);

/*Zara Male*/
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 1, 1, "http://www.zara.com/uk/en/man/shoes/view-all-c719027.html",        20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 4, 1, "http://www.zara.com/uk/en/man/shirts/view-all-c719520.html",              20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 5, 1, "http://www.zara.com/uk/en/man/trousers/view-all-c719514.html",            20);
INSERT INTO StoreCategory(store_id, category_id, gender, url, quantity)  VALUES(8, 6, 1, "http://www.zara.com/uk/en/man/jackets-c586542.html",   20);