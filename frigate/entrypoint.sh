#!/bin/sh

tempio -conf /data/options.json -template /nginx.conf.gtpl -out /etc/nginx/conf.d/proxy.conf
nginx -T -c  /etc/nginx/conf.d/proxy.conf

exec /docker-entrypoint.sh