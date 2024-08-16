package com.github.Ali_Star_302.media_server_controller.control_panel;

import com.github.Ali_Star_302.media_server_controller.ping.PingServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlPanelController {

    private final ControlPanelService panelService;
    private final PingServicer pingService;

    @Value("${mediaServer.address.ip}")
    private final String mediaServerIp;

    @Value("${mediaServer.address.mac}")
    private final String mediaServerMac;

    @Autowired
    public ControlPanelController(ControlPanelService panelService, PingServicer pingService) {
        this.panelService = panelService;
        this.pingService = pingService;
        this.mediaServerIp = "";
        this.mediaServerMac = "";
    }

    @GetMapping("/control-panel")
    public ModelAndView controlPanel() {
        ModelAndView modelAndView = new ModelAndView("control-panel");
        System.out.println(mediaServerIp);
        modelAndView.addObject("awake", this.pingService.sendPing(mediaServerIp, 1000));
        modelAndView.addObject("isWaking", this.panelService.isWaking());

        return modelAndView;
    }

    @PostMapping("/control-panel")
    public ModelAndView mediaServerBtnPress() {
        panelService.wakePc(mediaServerIp, mediaServerMac);
        ModelAndView modelAndView = new ModelAndView("control-panel");
        System.out.println(mediaServerIp);
        modelAndView.addObject("awake", this.pingService.sendPing(mediaServerIp, 1000));
        modelAndView.addObject("isWaking", this.panelService.isWaking());

        return modelAndView;
    }
}
