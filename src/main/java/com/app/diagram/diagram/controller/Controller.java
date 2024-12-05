package com.app.diagram.diagram.controller;

import com.app.diagram.diagram.model.RequestBody;
import com.app.diagram.diagram.model.ResponseBody;
import com.app.diagram.diagram.service.DiagramApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @PostMapping("/createDiagram")
    public ResponseBody createDiagram(RequestBody requestBody){
        DiagramApplicationService diagramApplicationService=new DiagramApplicationService();
        return diagramApplicationService.processRecord(requestBody);
    }
}
