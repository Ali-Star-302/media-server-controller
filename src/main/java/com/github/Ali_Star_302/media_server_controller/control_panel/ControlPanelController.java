package com.github.Ali_Star_302.media_server_controller.control_panel;

import com.github.Ali_Star_302.media_server_controller.ping.PingServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlPanelController {

    private final ControlPanelService panelService;
    private final PingServicer pingService;
    private final String mediaServerIp;
    private final String mediaServerMac;

    @Autowired
    public ControlPanelController(ControlPanelService panelService, PingServicer pingService) {
        this.panelService = panelService;
        this.pingService = pingService;
        this.mediaServerIp = "192.168.0.0";
        this.mediaServerMac = "8C-89-A5-E7-4D-4C";
//        this.mediaServerIp = "127.0.0.1";
    }

    @GetMapping("/control-panel")
    public ModelAndView controlPanel() {
        ModelAndView modelAndView = new ModelAndView("control-panel");
        modelAndView.addObject("awake", this.pingService.sendPing(mediaServerIp, 1000));

        return modelAndView;
    }

    @PostMapping("/control-panel")
    public ModelAndView mediaServerBtnPress() {
        panelService.wakePc(mediaServerIp, mediaServerMac);
        ModelAndView modelAndView = new ModelAndView("control-panel");
        modelAndView.addObject("awake", this.pingService.sendPing(mediaServerIp, 1000));

        return modelAndView;
    }
}
