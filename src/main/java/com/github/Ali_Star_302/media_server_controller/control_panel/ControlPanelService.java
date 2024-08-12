package com.github.Ali_Star_302.media_server_controller.control_panel;

import org.springframework.stereotype.Service;

@Service
public class ControlPanelService {

    public void wakePc(String ipAddr, String macAddr) {
        System.out.println("toggle media server");
        System.out.println("IP: " + ipAddr + ", MAC: " + macAddr);
        //subnet mask: 255.255.255.0
        // remote port: 3389
    }
}
