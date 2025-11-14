-- 코드를 입력하세요 1번이라도 10월에 대여 시작한거 먼저 구하자

with car_ten_one as(
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where TO_CHAR(START_DATE, 'MM') = '10'
)

select distinct CTO.CAR_ID
from car_ten_one CTO join CAR_RENTAL_COMPANY_CAR CRCC on CTO.CAR_ID = CRCC.CAR_ID
where CRCC.CAR_TYPE = '세단'
order by CTO.CAR_ID DESC