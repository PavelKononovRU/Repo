insert into cards (card_id, number, principal, csv, user_id)
values
    (1, '2101-2222-3333-4444', 'user1', '123', 1),
    (2, '2102-3333-1111-4444', 'user2', '223', 2);

SELECT setval('cards_card_id_seq', (SELECT MAX(card_id) FROM cards));

insert into payment_table (payment_id, create_at, update_at, status, message, user_id, card_id)
VALUES (1, DATE '2023-01-18', DATE '2023-01-18', 'SUCCESSFULLY', 'Test', 1, 1);

insert into payment_table (payment_id, create_at, update_at, status, message, user_id, card_id)
VALUES (2, DATE '2023-02-10', DATE '2023-02-10', 'ERROR', 'Test2', 2, 2);

SELECT setval('payment_table_payment_id_seq', (SELECT MAX(payment_id) FROM payment_table));
