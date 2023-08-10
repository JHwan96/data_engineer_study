import re
import pandas as pd

pattern = re.compile('^\S+ \S+ \S+ \[(.*)\] "(.*)" (\S+) (\S+)$')

def parse_access_log(path):
    for line in open(path, 'r', encoding='ANSI'):
        for m in pattern.finditer(line):
            yield m.groups()


columns = ['time','request','status','bytes']
df = pd.DataFrame(parse_access_log('NASA_access_log_Jul95.log'), columns=columns)
df.time = pd.to_datetime(df.time, format='%d/%b/%Y:%X', exact=False)

df.to_csv('access_log.csv',index=False)


print(df.head(2))