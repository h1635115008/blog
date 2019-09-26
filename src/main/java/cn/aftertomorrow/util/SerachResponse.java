package cn.aftertomorrow.util;

import cn.aftertomorrow.po.SerachItem;

import java.util.ArrayList;
import java.util.List;

public class SerachResponse {
    private List<SerachItem> serachItems = new ArrayList<>();

    public List<SerachItem> getSerachItems() {
        return serachItems;
    }

    public void setSerachItems(List<SerachItem> serachItems) {
        this.serachItems = serachItems;
    }

    @Override
    public String toString() {
        return "SerachResponse{" +
                "serachItems=" + serachItems +
                '}';
    }
}


