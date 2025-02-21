-- 코드를 입력하세요
SELECT concat('/home/grep/src/', UGF.BOARD_ID,'/' ,UGF.FILE_ID,UGF.FILE_NAME ,UGF.FILE_EXT) as 'FILE_PATH'
from USED_GOODS_BOARD as UGB left join USED_GOODS_FILE as UGF
on UGB.BOARD_ID = UGF.BOARD_ID
where UGB.VIEWS = (
    select max(VIEWS)
    from USED_GOODS_BOARD
)
order by UGF.FILE_ID DESC