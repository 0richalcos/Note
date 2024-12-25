package com.orichalcos.markdownUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
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

        // 删除未引用的图片和未使用的文件夹
        cleanUnusedAssets(Paths.get(markdownDirPath), Paths.get(assetsDirPath), ignoreList);

        System.out.println("==========================");
        System.out.println("===========完成============");
        System.out.println("==========================");
    }

    /**
     * 清理未引用的资源文件夹及图片
     *
     * @param markdownDir   Markdown 文件目录路径
     * @param assetsBaseDir 资源基础目录
     * @param ignoreList    忽略清单
     */
    private static void cleanUnusedAssets(Path markdownDir, Path assetsBaseDir, List<String> ignoreList) {
        try {
            // 遍历资源文件夹下的所有子文件夹
            Files.list(assetsBaseDir).filter(Files::isDirectory).forEach(subDir -> {
                String folderName = subDir.getFileName().toString();
                Path correspondingMarkdownFile = markdownDir.resolve(folderName + ".md");

                if (!Files.exists(correspondingMarkdownFile)) {
                    // 如果没有对应的 Markdown 文件，删除整个文件夹
                    deleteDirectory(subDir);
                    System.out.println("删除未使用的文件夹: " + subDir);
                } else {
                    // 如果有对应的 Markdown 文件，检查未引用的图片
                    deleteUnusedImagesForMarkdownFile(correspondingMarkdownFile, subDir, ignoreList);
                }
            });
        } catch (IOException e) {
            System.err.println("读取资源文件夹时出错: " + e.getMessage());
        }
    }

    /**
     * 删除未被 Markdown 文件引用的图片
     *
     * @param markdownFile Markdown 文件路径
     * @param targetDir    图片所在的文件夹
     * @param ignoreList   忽略清单
     */
    private static void deleteUnusedImagesForMarkdownFile(Path markdownFile, Path targetDir, List<String> ignoreList) {
        Pattern imgPattern = Pattern.compile("!\\[(.*?)\\]\\((.*?)\\)|<img\\s+.*?src=\\\"(.*?)\\\".*?>");

        // 记录 Markdown 文件中引用的所有图片
        Set<String> referencedImages = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(markdownFile.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // 检查当前行是否在忽略清单中
                if (ignoreList.stream().anyMatch(line::contains)) {
                    continue;
                }

                Matcher matcher = imgPattern.matcher(line);

                while (matcher.find()) {
                    String imgPath = matcher.group(2) != null ? matcher.group(2) : matcher.group(3);

                    if (imgPath != null) {
                        try {
                            Path imgFile = Paths.get(imgPath).getFileName();
                            if (imgFile != null) {
                                referencedImages.add(imgFile.toString());
                            }
                        } catch (InvalidPathException ex) {
                            System.err.println("无效的图片路径: " + imgPath + " 在文件 " + markdownFile + " 中, 错误: " + ex.getMessage());
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

    /**
     * 删除目录及其内容
     *
     * @param directory 目标目录
     */
    private static void deleteDirectory(Path directory) {
        try {
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.err.println("删除文件时出错: " + path + ", " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("删除目录时出错: " + directory + ", " + e.getMessage());
        }
    }
}
