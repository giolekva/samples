# Compile proto definitions for Go, JS and gRPC-web.
protoc \
    --go_out=plugins=grpc:. \
    --js_out=import_style=commonjs:. \
    --grpc-web_out=import_style=commonjs,mode=grpcwebtext:. \
    *.proto

# Craete Docker image for webpack compilation tool.
docker build -f=Dockerfile.webpack --tag=webpack-compiler .
# Compile client.js with grpc-web lib using webpack-compiler built above.
# Uses package.json for dependency resolution.
docker run -v $(pwd):/app webpack-compiler \
       npx webpack client.js

# Run gRPC server on host machine
# NOTE, same port must be configured in envoy.yaml file mentioned below.
go run *.go \
   --port=8081 &

# Run Envoy gRPC-web proxy in a Docker
# We are using host.docker.internal hastname provided by Docker,
# so Envoy proxy running as a container can reach a gRPC server started above.
# For more details see envoy.yaml
#
# Mounts envoy.yaml into container as a /etc/envoy.yaml
# Exposes container 9901 and 8080 ports
docker run \
       -v $(pwd)/envoy.yaml:/etc/envoy.yaml \
       -p 9901:9901 \
       -p 8080:8080 \
       envoyproxy/envoy-alpine:v1.14.1 \
       /usr/local/bin/envoy -c /etc/envoy.yaml &

# Open gRPC client
open client.html
