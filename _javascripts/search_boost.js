/**
 * 搜索增强脚本 - 提升标题匹配的优先级
 * 解决中文搜索时标题不在最前面的问题
 */

document.addEventListener("DOMContentLoaded", function() {
  // 等待搜索功能初始化
  const searchInitInterval = setInterval(function() {
    const searchWorker = document.querySelector('[data-md-component="search"]');
    if (searchWorker) {
      clearInterval(searchInitInterval);
      enhanceSearch();
    }
  }, 100);

  function enhanceSearch() {
    // 拦截搜索结果的渲染
    const observer = new MutationObserver(function(mutations) {
      mutations.forEach(function(mutation) {
        if (mutation.addedNodes.length) {
          mutation.addedNodes.forEach(function(node) {
            if (node.classList && node.classList.contains('md-search-result__list')) {
              reorderSearchResults(node);
            }
          });
        }
      });
    });

    const searchContainer = document.querySelector('.md-search__output');
    if (searchContainer) {
      observer.observe(searchContainer, {
        childList: true,
        subtree: true
      });
    }
  }

  function reorderSearchResults(resultsList) {
    const items = Array.from(resultsList.querySelectorAll('.md-search-result__item'));

    if (items.length === 0) return;

    // 获取当前搜索词
    const searchInput = document.querySelector('.md-search__input');
    const searchTerm = searchInput ? searchInput.value.toLowerCase().trim() : '';

    if (!searchTerm) return;

    // 对结果进行评分和排序
    const scoredItems = items.map(item => {
      let score = 0;

      // 获取标题和内容
      const titleElement = item.querySelector('.md-search-result__title');
      const articleElement = item.querySelector('.md-search-result__article');
      const contentElement = item.querySelector('.md-search-result__teaser');

      const title = titleElement ? titleElement.textContent.toLowerCase() : '';
      const article = articleElement ? articleElement.textContent.toLowerCase() : '';
      const content = contentElement ? contentElement.textContent.toLowerCase() : '';

      // 标题完全匹配 -> 最高优先级
      if (title === searchTerm) {
        score += 1000;
      }
      // 标题开头匹配 -> 高优先级
      else if (title.startsWith(searchTerm)) {
        score += 800;
      }
      // 标题包含匹配 -> 中高优先级
      else if (title.includes(searchTerm)) {
        score += 500;
        // 如果是二级标题（通常在标题中显示）
        if (article.includes('##') || article.includes('#')) {
          score += 200;
        }
      }

      // 文章标题匹配 -> 中等优先级
      if (article.includes(searchTerm)) {
        score += 300;
      }

      // 内容匹配 -> 基础分数
      if (content.includes(searchTerm)) {
        score += 100;
        // 计算匹配密度
        const matches = content.match(new RegExp(searchTerm, 'gi'));
        if (matches) {
          score += matches.length * 10;
        }
      }

      // 精确匹配高亮的部分给予额外分数
      const marks = item.querySelectorAll('mark');
      marks.forEach(mark => {
        if (mark.textContent.toLowerCase() === searchTerm) {
          score += 50;
        }
      });

      return { item, score };
    });

    // 按分数降序排序
    scoredItems.sort((a, b) => b.score - a.score);

    // 重新排列 DOM
    scoredItems.forEach(({ item }) => {
      resultsList.appendChild(item);
    });
  }
});
