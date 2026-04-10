select BOARD_ID, WRITER_ID, TITLE, PRICE, case 
when STATUS='DONE' then '거래완료'
when STATUS='SALE' then '판매중'
else '예약중'
end as STATUS
from USED_GOODS_BOARD
where date_format(CREATED_DATE, '%Y-%m-%d') = '2022-10-05'
order by BOARD_ID DESC