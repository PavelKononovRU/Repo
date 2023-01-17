-- Заполнить таблицу "Subscriptions"
INSERT INTO public.subscriptions (create_at, update_at, start_at, expires_at, status, interval,
                                  interval_count, price, send_sms, tariff_id, user_id, is_promo, promocode_id ) VALUES
       ('2023-01-09 22:30:59.000000', '2023-02-09 22:31:02.000000', '2023-06-09 22:31:05.000000', '2023-01-09 22:31:10.000000',
        'ACTIVE', 'TEST_1', 123, 450, 1, 1, 1, true, 1),
       ('2023-01-09 22:30:59.000000', '2023-02-09 22:31:02.000000', '2023-06-09 22:31:05.000000', '2023-01-09 22:31:10.000000',
        'INACTIVE', 'TEST_1', 456, 650, 1, 2, 2, true, 2),
       ('2023-01-09 22:30:59.000000', '2023-02-09 22:31:02.000000', '2023-06-09 22:31:05.000000', '2023-01-09 22:31:10.000000',
        'AWAITING_TRANSACTION', 'TEST_2', 789, 850, 1, 2, 2, true, 1),
       ('2023-01-09 22:30:59.000000', '2023-02-09 22:31:02.000000', '2023-06-09 22:31:05.000000', '2023-01-09 22:31:10.000000',
        'ARCHIVED', 'TEST_3', 313, 1250, 1, 1, 1, true, 2),
       ('2023-01-09 22:30:59.000000', '2023-02-09 22:31:02.000000', '2023-06-09 22:31:05.000000', '2023-01-09 22:31:10.000000',
        'AWAITING_TRANSACTION', 'TEST_1', 111, 1500, 1, 3, 3, true, 1),
       ('2023-01-09 22:30:59.000000', '2023-02-09 22:31:02.000000', '2023-06-09 22:31:05.000000', '2023-01-09 22:31:10.000000',
        'ACTIVE', 'TEST_3', 222, 3000, 1, 2, 1, true, 2)