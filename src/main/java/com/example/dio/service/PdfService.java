package com.example.dio.service;

import java.io.IOException;
import java.util.Map;

public interface PdfService {
     byte[] generatePdf(String templateName, Map<String,Object> data) throws IOException;
}
