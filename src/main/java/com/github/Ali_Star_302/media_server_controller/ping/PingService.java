package com.github.Ali_Star_302.media_server_controller.ping;

import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class PingService implements PingServicer {
    @Override
    public boolean sendPing(String ip, int timeoutMSecs) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            return address.isReachable(timeoutMSecs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
