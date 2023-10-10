package com.example.grpc;

import com.protocol.grpc.PingPongServiceGrpc;
import com.protocol.grpc.PingPongServiceOuterClass;
import com.protocol.grpc.PingPongServiceOuterClass.SayPongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        ManagedChannel channel =
	       ManagedChannelBuilder.forTarget("localhost:9090")
                        .usePlaintext()
                        .build();

        PingPongServiceGrpc.PingPongServiceBlockingStub stub =
	       PingPongServiceGrpc.newBlockingStub(channel);

        PingPongServiceOuterClass.SayPingRequest request =
	       PingPongServiceOuterClass.SayPingRequest.newBuilder()
		      .setPingWord("Ping...")
		      .setGameName("tennis")
		      .build();

        final SayPongResponse response = stub.say(request);
        System.out.println(response);
        channel.shutdown();

    }

}
