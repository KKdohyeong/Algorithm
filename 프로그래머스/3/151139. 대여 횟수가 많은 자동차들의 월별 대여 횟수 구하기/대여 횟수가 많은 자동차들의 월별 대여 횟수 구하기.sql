with frequent_cars AS (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY as CH
    where DATE_FORMAT(CH.START_DATE, '%Y')= 2022
    and DATE_FORMAT(CH.START_DATE, '%m') >= 08
    and DATE_FORMAT(CH.START_DATE, '%m') <= 10
    group by CAR_ID
    having count(*) >= 5
)
select DATE_FORMAT(CH1.START_DATE, '%c') as 'MONTH', CH1.CAR_ID, count(*) as 'RECORDS'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as CH1 inner join frequent_cars
on CH1.CAR_ID = frequent_cars.CAR_ID
where DATE_FORMAT(CH1.START_DATE, '%Y')= 2022
and DATE_FORMAT(CH1.START_DATE, '%m') >= 08
and DATE_FORMAT(CH1.START_DATE, '%m') <= 10
group by DATE_FORMAT(CH1.START_DATE, '%m'), CH1.CAR_ID
having count(*) > 0
order by DATE_FORMAT(CH1.START_DATE, '%m') ASC, CH1.CAR_ID DESC