-- 코드를 입력하세요
with book_author as(
    select BK.*, AT.AUTHOR_NAME
    from BOOK as BK left join AUTHOR as AT
    on BK.AUTHOR_ID = AT.AUTHOR_ID
),
AUTHOR_CATEGORY_BOOKID as(
    select ba.AUTHOR_ID as AUTHOR_ID, ba.AUTHOR_NAME ,ba.CATEGORY as CATEGORY, sum(BS.SALES) * ba.PRICE as TOTAL_SALES
    from book_author as ba left join BOOK_SALES as BS
    on ba.BOOK_ID = BS.BOOK_ID
    where DATE_FORMAT(BS.SALES_DATE, '%Y-%m') = '2022-01'
    group by ba.AUTHOR_ID, ba.CATEGORY, ba.BOOK_ID 
)
select ACB.AUTHOR_ID, ACB.AUTHOR_NAME, ACB.CATEGORY, SUM(ACB.TOTAL_SALES) as TOTAL_SALES
from AUTHOR_CATEGORY_BOOKID as ACB
group by ACB.AUTHOR_ID, ACB.CATEGORY
order by ACB.AUTHOR_ID ASC, ACB.CATEGORY DESC