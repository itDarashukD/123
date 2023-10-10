package com.example.grpc;

import com.example.grpc.server.PingPongServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(GrpcApplication.class, args);

        Server server = ServerBuilder
	       .forPort(9090)
	       .addService(new PingPongServiceImpl())
	       .build();

        System.out.println("start server ...");
        server.start();
        server.awaitTermination();
    }

}
