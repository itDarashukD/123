package com.example.grpc.server;

import com.protocol.grpc.PingPongServiceGrpc.PingPongServiceImplBase;
import com.protocol.grpc.PingPongServiceOuterClass.SayPingRequest;
import com.protocol.grpc.PingPongServiceOuterClass.SayPongResponse;
import io.grpc.stub.StreamObserver;

public class PingPongServiceImpl extends PingPongServiceImplBase {

    @Override
    public void say(SayPingRequest request, StreamObserver<SayPongResponse> responseObserver) {     //request from client
        System.out.println(request);

        SayPongResponse response = SayPongResponse.newBuilder()                         //configuring response to client with using some fields from client
                .setPongWord("the request :"+request.getPingWord() + "Pong")
                .setGameDescription("the description for game : " + request.getGameName())
                .build();

        responseObserver.onNext(response);          //sent response callS to client
        responseObserver.onCompleted();
    }

}
