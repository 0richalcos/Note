package com.orichalcos.markdownUtils;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.orichalcos.markdownUtils.LoadOptions.loadIgnoreList;

/**
 * ***********************************************
 * 用于将md文件的图片复制到 !assets/<md文件名> 文件下 **
 * ***********************************************
 */
public class MarkdownImageCopier {

    public static void main(String[] args) {
        // 设置文件路径
        String markdownDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown";
        String assetsDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown\\!assets";
        String ignoreListPath = "src/main/resources/ignoreList.json";

        // 加载忽略清单
        List<String> ignoreList = loadIgnoreList(ignoreListPath);

        // 遍历 Markdown 文件并复制图片引用
        copyImagesFromMarkdown(Paths.get(markdownDirPath), Paths.get(assetsDirPath), ignoreList);

        System.out.println("==========================");
        System.out.println("===========完成============");
        System.out.println("==========================");
    }

    /**
     * 遍历 Markdown 文件并复制图片引用到目标文件夹
     *
     * @param markdownDir   Markdown 文件目录路径
     * @param assetsBaseDir 资源基础目录
     * @param ignoreList    忽略清单
     */
    private static void copyImagesFromMarkdown(Path markdownDir, Path assetsBaseDir, List<String> ignoreList) {
        try {
            Files.walk(markdownDir).filter(file -> file.toString().endsWith(".md")).forEach(markdownFile -> {
                copyImagesForMarkdownFile(markdownFile, assetsBaseDir, ignoreList);
            });
        } catch (IOException e) {
            System.err.println("读取 Markdown 文件目录时出错: " + e.getMessage());
        }
    }

    /**
     * 为单个 Markdown 文件复制图片引用并更新路径
     *
     * @param markdownFile  Markdown 文件路径
     * @param assetsBaseDir 资源基础目录
     * @param ignoreList    忽略清单
     */
    private static void copyImagesForMarkdownFile(Path markdownFile, Path assetsBaseDir, List<String> ignoreList) {
        Pattern imgPattern = Pattern.compile(
                "!\\[(.*?)\\]\\((.*?)\\)|<img\\s+(.*?)src=\\\"(.*?)\\\"(.*?)>"
        );
        Path targetDir = assetsBaseDir.resolve(markdownFile.getFileName().toString().replace(".md", ""));

        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(markdownFile.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = imgPattern.matcher(line);
                StringBuilder updatedLine = new StringBuilder(line);

                while (matcher.find()) {
                    String alt = "";
                    String style = "";
                    String imgPath;

                    if (matcher.group(1) != null && matcher.group(2) != null) {
                        // Markdown 格式 ![alt](src)
                        alt = matcher.group(1);
                        imgPath = matcher.group(2);
                    } else {
                        // HTML 格式 <img src="src" alt="alt" style="style">
                        imgPath = matcher.group(4);
                        String attributes = matcher.group(3) + matcher.group(5);
                        Matcher altMatcher = Pattern.compile("alt=\\\"(.*?)\\\"").matcher(attributes);
                        Matcher styleMatcher = Pattern.compile("style=\\\"(.*?)\\\"").matcher(attributes);

                        if (altMatcher.find()) {
                            alt = altMatcher.group(1);
                        }
                        if (styleMatcher.find()) {
                            style = styleMatcher.group(1);
                        }
                    }

                    // 跳过忽略清单中的引用
                    if (ignoreList.contains(matcher.group(0))) {
                        continue;
                    }

                    String fileName = extractFileName(imgPath);

                    if (fileName != null) {
                        Path sourceFile = findFileInDirectory(assetsBaseDir, fileName);
                        if (sourceFile == null) {
                            System.err.println("未找到图片文件: " + imgPath);
                            continue;
                        }

                        Path targetFile = targetDir.resolve(fileName);
                        String relativePath = "!assets/" + targetDir.getFileName() + "/" + fileName;

                        copyFileIfNotExists(sourceFile, targetFile);

                        String updatedImgTag = String.format(
                                "<img src=\"%s\" alt=\"%s\" style=\"%s\" />",
                                relativePath,
                                alt,
                                style
                        );
                        updatedLine = new StringBuilder(updatedLine.toString().replaceFirst(Pattern.quote(matcher.group(0)), updatedImgTag));
                    }
                }

                updatedContent.append(updatedLine).append(System.lineSeparator());
            }

            // 写入更新后的内容
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(markdownFile.toFile()))) {
                writer.write(updatedContent.toString());
            }

        } catch (IOException e) {
            System.err.println("处理 Markdown 文件时出错: " + markdownFile + ", " + e.getMessage());
        }
    }

    /**
     * 在目录及其子目录中查找文件
     *
     * @param directory 目录路径
     * @param fileName  文件名
     * @return 文件路径，如果未找到则返回 null
     */
    private static Path findFileInDirectory(Path directory, String fileName) {
        try {
            return Files.walk(directory)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            System.err.println("搜索文件时出错: " + e.getMessage());
            return null;
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
            try {
                return Paths.get(imgPath).getFileName().toString();
            } catch (Exception ex) {
                return null;
            }
        }
    }

    /**
     * 复制文件到目标目录（如果文件不存在）
     *
     * @param sourceFile 源文件路径
     * @param targetFile 目标文件路径
     */
    private static void copyFileIfNotExists(Path sourceFile, Path targetFile) {
        try {
            if (!Files.exists(targetFile)) {
                Files.createDirectories(targetFile.getParent());
                Files.copy(sourceFile, targetFile);
                System.out.println("复制文件: " + sourceFile + " -> " + targetFile);
            } else {
                // System.out.println("文件已存在，跳过复制: " + targetFile);
            }
        } catch (IOException e) {
            System.err.println("复制文件时出错: " + sourceFile + " -> " + targetFile + ", " + e.getMessage());
        }
    }
}
