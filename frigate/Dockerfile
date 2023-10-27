FROM nginx

# Copy root filesystem
ADD nginx.conf.gtpl /
ADD entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]
