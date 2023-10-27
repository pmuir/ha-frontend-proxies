FROM zigbee2mqtt/zigbee2mqtt-proxy-aarch64

# Copy root filesystem
ADD nginx.conf.gtpl /
RUN ln -sf /dev/stdout /var/log/nginx/access.log && ln -sf /dev/stderr /var/log/nginx/error.log
ENTRYPOINT ["/entrypoint.sh"]
