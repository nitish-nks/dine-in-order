package com.example.dio.service;

public interface QRCodeService {
    byte[] generateQRCode(String url) throws Exception;
}
