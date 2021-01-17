INSERT INTO users(login, password, first_name, last_name, role,tariff_id) VALUES( 'admin', SHA1('admin'), 'Роман', 'Александров', 'ADMIN', 4);

INSERT INTO users(login, password, first_name, last_name, total_amount, discount, traffic, tariff_id ) VALUES('user1', SHA1('user1'), 'Дмитрий', 'Смирнов', 10.5, 5, 200, 1);
INSERT INTO users(login, password, first_name, last_name, total_amount, discount, traffic, tariff_id ) VALUES('user2', SHA1('user2'), 'Федор', 'Козлов', 12.5, 5, 300, 2);
INSERT INTO users(login, password, first_name, last_name, total_amount, discount, traffic, tariff_id ) VALUES('user3', SHA1('user3'), 'Алексей', 'Чернов', 13.5, 10, 400, 1);
INSERT INTO users(login, password, first_name, last_name, total_amount, discount, traffic, tariff_id ) VALUES('user4', SHA1('user4'), 'Давид', 'Орлов', 14.5, 10, 500, 2);
INSERT INTO users(login, password, first_name, last_name, total_amount, traffic, tariff_id, promotion_id) VALUES('user5', SHA1('user5'), 'Анастасия', 'Сергеева', 10, 200, 1, 1);
INSERT INTO users(login, password, first_name, last_name, total_amount, status_block, traffic, tariff_id) VALUES('user6', SHA1('user6'), 'Анна', 'Попова', 14.5, true, 500, 3);
INSERT INTO users(login, password, first_name, last_name, total_amount, status_block, traffic, tariff_id) VALUES('user7', SHA1('user7'), 'Мария', 'Владимирова', 14.5, true, 500, 3);
INSERT INTO users(login, password, first_name, last_name, total_amount, status_block, traffic, tariff_id) VALUES('user8', SHA1('user8'), 'Ариана', 'Русакова', 14.5, true, 500, 3);
INSERT INTO users(login, password, first_name, last_name, total_amount, status_block, traffic, tariff_id) VALUES('user9', SHA1('user9'), 'Марк', 'Демьянов', 14.5, true, 500, 3);
INSERT INTO users(login, password, first_name, last_name, total_amount, status_block, traffic, tariff_id) VALUES('user10', SHA1('user10'), 'Анна', 'Русанова', 14.5, true, 500, 3);
INSERT INTO users(login, password, first_name, last_name, total_amount, status_block, traffic, tariff_id) VALUES('user11', SHA1('user11'), 'Анна', 'Листопадова', 14.5, true, 500, 3);

INSERT INTO tariff_plans(tariff_name, price, description) VALUES('SUPER', 5, 'Скорость 10Мб/с');
INSERT INTO tariff_plans(tariff_name, price, description) VALUES('SUPER+', 10, 'Скорость 30Мб/с');
INSERT INTO tariff_plans(tariff_name, price, description) VALUES('SUPER++', 20, 'Скорость 50Мб/с');
INSERT INTO tariff_plans(tariff_name, price, description) VALUES('MEGA', 35, 'Скорость 100Мб/с');
INSERT INTO tariff_plans(tariff_name, price, description) VALUES('MEGA+', 40, 'бонус');

INSERT INTO promotions(promotion_name, start_date, end_date, description, tariff_id, new_price) VALUES('Осенние скидки!', '2020-09-01', '2020-10-01', 'Тариф SUPER++ всего за 17р.!', 3 , 17);
INSERT INTO promotions(promotion_name, start_date, end_date, description, tariff_id, new_price) VALUES('Осенние скидки продолжаются!','2020-11-01', '2020-12-31', 'Тариф MEGA всего за 17р.!', 4, 30);
INSERT INTO promotions(promotion_name, start_date, end_date, description, tariff_id, new_price) VALUES('Наступление новогодних скидок!', '2020-11-01', '2020-12-31', 'Тариф SUPER+ всего за 17р.!', 2, 7);

INSERT INTO payments(amount, payment_date, user_id) VALUES( 5, '2020-10-01', 5);
INSERT INTO payments(amount, payment_date, user_id) VALUES(25, '2020-10-01', 5);