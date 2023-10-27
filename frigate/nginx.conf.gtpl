server {
    listen 5000 default_server;

    root /dev/null;
    server_name _;

    location / {
        allow   172.30.32.2;
        deny    all;

        proxy_pass                  {{ .server }};
        proxy_set_header Authorization "Basic {{ .basic_auth }}";
        
        proxy_http_version          1.1;
        proxy_ignore_client_abort   off;
        proxy_read_timeout          86400s;
        proxy_redirect              off;
        proxy_max_temp_file_size    0;

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

