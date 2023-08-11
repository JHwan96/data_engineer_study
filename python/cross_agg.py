import pandas as pd
df1 = pd.read_excel(u'판매 데이터.xlsx', u'판매이력')
df2 = pd.read_excel(u'판매 데이터.xlsx', u'상품')

# 테이블 결합
df3 = pd.merge(df1, df2, on=u'상품ID')
print(df3.head(3))

# 크로스 집계
print('\n')
df4 =df3.pivot_table(u'금액',[u'점포ID', u'상품명'], u'매출일', aggfunc='sum')
print(df4)

# 카테고리 
print('\n')
def category(row):
    return {101:u'식료품'}.get(row[u'상품ID'], u'그 외')
df1[u'상품 카테고리']=df1.apply(category, axis=1)

print(df1)
