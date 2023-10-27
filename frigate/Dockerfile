FROM nginx

# Copy root filesystem
ADD proxy.conf.gtpl /
ADD entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]
