package com.example.stack_test.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.text.DecimalFormat;
@EnableScheduling
@Service
public class memoryService {

    public static GlobalMemory getMemory() {
        SystemInfo si = new SystemInfo();
        final HardwareAbstractionLayer hw = si.getHardware();

        return hw.getMemory();
    }

    public static int bytesToMegabytes(long bytes) {
        return (int) (bytes / (1024 * 1024));
    }

    public static String bytesToGigabytes(long bytes) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        double gigaBytes =  (double) bytes / (1024 * 1024 * 1024);
        return decimalFormat.format(gigaBytes);
    }

    @Scheduled(fixedDelay = 5000)
    public static void getTotalMemory() {
        long totalMemoryBytes = getMemory().getTotal();
        int totalMemoryMegabytes = bytesToMegabytes(totalMemoryBytes);
        String totalMemoryGigabytes = bytesToGigabytes(totalMemoryBytes);
        System.out.println(totalMemoryMegabytes);
        System.out.println(totalMemoryGigabytes);
    }

    @Scheduled(fixedDelay = 5000)
    public static void getAvailableMemory() {
        long availableMemoryBytes = getMemory().getAvailable();
        int availableMemoryMegabytes = bytesToMegabytes(availableMemoryBytes);
        String availableGigaBytes = bytesToGigabytes(availableMemoryBytes);
        System.out.println(availableMemoryMegabytes);
        System.out.println(availableGigaBytes);
    }
}
