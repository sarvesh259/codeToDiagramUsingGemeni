package com.app.diagram.diagram.service;

import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

class GenerateDiagram {



    public String diagram(String plantUMLCode) {
        String base64Diagram = generateDiagram(plantUMLCode);

        if (base64Diagram != null) {
            System.out.println("Diagram generated successfully (Base64):");
            System.out.println(base64Diagram);
            return base64Diagram;
        } else {
            System.out.println("Failed to generate the diagram.");
            return null;
        }
    }

    public static String generateDiagram(String plantUMLCode) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            SourceStringReader reader = new SourceStringReader(plantUMLCode);
            // Generate the diagram and write it to the output stream
            reader.generateImage(outputStream);

            // Convert the output stream to a byte array
            byte[] imageBytes = outputStream.toByteArray();

            // Encode the byte array to a Base64 string
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}