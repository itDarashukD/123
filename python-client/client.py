import grpc
import PingPongService_pb2
import PingPongService_pb2_grpc


def run():
    print("Python client starting ...")
    with grpc.insecure_channel("localhost:9090") as channel:
        stub = PingPongService_pb2_grpc.PingPongServiceStub(channel)
        request = PingPongService_pb2.SayPingRequest(pingWord="Ping...", gameName="small tennis")
        response = stub.say(request)
    print("responce from Python client: " + response.gameDescription + ", pongResponse " + response.pongWord)


run()
