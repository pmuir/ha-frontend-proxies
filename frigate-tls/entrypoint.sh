#!/bin/sh

tempio -conf /data/options.json -template /nginx.conf.gtpl -out /tmp/nginx.conf
nginx -T -c /tmp/nginx.conf

echo "nginx.conf"
echo "------------------------------"
cat /tmp/nginx.conf
echo "------------------------------"

exec nginx -c /tmp/nginx.conf