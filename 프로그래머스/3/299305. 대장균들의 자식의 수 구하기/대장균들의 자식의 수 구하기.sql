-- 코드를 작성해주세요
select ED.ID, count(ED1.PARENT_ID) as 'CHILD_COUNT'
from ECOLI_DATA as ED LEFT JOIN ECOLI_DATA as ED1
on ED.ID = ED1.PARENT_ID
group by ED.ID
order by ED.ID ASC