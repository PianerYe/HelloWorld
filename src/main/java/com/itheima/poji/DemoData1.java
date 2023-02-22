package com.itheima.poji;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DemoData1 {
    @ExcelProperty("姓名")
    private String name;
    private Integer age;
    private Double price;
}
