-- 코드를 입력하세요
SELECT fh.FLAVOR
from FIRST_HALF as fh right join ICECREAM_INFO as II
on fh.FLAVOR = II.FLAVOR
where fh.TOTAL_ORDER > 3000 and II.INGREDIENT_TYPE = 'fruit_based'
order by fh.TOTAL_ORDER DESC