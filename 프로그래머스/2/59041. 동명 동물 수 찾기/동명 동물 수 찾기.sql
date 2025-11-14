-- 코드를 입력하세요
SELECT NAME, count(*)
from ANIMAL_INS
where NOT NAME IS NULL
group by NAME
having count(*) >= 2
order by NAME