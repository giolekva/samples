# Compile proto definitions for Go, JS and gRPC-web.
protoc \
    --go_out=plugins=grpc:. \ # Compiles proto/service definitions for Go
    --js_out=import_style=commonjs:. \ # Compiles proto definitions for JS
    --grpc-web_out=import_style=commonjs,mode=grpcwebtext:. \ # Compiles service definitions for JS
    *.proto

# Craete Docker image for webpack compilation tool.
docker build -f=Dockerfile.webpack --tag=webpack-compiler .
# Compile client.js with grpc-web lib using webpack-compiler built above.
# Uses package.json for dependency resolution.
docker run -v $(pwd):/app webpack-compiler \
       npx webpack client.js # webpack command line compiling client.js with dependencies

# Run gRPC server on host machine
go run *.go \
   --port=8081 & # NOTE, same port must be configured in envoy.yaml file mentioned below.

# Run Envoy gRPC-web proxy in a Docker
# We are using host.docker.internal hastname provided by Docker,
# so Envoy proxy running as a container can reach a gRPC server started above.
# For more details see envoy.yaml
docker run \
       -v $(pwd)/envoy.yaml:/etc/envoy.yaml \ # Mounts envoy.yaml into container as a /etc/envoy.yaml
       -p 9901:9901 \ # Exposes container 9901 and 8080 ports
       -p 8080:8080 \
       envoyproxy/envoy-alpine:v1.14.1 \ # Container image name
       /usr/local/bin/envoy -c /etc/envoy.yaml & # Command line starting Envoy proxy

# Open gRPC client
open client.html
