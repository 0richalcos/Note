package com.orichalcos.markdownUtils;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class LoadOptions {
    /**
     * 加载忽略清单
     *
     * @param ignoreListPath 忽略清单文件路径
     * @return 忽略清单列表
     */
    public static List<String> loadIgnoreList(String ignoreListPath) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(ignoreListPath)));
            return JSONArray.parseArray(jsonContent, String.class);
        } catch (IOException e) {
            System.err.println("加载忽略清单时出错: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
