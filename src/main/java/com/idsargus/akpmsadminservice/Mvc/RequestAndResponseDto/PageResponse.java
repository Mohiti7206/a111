package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

public class PageResponse {
    private int size;
    private long totalElements;
    private int totalPages;
    private int number;
    // Getters and Setters
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}






