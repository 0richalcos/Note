// 监听 MkDocs Material 的页面加载事件 (兼容 Instant Loading)
document$.subscribe(function () {
    // 找到正文区域所有的段落 <p>
    var paragraphs = document.querySelectorAll(".md-typeset p");

    paragraphs.forEach(function (p) {
        // 1. 判断该段落是否只有一个子元素
        if (p.children.length === 1) {
            var child = p.children[0];

            // 2. 判断这个子元素是不是 <strong> (即加粗)
            if (child.tagName === "STRONG") {

                // 3. 关键判断：确保段落里没有其他文字（去除首尾空格后比较内容）
                // 比如 "这是 **加粗**" 就不符合，因为 p 的内容比 strong 的多
                if (p.textContent.trim() === child.textContent.trim()) {
                    // 符合条件！给这个段落 (<p>) 添加一个特殊的类
                    p.classList.add("auto-t");
                }
            }
        }
    });
});