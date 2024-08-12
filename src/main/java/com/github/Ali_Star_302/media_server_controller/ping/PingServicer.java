package com.github.Ali_Star_302.media_server_controller.ping;

public interface PingServicer {

    boolean sendPing(String ip, int timeoutMSecs);
}
