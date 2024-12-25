package com.orichalcos.markdownUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * *********************************************
 * 用于删除 !assets/<md文件名> 文件夹下未引用的图片 **
 * *********************************************
 */
public class MarkdownImageDeleter {
    public static void main(String[] args) {
        // 设置文件路径
        String markdownDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown";
        String assetsDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown\\!assets";
        String ignoreListPath = "src/main/resources/ignoreList.json";

        // 加载忽略清单
        List<String> ignoreList = LoadOptions.loadIgnoreList(ignoreListPath);

        // 遍历 Markdown 文件并删除未引用的图片
        deleteUnusedImages(Paths.get(markdownDirPath), Paths.get(assetsDirPath), ignoreList);

        System.out.println("==========================");
        System.out.println("===========完成============");
        System.out.println("==========================");
    }

    /**
     * 删除未被 Markdown 文件引用的图片
     *
     * @param markdownDir   Markdown 文件目录路径
     * @param assetsBaseDir 资源基础目录
     * @param ignoreList    忽略清单
     */
    private static void deleteUnusedImages(Path markdownDir, Path assetsBaseDir, List<String> ignoreList) {
        try {
            Files.walk(markdownDir).filter(file -> file.toString().endsWith(".md")).forEach(markdownFile -> {
                deleteUnusedImagesForMarkdownFile(markdownFile, assetsBaseDir, ignoreList);
            });
        } catch (IOException e) {
            System.err.println("读取 Markdown 文件目录时出错: " + e.getMessage());
        }
    }

    /**
     * 检查单个 Markdown 文件的图片引用并删除未被引用的图片
     *
     * @param markdownFile  Markdown 文件路径
     * @param assetsBaseDir 资源基础目录
     * @param ignoreList    忽略清单
     */
    private static void deleteUnusedImagesForMarkdownFile(Path markdownFile, Path assetsBaseDir, List<String> ignoreList) {
        Pattern imgPattern = Pattern.compile("!\\[(.*?)\\]\\((.*?)\\)|<img\\s+.*?src=\\\"(.*?)\\\".*?>");
        Path targetDir = assetsBaseDir.resolve(markdownFile.getFileName().toString().replace(".md", ""));

        // 记录 Markdown 文件中引用的所有图片
        Set<String> referencedImages = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(markdownFile.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = imgPattern.matcher(line);

                while (matcher.find()) {
                    String imgPath = matcher.group(2) != null ? matcher.group(2) : matcher.group(3);

                    if (imgPath != null && !ignoreList.contains(imgPath)) {
                        Path imgFile = Paths.get(imgPath).getFileName();
                        if (imgFile != null) {
                            referencedImages.add(imgFile.toString());
                        }
                    }
                }
            }

            // 检查目标文件夹下的图片是否未被引用
            if (Files.exists(targetDir) && Files.isDirectory(targetDir)) {
                Files.list(targetDir).forEach(file -> {
                    if (!referencedImages.contains(file.getFileName().toString())) {
                        try {
                            Files.delete(file);
                            System.out.println("删除未引用的图片: " + file);
                        } catch (IOException e) {
                            System.err.println("删除图片时出错: " + file + ", " + e.getMessage());
                        }
                    }
                });
            }

        } catch (IOException e) {
            System.err.println("处理 Markdown 文件时出错: " + markdownFile + ", " + e.getMessage());
        }
    }
}
