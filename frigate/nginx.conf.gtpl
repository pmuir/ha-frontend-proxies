

# Run nginx in foreground.
daemon off;

# This is run inside Docker.
user nginx;

# Pid storage location.
pid /var/run/nginx.pid;

# Set number of worker processes.
worker_processes 1;

# Enables the use of JIT for regular expressions to speed-up their processing.
pcre_jit on;

# Write log to the add-on log.
error_log  /tmp/error.log debug;

# Max num of simultaneous connections by a worker process.
events {
    worker_connections 512;
}

http {
    access_log  /tmp/access.log;
    client_max_body_size    4G;
    default_type            application/octet-stream;
    gzip                    on;
    keepalive_timeout       65;
    sendfile                off;
    server_tokens           off;
    tcp_nodelay             on;
    tcp_nopush              on;

    types {
        text/html                                        html htm shtml;
        text/css                                         css;
        text/xml                                         xml;
        image/gif                                        gif;
        image/jpeg                                       jpeg jpg;
        application/javascript                           js;
        application/atom+xml                             atom;
        application/rss+xml                              rss;

        text/mathml                                      mml;
        text/plain                                       txt;
        text/vnd.sun.j2me.app-descriptor                 jad;
        text/vnd.wap.wml                                 wml;
        text/x-component                                 htc;

        image/png                                        png;
        image/svg+xml                                    svg svgz;
        image/tiff                                       tif tiff;
        image/vnd.wap.wbmp                               wbmp;
        image/webp                                       webp;
        image/x-icon                                     ico;
        image/x-jng                                      jng;
        image/x-ms-bmp                                   bmp;

        font/woff                                        woff;
        font/woff2                                       woff2;

        application/java-archive                         jar war ear;
        application/json                                 json;
        application/mac-binhex40                         hqx;
        application/msword                               doc;
        application/pdf                                  pdf;
        application/postscript                           ps eps ai;
        application/rtf                                  rtf;
        application/vnd.apple.mpegurl                    m3u8;
        application/vnd.google-earth.kml+xml             kml;
        application/vnd.google-earth.kmz                 kmz;
        application/vnd.ms-excel                         xls;
        application/vnd.ms-fontobject                    eot;
        application/vnd.ms-powerpoint                    ppt;
        application/vnd.oasis.opendocument.graphics      odg;
        application/vnd.oasis.opendocument.presentation  odp;
        application/vnd.oasis.opendocument.spreadsheet   ods;
        application/vnd.oasis.opendocument.text          odt;
        application/vnd.openxmlformats-officedocument.presentationml.presentation
                                                        pptx;
        application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
                                                        xlsx;
        application/vnd.openxmlformats-officedocument.wordprocessingml.document
                                                        docx;
        application/vnd.wap.wmlc                         wmlc;
        application/x-7z-compressed                      7z;
        application/x-cocoa                              cco;
        application/x-java-archive-diff                  jardiff;
        application/x-java-jnlp-file                     jnlp;
        application/x-makeself                           run;
        application/x-perl                               pl pm;
        application/x-pilot                              prc pdb;
        application/x-rar-compressed                     rar;
        application/x-redhat-package-manager             rpm;
        application/x-sea                                sea;
        application/x-shockwave-flash                    swf;
        application/x-stuffit                            sit;
        application/x-tcl                                tcl tk;
        application/x-x509-ca-cert                       der pem crt;
        application/x-xpinstall                          xpi;
        application/xhtml+xml                            xhtml;
        application/xspf+xml                             xspf;
        application/zip                                  zip;

        application/octet-stream                         bin exe dll;
        application/octet-stream                         deb;
        application/octet-stream                         dmg;
        application/octet-stream                         iso img;
        application/octet-stream                         msi msp msm;

        audio/midi                                       mid midi kar;
        audio/mpeg                                       mp3;
        audio/ogg                                        ogg;
        audio/x-m4a                                      m4a;
        audio/x-realaudio                                ra;

        video/3gpp                                       3gpp 3gp;
        video/mp2t                                       ts;
        video/mp4                                        mp4;
        video/mpeg                                       mpeg mpg;
        video/quicktime                                  mov;
        video/webm                                       webm;
        video/x-flv                                      flv;
        video/x-m4v                                      m4v;
        video/x-mng                                      mng;
        video/x-ms-asf                                   asx asf;
        video/x-ms-wmv                                   wmv;
        video/x-msvideo                                  avi;
    }


    map $http_upgrade $connection_upgrade {
        default upgrade;
        ''      close;
    }
    resolver 127.0.0.11 ipv6=off;

    server {
        listen 8099 default_server;

        root /dev/null;
        server_name $hostname;

        add_header X-Content-Type-Options nosniff;
        add_header X-XSS-Protection "1; mode=block";
        add_header X-Robots-Tag none;

        location / {
            allow   172.30.32.2;
            deny    all;

            proxy_pass                  {{ .server }};
            # proxy_set_header Authorization "Basic {{ .basic_auth }}";
            
            proxy_http_version          1.1;
            proxy_ignore_client_abort   off;
            proxy_read_timeout          86400s;
            proxy_redirect              off;
            proxy_max_temp_file_size    0;
            proxy_send_timeout          86400s;

            proxy_set_header Accept-Encoding "";
            proxy_set_header Connection $connection_upgrade;
            proxy_set_header Host $http_host;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
            proxy_set_header X-NginX-Proxy true;
            proxy_set_header X-Real-IP $remote_addr;

        }
    }
}

