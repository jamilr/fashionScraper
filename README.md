# Fashion Products Scraper

Fashion Products Scraper is a standalone module for scraping fashion products metadata from the fashion retailers' web-sites. Written in Java 7 with an integration of Spring dependency injection, and Jsoup HTML documents parser.

# The Project Road Map 

1. Enabling full-text search with ElasticSearch integration
2. Enhancing search using fashion features (e.g. "coats with leather strap") using natural language 
3. Fashion product auto-tagging by introducing SVM-based classification 

# Supported List of Product Categories

## Female Categories
1. Handbags
2. Shoes
3. Dresses
4. Shirts

## Male Categories

1. Shirts
2. Trousers
3. Shoes
4. Jackets

## Supported List of Fashion Retailers

1. Selfridges
2. Harrods
3. Marks and Spencer
4. John Lewis
5. Zara
6. Burberry

The module supports a number of database options - SQLite, MongoDB, and Cassandra.

# How To Run Scraper

Scraper has a number of parameters to specify upon running the scraper from Shell script or command line. The parameters tell the scraper where and what to scraper, as well as in whats quantities, and where to store data, afterwards.

-s -- store name

-c -- categories to be scraped listed with a comma separator in the following format: "category-gender". For instance, "shoes-m,shoes-f,shirts-m,jackets-m" tells the scraper to scrape shoes for men, shoes for women, shirts for men, and jackets for men.

-m -- tells the scraper whether to scrape the whole inventory available for the category on the web-site or not. Two options are available for that parameter - limited, or all.

-q -- if previous parameter, m, has a value - limited, then q tells the scraper what number of products to scrape for the retailer's web-site. For instance, if q is 20, then, for every category listed in c parameter, there is going to be 20 products scraped from the retailer's web-site.

-d -- the type of database to use for storing scraper's results. Three options are available for the parameter - sql, mongo, cassandra
