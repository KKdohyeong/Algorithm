-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME,
    case
        when REGEXP_LIKE(SEX_UPON_INTAKE, 'Spayed|Neutered')
        then 'O'
        else 'X'
        end as "중성화"
from ANIMAL_INS
order by ANIMAL_ID