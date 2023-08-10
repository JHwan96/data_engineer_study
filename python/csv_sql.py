import pandas as pd
import sqlalchemy

engine = sqlalchemy.create_engine('sqlite:///sample.db')

query = "Select substr(time, 1, 10) time, count(*) count \
        from access_log\
        where time between '1995-07-01' and '1995-07-04'\
        group by 1 order by 1"
print(pd.read_sql(query, engine))