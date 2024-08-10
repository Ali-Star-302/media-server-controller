package com.github.Ali_Star_302.media_server_controller.control_panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlPanelController {
    @GetMapping("/control-panel")
    public String controlPanel() {
        return "control-panel";
    }
}
