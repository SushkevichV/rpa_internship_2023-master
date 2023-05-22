DROP TABLE IF EXISTS CURRENCY cascade;
DROP TABLE IF EXISTS WEEKENDS cascade;

CREATE TABLE WEEKENDS (
  WEEKEND_ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  CALENDAR_DATE DATE NULL,
  IS_DAY_OFF BIT NULL,
  CONSTRAINT PK_WEEKEND12345687 PRIMARY KEY (WEEKEND_ID)
);

INSERT INTO WEEKENDS (CALENDAR_DATE, IS_DAY_OFF)
VALUES
    ('2022-12-01', 0),
    ('2022-12-02', 1),
    ('2022-12-03', 1),
    ('2022-12-04', 0),
    ('2022-12-05', 0),
    ('2022-12-06', 0),
    ('2022-12-07', 0),
    ('2022-12-08', 0),
    ('2022-12-09', 0),
    ('2022-12-10', 1),
    ('2022-12-11', 1),
    ('2022-12-12', 0),
    ('2022-12-13', 0),
    ('2022-12-14', 0),
    ('2022-12-15', 0),
    ('2022-12-16', 0),
    ('2022-12-17', 1),
    ('2022-12-18', 1),
    ('2022-12-19', 0),
    ('2022-12-20', 0),
    ('2022-12-21', 0),
    ('2022-12-22', 0),
    ('2022-12-23', 0),
    ('2022-12-24', 1),
    ('2022-12-25', 1),
    ('2022-12-26', 0),
    ('2022-12-27', 0),
    ('2022-12-28', 0),
    ('2022-12-29', 0),
    ('2022-12-30', 0),
    ('2022-12-31', 1),
    ('2023-01-01', 1),
    ('2023-01-02', 1),
    ('2023-01-03', 0),
    ('2023-01-04', 0),
    ('2023-01-05', 0),
    ('2023-01-06', 0),
    ('2023-01-07', 1),
    ('2023-01-08', 1),
    ('2023-01-09', 0),
    ('2023-01-10', 0),
    ('2023-01-11', 0),
    ('2023-01-12', 0),
    ('2023-01-13', 0),
    ('2023-01-14', 1),
    ('2023-01-15', 1),
    ('2023-01-16', 0),
    ('2023-01-17', 0),
    ('2023-01-18', 0),
    ('2023-01-19', 0),
    ('2023-01-20', 0),
    ('2023-01-21', 1),
    ('2023-01-22', 1),
    ('2023-01-23', 0),
    ('2023-01-24', 0),
    ('2023-01-25', 0),
    ('2023-01-26', 0),
    ('2023-01-27', 0),
    ('2023-01-28', 1),
    ('2023-01-29', 1),
    ('2023-01-30', 0),
    ('2023-01-31', 0),
    ('2023-02-01', 0),
    ('2023-02-02', 0),
    ('2023-02-03', 0),
    ('2023-02-04', 1),
    ('2023-02-05', 1),
    ('2023-02-06', 0),
    ('2023-02-07', 0),
    ('2023-02-08', 0),
    ('2023-02-09', 0),
    ('2023-02-10', 0),
    ('2023-02-11', 1),
    ('2023-02-12', 1),
    ('2023-02-13', 0),
    ('2023-02-14', 0),
    ('2023-02-15', 0),
    ('2023-02-16', 0),
    ('2023-02-17', 0),
    ('2023-02-18', 1),
    ('2023-02-19', 1),
    ('2023-02-20', 0),
    ('2023-02-21', 0),
    ('2023-02-22', 0),
    ('2023-02-23', 0),
    ('2023-02-24', 0),
    ('2023-02-25', 1),
    ('2023-02-26', 1),
    ('2023-02-27', 0),
    ('2023-02-28', 0),
    ('2023-03-01', 0),
    ('2023-03-02', 0),
    ('2023-03-03', 0),
    ('2023-03-04', 1),
    ('2023-03-05', 1),
    ('2023-03-06', 0),
    ('2023-03-07', 0),
    ('2023-03-08', 0),
    ('2023-03-09', 0),
    ('2023-03-10', 0),
    ('2023-03-11', 1),
    ('2023-03-12', 1),
    ('2023-03-13', 0),
    ('2023-03-14', 0),
    ('2023-03-15', 0),
    ('2023-03-16', 0),
    ('2023-03-17', 0),
    ('2023-03-18', 1),
    ('2023-03-19', 1),
    ('2023-03-20', 0),
    ('2023-03-21', 0),
    ('2023-03-22', 0),
    ('2023-03-23', 0),
    ('2023-03-24', 0),
    ('2023-03-25', 1),
    ('2023-03-26', 1),
    ('2023-03-27', 0),
    ('2023-03-28', 0),
    ('2023-03-29', 0),
    ('2023-03-30', 0),
    ('2023-03-31', 0),
    ('2023-04-01', 1),
    ('2023-04-02', 1),
    ('2023-04-03', 0),
    ('2023-04-04', 0),
    ('2023-04-05', 0),
    ('2023-04-06', 0),
    ('2023-04-07', 0),
    ('2023-04-08', 1),
    ('2023-04-09', 1),
    ('2023-04-10', 0),
    ('2023-04-11', 0),
    ('2023-04-12', 0),
    ('2023-04-13', 0),
    ('2023-04-14', 0),
    ('2023-04-15', 1),
    ('2023-04-16', 1),
    ('2023-04-17', 0),
    ('2023-04-18', 0),
    ('2023-04-19', 0),
    ('2023-04-20', 0),
    ('2023-04-21', 0),
    ('2023-04-22', 1),
    ('2023-04-23', 1),
    ('2023-04-24', 0),
    ('2023-04-25', 0),
    ('2023-04-26', 0),
    ('2023-04-27', 0),
    ('2023-04-28', 0),
    ('2023-04-29', 1),
    ('2023-04-30', 1),
    ('2023-05-01', 1),
    ('2023-05-02', 0),
    ('2023-05-03', 0),
    ('2023-05-04', 0),
    ('2023-05-05', 0),
    ('2023-05-06', 1),
    ('2023-05-07', 1),
    ('2023-05-08', 1),
    ('2023-05-09', 1),
    ('2023-05-10', 0),
    ('2023-05-11', 0),
    ('2023-05-12', 0),
    ('2023-05-13', 0),
    ('2023-05-14', 1),
    ('2023-05-15', 0),
    ('2023-05-16', 0),
    ('2023-05-17', 0),
    ('2023-05-18', 0),
    ('2023-05-19', 0),
    ('2023-05-20', 1),
    ('2023-05-21', 1),
    ('2023-05-22', 0),
    ('2023-05-23', 0),
    ('2023-05-24', 0),
    ('2023-05-25', 0),
    ('2023-05-26', 0),
    ('2023-05-27', 1),
    ('2023-05-28', 1),
    ('2023-05-29', 0),
    ('2023-05-30', 0),
    ('2023-05-31', 0);
