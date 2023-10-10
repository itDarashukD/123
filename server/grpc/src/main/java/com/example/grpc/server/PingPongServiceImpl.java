package com.example.grpc.server;

import com.protocol.grpc.PingPongServiceGrpc.PingPongServiceImplBase;
import com.protocol.grpc.PingPongServiceOuterClass.SayPingRequest;
import com.protocol.grpc.PingPongServiceOuterClass.SayPongResponse;
import io.grpc.stub.StreamObserver;

public class PingPongServiceImpl extends PingPongServiceImplBase {

    @Override
    public void say(SayPingRequest request, StreamObserver<SayPongResponse> responseObserver) {
        System.out.println(request);

        SayPongResponse response = SayPongResponse.newBuilder()
                .setPongWord("the request :"+request.getPingWord() + "Pong")
                .setGameDescription("the description for game : " + request.getGameName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
