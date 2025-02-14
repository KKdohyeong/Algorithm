-- 코드를 작성해주세요
select HD.DEPT_ID, HD.DEPT_NAME_EN, round(avg(HE.SAL), 0) as 'AVG_SAL'
from HR_DEPARTMENT as HD inner join HR_EMPLOYEES as HE
on HD.DEPT_ID = HE.DEPT_ID
group by HD.DEPT_ID
order by round(avg(HE.SAL), 0) DESC
