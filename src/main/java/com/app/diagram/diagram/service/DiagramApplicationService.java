package com.app.diagram.diagram.service;


import com.app.diagram.diagram.model.RequestBody;
import com.app.diagram.diagram.model.ResponseBody;
import org.springframework.stereotype.Service;

@Service
public class DiagramApplicationService {
    public ResponseBody processRecord(RequestBody requestBody){
        String prompt="Generate a PlantUML flow diagram of the following Java code. Only return the PlantUML code between @startuml and @enduml, with no explanation or additional text. Make sure the flow accurately represents the logic of the code, including decisions, loops, and method calls\n"+requestBody.getCode();
        GeminiAPICaller geminiAPICaller=new GeminiAPICaller();
        String plantUMLCode=geminiAPICaller.geminiCall(prompt);
        GenerateDiagram generateDiagram=new GenerateDiagram();
        ResponseBody responseBody=new ResponseBody();
        responseBody.setDiagram(generateDiagram.diagram(plantUMLCode));
        return responseBody;
    }
}
