-- 코드를 입력하세요
SELECT RI.REST_ID, RI.REST_NAME, RI.FOOD_TYPE, RI.FAVORITES, RI.ADDRESS, round(avg(RR.REVIEW_SCORE), 2) as 'SCORE'
from REST_INFO as RI inner join REST_REVIEW as RR
on RI.REST_ID = RR.REST_ID
where RI.ADDRESS like '%서울시%' or RI.ADDRESS like '%서울특별시%'
group by RI.REST_ID
order by round(avg(RR.REVIEW_SCORE), 2) DESC, RI.FAVORITES DESC