package edu.develop.leave.controller.vo;

/**
 * 数据展示控制
 *
 * @anthor zsl
 * @date 2018/08/19
 */
public class LimitVO {

    /**
     * 前端页面的当前页
     */
    private Integer curPage;

    /**
     * 前端每页显示的数据条数
     */
    private Integer limit;

    public Integer getCurPage() {
        return curPage;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "LimitVO{" +
                "curPage=" + curPage +
                ", limit=" + limit +
                '}';
    }
}
