--匯入電文檔
INSERT INTO IR_CASE (SEQ_NO, VALUE_DATE, AMOUNT, CURRENCY, PROCESS_STATUS) VALUES ('123456789012345', '2022-03-10', '2000','USD','1');
INSERT INTO IR_CASE (SEQ_NO, VALUE_DATE, AMOUNT, CURRENCY, PROCESS_STATUS) VALUES ('123456789012346', '2022-03-10', '1500','USD','8');
INSERT INTO IR_CASE (SEQ_NO, VALUE_DATE, AMOUNT, CURRENCY, PROCESS_STATUS) VALUES ('123456789012347', '2022-03-10', '1300','USD','3');
INSERT INTO IR_CASE (SEQ_NO, VALUE_DATE, AMOUNT, CURRENCY, PROCESS_STATUS) VALUES ('123456789012348', '2022-03-10', '2000','USD','1');

--匯入匯款案件主檔
INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00947',4,'Y','093','Y','05052322','USD',3456.78);

INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00955',0,'Y','093','Y','86483666','HKD',13681.89);

INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00963',0,'N','101','Y','03218306','USD',542436.2);

INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00971',0,'N','102','Y','70538519','JPY',432643);

INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00979',0,'N','093','Y','05052322','ZAR',234623.76);

INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00984',4,'N','093','Y','05052322','ZAR',234623.76);

INSERT INTO IR_APPLY_MASTER (IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURRENCY,IR_AMT)
VALUES ('S1NHA00992',5,'N','093','Y','05052322','ZAR',234623.76);

