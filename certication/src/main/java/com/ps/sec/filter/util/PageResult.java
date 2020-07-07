package com.ps.sec.filter.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fb
 */
@Data
public class PageResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总条数
     */
    private long count;
    /**
     * 当前页结果数据
     */
    private List data;
    /**
     * 当前页
     */
    private Integer code;
    /**
     * 每页条数
     */
    private String msg;

    public PageResult() {
    }

    public PageResult(long count, List data, Integer code, String msg) {
        this.count = count;
        this.data = data;
        this.code = code;
        this.msg = msg;

    }
    public PageResult(Integer code, String msg) {
        this.code=code;
        this.msg = msg;

    }

    public PageResult(Integer code, List data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     *成功，传入总条数、结果、页码、页数
     */
    public static PageResult ok(long count,List data,Integer code,String msg) {
        return new PageResult(count,data,0,"success");
    }
    /**
     * 成功，传入总条数、结果
     */

    public static PageResult result(Integer code,String msg) {
        return new PageResult(code,msg);
    }
    public static PageResult suc(Integer code,List data,String msg) {
        return new PageResult(code,data,msg);
    }
}
