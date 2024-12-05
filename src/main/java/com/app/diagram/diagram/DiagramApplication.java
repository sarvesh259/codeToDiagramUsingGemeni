package com.app.diagram.diagram;

import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

@SpringBootApplication

class DiagramApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiagramApplication.class, args);
    }
}