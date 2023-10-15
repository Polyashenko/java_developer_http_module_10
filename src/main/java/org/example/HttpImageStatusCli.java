package org.example;

import org.example.HttpStatusImageDownloader;

import java.util.Scanner;

public class HttpImageStatusCli {
    private HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter HTTP status code: ");
        if (scanner.hasNextInt()) {
            int code = scanner.nextInt();
            try {
                downloader.downloadStatusImage(code);
            } catch (Exception e) {
                System.err.println("Помилка: " + e.getMessage());
            }
        } else {
            System.err.println("Please enter a valid number");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}