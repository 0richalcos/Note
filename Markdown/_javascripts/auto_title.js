// 监听 MkDocs Material 的页面加载事件 (兼容 Instant Loading)
document$.subscribe(function () {
    var paragraphs = document.querySelectorAll(".md-typeset > p");

    paragraphs.forEach(function (p) {

        // 1. 必须只有一个子元素
        if (p.children.length !== 1) return;

        var child = p.children[0];

        // 2. 子元素必须是 strong
        if (child.tagName !== "STRONG") return;

        // 3. 段落内容必须等于 strong 内容
        if (p.textContent.trim() !== child.textContent.trim()) return;

        // 4. 排除列表、引用、表格等情况（双保险）
        if (p.closest("li, blockquote, table")) return;

        // 通过所有检查，才认定为伪标题
        p.classList.add("auto-t");
    });
});
