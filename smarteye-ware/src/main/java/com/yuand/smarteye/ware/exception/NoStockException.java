package com.yuand.smarteye.ware.exception;

public class NoStockException extends RuntimeException {
    public NoStockException() {
        super("库存不足");
    }
}
