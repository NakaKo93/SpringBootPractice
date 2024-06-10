FROM mysql:8.0.36-debian

# 日本語化
RUN apt-get update && \
    apt-get install -y locales && \
    sed -i -E 's/# (ja_JP.UTF-8)/\1/' /etc/locale.gen && \
    locale-gen && \
    update-locale LANG=ja_JP.UTF-8 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ENV LC_ALL ja_JP.UTF-8
COPY docker/mySQL/my.cnf /etc/mysql/conf.d/my.cnf

ENV TZ "Asia/Tokyo"

# SQLファイルをコンテナ内にコピー
COPY ./db/init /docker-entrypoint-initdb.d

CMD ["mysqld"]