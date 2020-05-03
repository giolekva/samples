package main

// "Auto-imports" api.pb.go as both are in main package
import (
	"context"
	"flag"
	"fmt"
	"log"
	"net"

	"google.golang.org/grpc"
)

var port = flag.Int("port", 8080, "Port to listen on")

type server struct {
}

func (s *server) Hello(ctx context.Context, req *HelloReq) (*HelloResp, error) {
	log.Printf("Received: %v", req.Name)
	return &HelloResp{Message: "Hello " + req.Name + " from gRPC server"}, nil
}

func main() {
	flag.Parse()
	lis, err := net.Listen("tcp", fmt.Sprintf(":%d", *port))
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()
	RegisterHelloServiceServer(s, &server{})
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
