package com.orichalcos.markdownUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.orichalcos.markdownUtils.LoadOptions.loadIgnoreList;

/**
 * 用于检查md文件中是否有未存在的图片
 */
public class MarkdownImageChecker {

    public static void main(String[] args) {
        // 设置文件路径
        String markdownDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown";
        String assetsDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown\\!assets";
        String ignoreListPath = "src/main/resources/ignoreList.json";

        // 获取资源文件目录中所有图片文件
        Set<String> assetFiles = getAssetFiles(Paths.get(assetsDirPath));

        // 加载忽略清单
        List<String> ignoreList = loadIgnoreList(ignoreListPath);

        // 遍历 Markdown 文件并检查图片引用
        processMarkdownFiles(Paths.get(markdownDirPath), assetFiles, ignoreList);

        System.out.println("==========================");
        System.out.println("===========完成============");
        System.out.println("==========================");
    }

    /**
     * 获取资源文件目录中所有图片文件
     *
     * @param assetsDir 资源文件目录路径
     * @return 包含所有图片文件路径的集合
     */
    private static Set<String> getAssetFiles(Path assetsDir) {
        Set<String> assetFiles = new HashSet<>();
        try {
            Files.walk(assetsDir).filter(Files::isRegularFile).forEach(file -> {
                assetFiles.add(file.getFileName().toString());
            });
        } catch (IOException e) {
            System.err.println("读取资源文件目录时出错: " + e.getMessage());
        }
        return assetFiles;
    }

    /**
     * 遍历 Markdown 文件并检查图片引用
     *
     * @param markdownDir Markdown 文件目录路径
     * @param assetFiles  资源文件集合
     * @param ignoreList  忽略的图片引用清单
     */
    private static void processMarkdownFiles(Path markdownDir, Set<String> assetFiles, List<String> ignoreList) {
        try {
            Files.walk(markdownDir).filter(file -> file.toString().endsWith(".md")).forEach(file -> {
                checkMarkdownFile(file, assetFiles, ignoreList);
            });
        } catch (IOException e) {
            System.err.println("读取 Markdown 文件目录时出错: " + e.getMessage());
        }
    }

    /**
     * 检查单个 Markdown 文件中的图片引用
     *
     * @param markdownFile Markdown 文件路径
     * @param assetFiles   资源文件集合
     * @param ignoreList   忽略的图片引用清单
     */
    private static void checkMarkdownFile(Path markdownFile, Set<String> assetFiles, List<String> ignoreList) {
        Pattern imgPattern = Pattern.compile("!\\[.*?\\]\\((.*?)\\)|<img\\s+src=\\\"(.*?)\\\".*?>");

        try (BufferedReader reader = new BufferedReader(new FileReader(markdownFile.toFile()))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (ignoreList.stream().anyMatch(line::contains)) {
                    continue;
                }

                Matcher matcher = imgPattern.matcher(line);
                while (matcher.find()) {
                    String imgPath = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
                    String fileName = extractFileName(imgPath);

                    if (!isImageInAssets(fileName, assetFiles)) {
                        System.out.println("文件: " + markdownFile + " 第" + lineNumber + "行，缺少图片: " + imgPath);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("读取 Markdown 文件时出错: " + markdownFile + ", " + e.getMessage());
        }
    }

    /**
     * 从图片引用路径中提取文件名
     *
     * @param imgPath 图片引用路径
     * @return 文件名
     */
    private static String extractFileName(String imgPath) {
        try {
            return Paths.get(new URI(imgPath).getPath()).getFileName().toString();
        } catch (Exception e) {
            return Paths.get(imgPath).getFileName().toString();
        }
    }

    /**
     * 检查图片引用是否存在于资源文件中
     *
     * @param fileName   图片文件名
     * @param assetFiles 资源文件集合
     * @return 如果图片存在返回 true，否则返回 false
     */
    private static boolean isImageInAssets(String fileName, Set<String> assetFiles) {
        return assetFiles.contains(fileName);
    }
}
