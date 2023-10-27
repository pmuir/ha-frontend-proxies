# Home Assistant Add-on: Frigate Proxy

⚠️ This addon does not contain Frigate ⚠️

This addon acts as a proxy to an external running Frigate instance. 
The sole purpose of this addon is to add a Frigate icon to the sidebar of Home Assistant which will open the frontend of an external running Frigate instance.

## Options

- `server` (required): this should be the local URL on which the Rackstation frontend is running, e.g. `http://192.168.2.43:8080`. Make sure there is no trailing slash!
- `basic_auth` (required): base64 encoded `username:password` for basic authentication
