package cn.aftertomorrow.util;

import cn.aftertomorrow.po.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class SerachResponse {
    private List<SearchItem> searchItems = new ArrayList<>();

    public List<SearchItem> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    @Override
    public String toString() {
        return "SerachResponse{" +
                "searchItems=" + searchItems +
                '}';
    }
}


