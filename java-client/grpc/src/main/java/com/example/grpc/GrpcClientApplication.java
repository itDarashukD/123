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
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9090")  //server location + port
                        .usePlaintext()
                        .build();

        PingPongServiceGrpc.PingPongServiceBlockingStub stub =			//stub == object that can go to server and invoke method say
	       PingPongServiceGrpc.newBlockingStub(channel);

        PingPongServiceOuterClass.SayPingRequest request =			//create request which server going to use
	       PingPongServiceOuterClass.SayPingRequest.newBuilder()
		      .setPingWord("Ping...")
		      .setGameName("tennis")
		      .build();

        final SayPongResponse response = stub.say(request);			//response returned from server
        System.out.println(response);
        channel.shutdown();

    }

}
