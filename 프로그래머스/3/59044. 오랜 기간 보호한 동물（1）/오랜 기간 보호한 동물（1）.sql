SELECT 
    AI.NAME,
    AI.DATETIME
FROM ANIMAL_INS AS AI
LEFT JOIN ANIMAL_OUTS AS AO
       ON AI.ANIMAL_ID = AO.ANIMAL_ID
WHERE AO.ANIMAL_ID IS NULL                -- 입양(ANIMAL_OUTS)에 기록이 없는 동물만
ORDER BY AI.DATETIME ASC                  -- 보호 시작일이 가장 오래된 순(오름차순)
LIMIT 3;                                  -- 그중 상위 3마리
