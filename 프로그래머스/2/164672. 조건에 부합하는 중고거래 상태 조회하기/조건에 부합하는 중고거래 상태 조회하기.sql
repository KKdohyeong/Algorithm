SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
    case 
        when STATUS = 'DONE' then '거래완료'
        when STATUS = 'RESERVED' then '예약중'
        when STATUS = 'SALE' then '판매중'
    end as STATUS
from USED_GOODS_BOARD
where TO_CHAR(CREATED_DATE, 'YYYY-MM-DD') = '2022-10-05'
order by BOARD_ID DESC