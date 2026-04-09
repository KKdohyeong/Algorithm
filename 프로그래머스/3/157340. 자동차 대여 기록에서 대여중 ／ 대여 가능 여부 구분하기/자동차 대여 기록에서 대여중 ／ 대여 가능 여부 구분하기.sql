/*
2022.10.16이면 대여중
아니면 대여 가능
칼럼명 : AVAILBILITY

BETWEEN을쓰거나 머 그런식으로 하고싶네. 
불가능이 하나라도 잇는 놈을 찾으면 돼
반납이 2022.10.16도 불가능이야.
*/
with RENT_NO as (
    select distinct CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where START_DATE <= '2022-10-16' AND END_DATE >= '2022-10-16'
)

select distinct CAR_ID,     
case 
    when CAR_ID in (select CAR_ID from RENT_NO) then '대여중'
    else '대여 가능'
end as 'AVAILABILITY'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
order by CAR_ID DESC