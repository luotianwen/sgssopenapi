package com.demo.common;

import java.util.ArrayList;
import java.util.List;

public class BackData {
    String error_code;
    String error_info;
    int total;
    List<Stock> rows=new ArrayList<>();

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Stock> getRows() {
        return rows;
    }

    public void setRows(List<Stock> rows) {
        this.rows = rows;
    }
}
