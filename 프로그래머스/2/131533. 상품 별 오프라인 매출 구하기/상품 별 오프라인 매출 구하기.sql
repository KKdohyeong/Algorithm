-- 코드를 입력하세요
SELECT PD.PRODUCT_CODE, SUM(PD.PRICE * OS.SALES_AMOUNT) as total
FROM OFFLINE_SALE as OS RIGHT JOIN PRODUCT as PD 
on OS.PRODUCT_ID = PD.PRODUCT_ID 
GROUP BY OS.PRODUCT_ID
ORDER BY total DESC , PD.PRODUCT_CODE ASC