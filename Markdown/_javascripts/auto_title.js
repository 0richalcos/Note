// 监听 MkDocs Material 的页面加载事件 (兼容 Instant Loading)
document$.subscribe(function () {
    var paragraphs = document.querySelectorAll(".md-typeset > p");

    paragraphs.forEach(function (p) {

        /* 1. 必须只有一个 strong */
        if (p.children.length !== 1) return;
        var child = p.children[0];
        if (child.tagName !== "STRONG") return;

        /* 2. 内容必须完全一致 */
        if (p.textContent.trim() !== child.textContent.trim()) return;

        /* 3. 排除列表 / 引用 / 表格 */
        if (p.closest("li, blockquote, table")) return;

        /* 4. 前后兄弟节点判断（模拟“空行”） */
        var prev = p.previousElementSibling;
        var next = p.nextElementSibling;

        // 前后不能是普通正文段落
        if (prev && prev.tagName === "P") return;
        if (next && next.tagName === "P") return;

        /* 满足所有条件，认定为伪标题 */
        p.classList.add("auto-t");
    });
});
