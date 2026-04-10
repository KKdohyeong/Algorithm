select CAR_ID, round(AVG(datediff(END_DATE, START_DATE)+1),1) as 'AVERAGE_DURATION'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
having round(AVG(datediff(END_DATE, START_DATE)+1),1) >=7
order by round(AVG(datediff(END_DATE, START_DATE)+1),1) DESC, CAR_ID DESC