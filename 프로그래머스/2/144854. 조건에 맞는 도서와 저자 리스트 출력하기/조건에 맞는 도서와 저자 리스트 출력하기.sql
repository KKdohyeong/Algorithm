-- 코드를 입력하세요
--- (1) 경제 카테고리를 찾아야함
--- 경제 카토리의 author name이 필요한거임, 이건 author_id로 얻어오자
--- null이 있는가? 없다고 생각해도 좋을거같다.
SELECT B.BOOK_ID, A.AUTHOR_NAME, TO_CHAR(B.PUBLISHED_DATE, 'YYYY-MM-DD')
from BOOK B join AUTHOR A on B.AUTHOR_ID = A.AUTHOR_ID 
where B.CATEGORY = '경제'
order by B.PUBLISHED_DATE ASC