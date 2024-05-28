-- 코드를 작성해주세요

# ID, 대장균 개체의 ID
# PARENT_ID, 부모 개체의 ID
# SIZE_OF_COLONY, 개체의 크기
# DIFFERENTIATION_DATE, 분화되어 나온 날짜
# GENOTYPE, 개체의 형질

# GENOTYPE 이 2가 아니고 1번 또는 3번인 경우의 수

select COUNT(*) as COUNT
from ECOLI_DATA d
where (not GENOTYPE & 2) and (GENOTYPE & 1 or GENOTYPE & 4)
;

