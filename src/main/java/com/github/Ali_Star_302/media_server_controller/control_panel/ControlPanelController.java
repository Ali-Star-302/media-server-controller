package com.github.Ali_Star_302.media_server_controller.control_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControlPanelController {

    private final ControlPanelService panelService;

    @Autowired
    public ControlPanelController(ControlPanelService panelService) {
        this.panelService = panelService;
    }

    @GetMapping("/control-panel")
    public String controlPanel() {
        return "control-panel";
    }

    @PostMapping("/control-panel")
    public String mediaServerBtnPress() {
        panelService.toggleMediaServer();
        return "control-panel";
    }
}
