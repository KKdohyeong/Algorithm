-- 코드를 입력하세요 1번이라도 10월에 대여를 시작한 자동차 id를 먼저 구하자
with rental_ten as(
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where DATE_FORMAT(START_DATE, '%m') = '10'
)
SELECT CRCC.CAR_ID
from CAR_RENTAL_COMPANY_CAR as CRCC
where CRCC.CAR_ID in (
    select CAR_ID
    from rental_ten
)
and CAR_TYPE = '세단'
order by CRCC.CAR_ID DESC