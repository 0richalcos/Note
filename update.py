import os
import re

# 配置目标文件夹名称
TARGET_DIR_NAME = "Markdown"

def get_indent_level(prefix_str):
    """
    计算缩进层级。
    逻辑：
    1. 现有的Tab算作1个层级。
    2. 空格按整除2计算（兼容2空格或3空格作为一级）。
    例如: 
      2空格 -> 1 Tab
      3空格 -> 1 Tab
      5空格 -> 2 Tabs
    """
    tabs = prefix_str.count('\t')
    spaces = prefix_str.count(' ')
    # 核心算法：空格数整除2。这能完美解决 3空格(有序) 和 2空格(无序) 混合的问题
    # 3 // 2 = 1, 2 // 2 = 1, 5 // 2 = 2
    level_from_spaces = spaces // 2 
    return tabs + level_from_spaces

def process_file(filepath):
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            lines = f.readlines()
    except Exception as e:
        print(f"❌ 无法读取文件: {filepath}, 错误: {e}")
        return

    new_lines = []
    
    # 状态机变量
    in_code_block = False
    block_start_fence = ""      # 记录代码块开始的标记 (如 ```java)
    block_base_prefix = ""      # 记录代码块开始行的原始缩进字符串 (如 "   ")
    block_target_prefix = ""    # 记录代码块应该被转换成的新缩进 (如 "\t")

    # 正则：匹配行首的空白字符
    prefix_pattern = re.compile(r'^(\s*)')
    # 正则：匹配代码块标记 (``` 或 ~~~)，不包含行首空白
    fence_pattern = re.compile(r'^(`{3,}|~{3,})')

    changed = False

    for line in lines:
        # 获取行首空白部分和剩余内容
        match = prefix_pattern.match(line)
        original_prefix = match.group(1) if match else ""
        content = line[len(original_prefix):] # 去掉缩进后的内容
        
        # 检查是否是代码块的分隔线（``` 或 ~~~）
        fence_match = fence_pattern.match(content)
        is_fence_line = fence_match is not None

        if is_fence_line:
            # 这是一个代码块的边界（开始或结束）
            
            if not in_code_block:
                # === 进入代码块 ===
                in_code_block = True
                block_start_fence = fence_match.group(1) # 记录是 ``` 还是 ~~~
                block_base_prefix = original_prefix      # 记住这一行原来的缩进（比如3个空格）
                
                # 计算这一行应该缩进多少个Tab
                level = get_indent_level(original_prefix)
                block_target_prefix = '\t' * level
                
                # 替换当前行
                new_line = block_target_prefix + content
            else:
                # === 结束代码块 ===
                # 只有当结束标记与开始标记匹配（或者是标准的```）时才视为结束
                # 这里简化逻辑：只要遇到围栏就视为结束，通常Markdown结构是配对的
                in_code_block = False
                
                # 结束行也需要调整缩进，通常和开始行一致
                level = get_indent_level(original_prefix)
                new_line = ('\t' * level) + content

        elif in_code_block:
            # === 在代码块内部 ===
            # 核心逻辑：保护代码内部结构
            # 我们只替换掉 "外层容器" 的缩进，保留 "代码内部" 的缩进
            
            if line.startswith(block_base_prefix):
                # 如果这一行以代码块的基础缩进开头（例如都是3个空格开头）
                # 去掉基础缩进，换成新的Tab缩进，保留后面的所有内容（包括代码自身的缩进）
                rest_of_line = line[len(block_base_prefix):]
                new_line = block_target_prefix + rest_of_line
            else:
                # 异常情况：代码块内部某行的缩进比代码块外壳还少？
                # 这种情况下通常直接保留原样，或者只尝试做最小转换
                # 这里选择：不做改动，防止破坏代码
                new_line = line 

        else:
            # === 普通文本/列表/图片 ===
            if not content.strip():
                #如果是空行，通常保留原样或变成空
                new_line = line
            else:
                # 计算目标Tab数量
                level = get_indent_level(original_prefix)
                new_line = ('\t' * level) + content

        if new_line != line:
            changed = True
        new_lines.append(new_line)

    # 只有文件内容确实发生变化时才写入
    if changed:
        try:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.writelines(new_lines)
            print(f"✅ 已修复: {filepath}")
        except Exception as e:
            print(f"❌ 写入失败: {filepath}, 错误: {e}")

def main():
    target_path = os.path.join(os.getcwd(), TARGET_DIR_NAME)
    if not os.path.exists(target_path):
        print(f"找不到文件夹: {target_path}")
        print("请确保脚本和 'Markdown' 文件夹在同一目录下。")
        return

    print(f"开始处理目录: {target_path} ...")
    count = 0
    for root, dirs, files in os.walk(target_path):
        for file in files:
            if file.endswith('.md'):
                file_path = os.path.join(root, file)
                process_file(file_path)
                count += 1
    
    print(f"\n处理完成！共扫描 {count} 个 Markdown 文件。")
    print("请打开 Typora 验证渲染效果。")

if __name__ == "__main__":
    main()