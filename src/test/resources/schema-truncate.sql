SET REFERENTIAL_INTEGRITY FALSE;

ALTER TABLE cart_item
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE cart_item;

ALTER TABLE product
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE product;

ALTER TABLE member
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE member;

ALTER TABLE orders
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE orders;

ALTER TABLE order_product
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE order_product;

ALTER TABLE point_addition
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE point_addition;

ALTER TABLE point_usage
    ALTER COLUMN id RESTART WITH 1;
TRUNCATE TABLE point_usage;

SET REFERENTIAL_INTEGRITY TRUE;
