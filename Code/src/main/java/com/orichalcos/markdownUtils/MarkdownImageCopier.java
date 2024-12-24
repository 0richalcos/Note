package com.orichalcos.markdownUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownImageCopier {
    public static void main(String[] args) {
        // 设置文件路径
        String markdownDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown";
        String assetsDirPath = "E:\\Users\\Orichalcos\\Documents\\Note\\Markdown\\!assets";

        // 遍历 Markdown 文件并复制图片引用
        copyImagesFromMarkdown(Paths.get(markdownDirPath), Paths.get(assetsDirPath));
    }

    /**
     * 遍历 Markdown 文件并复制图片引用到目标文件夹
     *
     * @param markdownDir   Markdown 文件目录路径
     * @param assetsBaseDir 资源基础目录
     */
    private static void copyImagesFromMarkdown(Path markdownDir, Path assetsBaseDir) {
        try {
            Files.walk(markdownDir).filter(file -> file.toString().endsWith(".md")).forEach(markdownFile -> {
                copyImagesForMarkdownFile(markdownFile, assetsBaseDir);
            });
        } catch (IOException e) {
            System.err.println("读取 Markdown 文件目录时出错: " + e.getMessage());
        }
    }

    /**
     * 为单个 Markdown 文件复制图片引用
     *
     * @param markdownFile  Markdown 文件路径
     * @param assetsBaseDir 资源基础目录
     */
    private static void copyImagesForMarkdownFile(Path markdownFile, Path assetsBaseDir) {
        Pattern imgPattern = Pattern.compile("!\\[.*?\\]\\((.*?)\\)|<img\\s+src=\\\"(.*?)\\\".*?>");
        Path targetDir = assetsBaseDir.resolve(markdownFile.getFileName().toString().replace(".md", ""));

        try (BufferedReader reader = new BufferedReader(new FileReader(markdownFile.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = imgPattern.matcher(line);
                while (matcher.find()) {
                    String imgPath = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
                    String fileName = extractFileName(imgPath);

                    if (fileName != null) {
                        Path sourceFile = assetsBaseDir.resolve(fileName);
                        Path targetFile = targetDir.resolve(fileName);

                        copyFileIfNotExists(sourceFile, targetFile);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("处理 Markdown 文件时出错: " + markdownFile + ", " + e.getMessage());
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
                System.out.println("文件已存在，跳过复制: " + targetFile);
            }
        } catch (IOException e) {
            System.err.println("复制文件时出错: " + sourceFile + " -> " + targetFile + ", " + e.getMessage());
        }
    }
}
