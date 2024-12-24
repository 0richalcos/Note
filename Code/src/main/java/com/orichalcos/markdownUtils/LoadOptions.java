package com.orichalcos.markdownUtils;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadOptions {
    /**
     * 加载忽略清单
     *
     * @param ignoreListPath 忽略清单文件路径
     * @return 忽略清单列表
     */
    public static List<String> loadIgnoreList(String ignoreListPath) {
        List<String> ignoreList = new ArrayList<>();
        try (InputStream is = new FileInputStream(ignoreListPath);
             JsonReader reader = Json.createReader(is)) {

            JsonArray jsonArray = reader.readArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                ignoreList.add(jsonArray.getString(i));
            }
        } catch (IOException e) {
            System.err.println("加载忽略清单时出错: " + e.getMessage());
        }
        return ignoreList;
    }
}
