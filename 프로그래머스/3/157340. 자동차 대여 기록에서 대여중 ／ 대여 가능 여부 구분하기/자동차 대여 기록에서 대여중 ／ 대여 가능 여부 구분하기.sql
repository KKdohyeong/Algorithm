WITH RENTAL_IMPOSSIBLE_CAR AS (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE '2022-10-16' >= START_DATE
      AND '2022-10-16' <= END_DATE
)
SELECT T.CAR_ID,
       CASE 
           WHEN T.CAR_ID IN (
               SELECT RC.CAR_ID
               FROM RENTAL_IMPOSSIBLE_CAR RC
           ) THEN '대여중'
           ELSE '대여 가능'
       END AS AVAILABILITY
FROM (
    SELECT DISTINCT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
) AS T
ORDER BY T.CAR_ID DESC;