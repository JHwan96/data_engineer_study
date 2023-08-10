import pandas as pd
df1 = pd.read_csv('access_log.csv', parse_dates=['time'])
df2 = df1.set_index('time')
df3 = df2['1995-07-01' : '1995-07-03']
a = df3.resample('1d').size()
print(a)