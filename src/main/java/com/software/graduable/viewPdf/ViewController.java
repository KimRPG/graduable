package com.software.graduable.viewPdf;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ViewController {
    @GetMapping("/view-pdf")
    public void viewPdf(HttpServletResponse response) throws IOException {
        // PDF 파일 경로 또는 바이너리 데이터
        ClassPathResource pdfFile = new ClassPathResource("coursehandbook.pdf");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=practice.pdf");
        response.setContentLength((int) pdfFile.contentLength());

        StreamUtils.copy(pdfFile.getInputStream(), response.getOutputStream());
    }
}
