DROP TABLE IF EXISTS drivers CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE DRIVERS
(
    ID INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    LAST_NAME  VARCHAR NOT NULL,
    FIRST_NAME VARCHAR NOT NULL,
    MID_NAME   VARCHAR,
    PHONE      VARCHAR,
    RATING     INTEGER
);

INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Иванов', 'Иван', 'Иванович', '8-926-456-11-22', 5);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Петров', 'Петр', 'Петрович', '8-926-456-11-23', 7);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Сидоров', 'Семён', 'Михайлович', '8-926-456-11-24', 2);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Макаров', 'Михаил', 'Алексеевич', '8-926-313-11-25', 4);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Алексеев', 'Алексей', 'Алексеевич', '8-926-313-11-26', 3);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Пономарёв', 'Альберт', 'Иванович', '8-926-313-11-27', 6);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Егоров', 'Дмитрий', 'Егорович', '8-926-313-11-28', 0);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Семёнов', 'Семён', 'Семёнович', '8-926-313-11-29', 9);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Кутепов', 'Александр', 'Александрович', '8-926-313-11-29', 9);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Ларионов', 'Алексей', 'Петрович', '8-926-314-11-01', 4);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Вавилов', 'Дмитрий', 'Аркадьевич', '8-926-314-11-02', 8);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Конев', 'Виктор', 'Семенович', '8-926-314-11-03', 5);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Инокентьев', 'Михаил', 'Михайлович', '8-926-314-11-04', 8);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Калинин', 'Кирилл', 'Степанович', '8-926-314-11-05', 4);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Зимин', 'Фёдор', 'Петрович', '8-926-314-11-06', 4);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Аркадьев', 'Аркадий', 'Иванович', '8-926-000-00-00', 5);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Иноземцев', 'Леонид', 'Эдуардович', '8-926-000-00-01', 4);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Жигулин', 'Семён', 'Владиславович', '8-926-000-00-02', 2);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Смирнов', 'Игорь', 'Алексеевич', '8-926-000-00-03', 5);
INSERT INTO public.drivers (last_name, first_name, mid_name, phone, rating)
values ('Андронов', 'Роман', 'Юрьевич', '8-926-000-00-04', 9);

