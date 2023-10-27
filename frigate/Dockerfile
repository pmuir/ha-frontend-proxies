FROM nginx

# Copy root filesystem
ADD nginx.conf.gtpl /
ADD entrypoint.sh /
RUN chmod a+rwx entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
