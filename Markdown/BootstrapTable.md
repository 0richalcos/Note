# 1、简单案例

这里使用了 Bootstrap 4.3.1、Bootstrap Table 1.15.3、FontAwesome 5.11.2、jQuery 3.3.1，下面是引用的 CSS 和 JS：

```html
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!--Bootstrap-table css-->
<link rel="stylesheet" href="https://unpkg.com/b ootstrap-table@1.15.3/dist/bootstrap-table.min.css">
<!--font-awesome css-->
<link rel="stylesheet" type="text/css" href="fontawesome-free-5.11.2-web/css/fontawesome.css"/>
<link rel="stylesheet" type="text/css" href="fontawesome-free-5.11.2-web/css/all.css">
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!--bootstrap-table-->
<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/locale/bootstrap-table-zh-CN.min.js"></script>
```

注意：导入顺序很重要，Bootstrap Table 依赖 Bootstrap，所以 Bootstrap Table 的引用写在 Bootstrap 的后面！

在`<body></body>`里面定义一个 table

```html
<table id="table"></table>
```

然后就可以写 jQuery 了，简单的表格信息如下：

```javascript
$(function () {
        $('#table').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $('#table').bootstrapTable({
            url: '/list',
            columns: [
                {
                    field: 'checked',
                    checkbox: true,
                    formatter: function (value, row, index) {
                        return row.id;
                    }
                }, {
                    title: '序号',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                }, {
                    title: '商品ID',
                    align: 'center'
                }, {
                    field: 'productName',
                    title: '商品名称',
                    sortable: true
                }, {
                    field: 'productWeight',
                    title: '商品重量(g)',
                    sortable: true
                }, {
                    field: 'productPrice',
                    title: '商品价格',
                    sortable: true
                }, {
                    field: 'productImage',
                    title: '商品图片'
                }, {
                    field: 'productClassify',
                    title: '商品分类'
                }, {
                    field: 'productRemark',
                    title: '备注'
                }]
        });
    });
```

此时会通过`/list`去获取 JSON 数据，默认请求方式是 GET，后台接口如下：

```java
@RestController
public class TableController {
    @Autowired
    private ProductService productService;
    
    /**
     * 查询所有的产品
     * 
     * @param order 排序
     * @return 产品集合
     */
    @GetMapping("/list")
    public List<Product> list(String order) {
        return productService.findAll(order);
    }
}
```

```java
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有的产品
     *
     * @param order 排序
     * @return 产品集合
     */
    @Override
    public List<Product> findAll(String order) {
        return productMapper.findAll(order);
    }
}
```

```java
@Repository
public interface ProductMapper {
    /**
     * 查询所有的产品
     * @param order 排序
     * @return 产品集合
     */
    public List<Product> findAll(String order);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.table.mapper.ProductMapper">
    <resultMap id="product" type="com.table.pojo.Product">
        <id property="productID" column="product_ID"/>
        <result property="productName" column="product_name"/>
        <result property="productWeight" column="product_weight"/>
        <result property="productPrice" column="product_price"/>
        <result property="productClassify" column="product_classify"/>
        <result property="productImage" column="product_image"/>
        <result property="productRemark" column="product_remark"/>
    </resultMap>

    <select id="findAll" resultMap="product" parameterType="string">
        select product_ID,product_name,product_weight,product_price,product_classify,product_image,product_remark from
        product
        <if test="order != null">
            order by #{order}
        </if>
    </select>
</mapper>
```



# 2、官方文档

| 名称                   | 标签                           | 类型     | 默认                                                         | 描述                                                         |
| ---------------------- | ------------------------------ | -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| -                      | data-toggle                    | String   | ‘table’                                                      | 不用写 JavaScript 直接启用表格                               |
| classes                | data-classes                   | String   | ‘table table-hover’                                          | 表格的类名称。默认情况下，表格是有边框的，可以添加 ‘table-no-bordered’ 来删除表格的边框样式 |
| sortClass              | data-sort-class                | String   | undefined                                                    | 被排序的 td 标签的 class 名                                  |
| height                 | data-height                    | Number   | undefined                                                    | 定义表格的高度                                               |
| undefinedText          | data-undefined-text            | String   | ‘-‘                                                          | 当数据为 undefined 时显示的字符                              |
| striped                | data-striped                   | Boolean  | false                                                        | 设置为 true 会有隔行变色效果                                 |
| sortName               | data-sort-name                 | String   | undefined                                                    | 定义排序列，通过url方式获取数据填写字段名，否则填写下标      |
| sortOrder              | data-sort-order                | String   | ‘asc’                                                        | 定义排序方式 ‘asc’ 或者 ‘desc’                               |
| sortStable             | data-sort-stable               | Boolean  | false                                                        | 设置为 true 将获得稳定的排序，会添加 _position 属性到 row 数据中 |
| iconsPrefix            | data-icons-prefix              | String   | ‘glyphicon’                                                  | 定义字体库 (‘Glyphicon’ or ‘fa’ for FontAwesome)，使用 ”fa” 时需引用 FontAwesome，并且配合 icons 属性实现效果 |
| icons                  | data-icons                     | Object   | { paginationSwitchDown:<br /> ‘glyphicon-collapse-down<br /> icon-chevron-down’,<br /> paginationSwitchUp: <br />‘glyphicon-collapse-up<br /> icon-chevron-up’,<br /> refresh: ‘glyphicon-refresh<br /> icon-refresh’ toggle: <br />‘glyphicon-list-alt<br /> icon-list-alt’ columns:<br /> ‘glyphicon-th icon-th’<br /> detailOpen: ‘glyphicon-plus <br />icon-plus’ detailClose: ‘glyphicon-minus <br />icon-minus’ } | 自定义图标                                                   |
| columns                | -                              | Array    | []                                                           | 列配置项,详情请查看 列参数 表格                              |
| data                   | -                              | Array    | []                                                           | 加载 json 格式的数据                                         |
| ajax                   | data-ajax                      | Function | undefined                                                    | 自定义 AJAX 方法,须实现 jQuery AJAX API                      |
| method                 | data-method                    | String   | ‘get’                                                        | 服务器数据的请求方式 ‘get’ or ‘post’                         |
| url                    | data-url                       | String   | undefined                                                    | 服务器数据的加载地址                                         |
| cache                  | data-cache                     | Boolean  | true                                                         | 设置为 true 禁用 AJAX 数据缓存                               |
| contentType            | data-content-type              | String   | ‘application/json’                                           | 发送到服务器的数据编码类型                                   |
| dataType               | data-data-type                 | String   | ‘json’                                                       | 服务器返回的数据类型                                         |
| ajaxOptions            | data-ajax-options              | Object   | {}                                                           | 提交ajax请求时的附加参数                                     |
| queryParams            | data-query-params              | Function | function(params) { return params; }                          | 请求服务器数据时，可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数，如果 queryParamsType = ‘limit’ ，返回参数必须包含 limit, offset, search, sort, order 否则, 需要包含:  pageSize, pageNumber, searchText, sortName, sortOrder。返回false将会终止请求 |
| queryParamsType        | data-query-params-type         | String   | ‘limit’                                                      | 设置为 ‘limit’ 则会发送符合 RESTFul 格式的参数.              |
| responseHandler        | data-response-handler          | Function | function(res) { return res; }                                | 加载服务器数据之前的处理程序，可以用来格式化数据。 参数：res为从服务器请求到的数据。 |
| pagination             | data-pagination                | Boolean  | false                                                        | 设置为 true 会在表格底部显示分页条                           |
| paginationLoop         | data-pagination-loop           | Boolean  | true                                                         | 设置为 true 启用分页条无限循环的功能。                       |
| onlyInfoPagination     | data-only-info-pagination      | Boolean  | false                                                        | 设置为 true 只显示总数据数，而不显示分页按钮。需要 pagination=’true’ |
| sidePagination         | data-side-pagination           | String   | ‘client’                                                     | 设置在哪里进行分页，可选值为 ‘client’ 或者 ‘server’。设置 ‘server’ 时，必须设置 服务器数据地址（url）或者重写 ajax 方法 |
| pageNumber             | data-page-number               | Number   | 1                                                            | 如果设置了分页，首页页码                                     |
| pageSize               | data-page-size                 | Number   | 10                                                           | 如果设置了分页，页面数据条数                                 |
| pageList               | data-page-list                 | Array    | [10, 25, 50, 100, All]                                       | 如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录 |
| selectItemName         | data-select-item-name          | String   | ‘btSelectItem’                                               | radio or checkbox 的字段名                                   |
| smartDisplay           | data-smart-display             | Boolean  | true                                                         | 设置为 true 可以在分页和卡片视图快速切换                     |
| escape                 | data-escape                    | Boolean  | false                                                        | 转义HTML字符串，替换 &、<、>、"、` 和 ' 字符                 |
| search                 | data-search                    | Boolean  | false                                                        | 是否启用搜索框                                               |
| searchOnEnterKey       | data-search-on-enter-key       | Boolean  | false                                                        | 设置为 true 时，按回车触发搜索方法，否则自动触发搜索方法     |
| strictSearch           | data-strict-search             | Boolean  | false                                                        | 设置为 true 启用 全匹配搜索，否则为模糊搜索                  |
| searchText             | data-search-text               | String   | ”                                                            | 初始化搜索文字                                               |
| searchTimeOut          | data-search-time-out           | Number   | 500                                                          | 设置搜索超时时间                                             |
| trimOnSearch           | data-trim-on-search            | Boolean  | true                                                         | 设置为 true 将允许空字符搜索                                 |
| showHeader             | data-show-header               | Boolean  | true                                                         | 是否显示列头                                                 |
| showFooter             | data-show-footer               | Boolean  | false                                                        | 是否显示列脚                                                 |
| showColumns            | data-show-columns              | Boolean  | false                                                        | 是否显示 内容列下拉框                                        |
| showRefresh            | data-show-refresh              | Boolean  | false                                                        | 是否显示 刷新按钮                                            |
| showToggle             | data-show-toggle               | Boolean  | false                                                        | 是否显示 切换试图（table/card）按钮                          |
| showPaginationSwitch   | data-show-pagination-switch    | Boolean  | false                                                        | 是否显示 数据条数选择框                                      |
| minimumCountColumns    | data-minimum-count-columns     | Number   | 1                                                            | 当列数小于此值时，将隐藏内容列下拉框。                       |
| idField                | data-id-field                  | String   | undefined                                                    | 指定主键列                                                   |
| uniqueId               | data-unique-id                 | String   | undefined                                                    | 为每一行指定唯一的标识符                                     |
| cardView               | data-card-view                 | Boolean  | false                                                        | 设置为 true 将显示card视图，适用于移动设备。否则为table试图，适用于pc |
| detailView             | data-detail-view               | Boolean  | false                                                        | 设置为 true 可以显示详细页面模式。                           |
| detailFormatter        | data-detail-formatter          | Function | function(index, row) { return ”; }                           | 格式化详细页面模式的视图。                                   |
| searchAlign            | data-search-align              | String   | ‘right’                                                      | 指定 搜索框 水平方向的位置。’left’ or ‘right’                |
| buttonsAlign           | data-buttons-align             | String   | ‘right’                                                      | 指定 按钮 水平方向的位置。’left’ or ‘right’                  |
| toolbarAlign           | data-toolbar-align             | String   | ‘left’                                                       | 指定 toolbar 水平方向的位置。’left’ or ‘right’               |
| paginationVAlign       | data-pagination-v-align        | String   | ‘bottom’                                                     | 指定 分页条 在垂直方向的位置。’top’ or ‘bottom’ or ‘bonth’   |
| paginationHAlign       | data-pagination-h-align        | String   | ‘right’                                                      | 指定 分页条 在水平方向的位置。’left’ or ‘right’              |
| paginationDetailHAlign | data-pagination-detail-h-align | String   | ‘left’                                                       | 指定 分页详细信息 在水平方向的位置。’left’ or ‘right’        |
| paginationPreText      | data-pagination-pre-text       | String   | ‘<’                                                          | 指定分页条中上一页按钮的图标或文字                           |
| paginationNextText     | data-pagination-next-text      | String   | ‘>’                                                          | 指定分页条中下一页按钮的图标或文字                           |
| clickToSelect          | data-click-to-select           | Boolean  | false                                                        | 设置true 将在点击行时，自动选择rediobox 和 checkbox          |
| singleSelect           | data-single-select             | Boolean  | false                                                        | 设置True 将禁止多选                                          |
| toolbar                | data-toolbar                   | String   | undefined                                                    | 一个 jQuery 选择器，指明自定义的toolbar 例如：#toolbar、 .toolbar |
| checkboxHeader         | data-checkbox-header           | Boolean  | true                                                         | 设置 false 将在列头隐藏 check-all checkbox                   |
| maintainSelected       | data-maintain-selected         | Boolean  | false                                                        | 设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项 |
| sortable               | data-sortable                  | Boolean  | true                                                         | 设置为 false 将禁止所有列的排序                              |
| silentSort             | data-silent-sort               | Boolean  | true                                                         | 设置为 false 将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server 时生效 |
| rowStyle               | data-row-style                 | Function | function(row,index) { return class; }                        | 自定义行样式 参数为： row: 行数据 index: 行下标 返回值可以为class或者css |
| rowAttributes          | data-row-attributes            | Function | function(row,index) { return attributes; }                   | 自定义行属性 参数为： row: 行数据 index: 行下标 返回值可以为class或者css 支持所有自定义属性 |
| customSearch           | data-custom-search             | Function | $.noop                                                       | 执行自定义搜索功能而不是内置搜索功能，需要一个参数： text：搜索文本 <br />如：<br />function customSearch(text) { <br /> //Search logic here.               <br /> //You must use `this.data` array in order to filter the data. NO use `this.options.data`. <br /> } |
| customSort             | data-custom-sort               | Function | $.noop                                                       | 执行自定义排序函数而不是内置排序函数，需要两个参数：  sortName: 排序名称 <br />sortOrder: 排序顺序 <br />如:<br />function customSort(sortName, sortOrder) {<br /> //Sort logic here. <br /> //You must use `this.data` array in order to sort the data. NO use `this.options.data`. <br /> } |

## 2.1、列参数

| 名称            | 标签                   | 类型                                | 默认      | 描述                                                         |
| --------------- | ---------------------- | ----------------------------------- | --------- | ------------------------------------------------------------ |
| radio           | data-radio             | Boolean                             | false     | 是否显示单选 radio                                           |
| checkbox        | data-checkbox          | Boolean                             | false     | 是否显示多选 checkbox                                        |
| field           | data-field             | String                              | undefined | 该列映射的 data 的参数名                                     |
| title           | data-title             | String                              | undefined | 该列的表头名                                                 |
| titleTooltip    | data-title-tooltip     | String                              | undefined | 该列表头的 title 提示文本                                    |
| class           | class / data-class     | String                              | undefined | 该列的 class                                                 |
| rowspan         | rowspan / data-rowspan | Number                              | undefined | 合并单元格时定义合并多少行                                   |
| colspan         | colspan / data-colspan | Number                              | undefined | 合并单元格时定义合并多少列                                   |
| align           | data-align             | String                              | undefined | 设置该列数据如何对齐，’left’，‘right’，‘center’              |
| halign          | data-halign            | String                              | undefined | table header对齐方式，‘left’，‘right’，‘center’              |
| falign          | data-falign            | String                              | undefined | table footer对齐方式，‘left’，‘right’，‘center’              |
| valign          | data-valign            | String                              | undefined | 单元格（cell）对齐方式，‘top’, ‘middle’, ‘bottom’            |
| width           | data-width             | Number {Pixels or <br />Percentage} | undefined | 列的宽度，可以使用像素或者百分比，不带单位则默认为 px        |
| sortable        | data-sortable          | Boolean                             | false     | 该列是否排序（表头显示双箭头）                               |
| order           | data-order             | String                              | ‘asc’     | 该列默认的排序方式， ‘asc’ or ‘desc’                         |
| visible         | data-visible           | Boolean                             | true      | 该列是否可见                                                 |
| cardVisible     | data-card-visible      | Boolean                             | true      | 在card视图里是否可见                                         |
| switchable      | data-switchable        | Boolean                             | true      | 列切换是否可见                                               |
| clickToSelect   | data-click-to-select   | Boolean                             | true      | 是否选中 checkbox 或者 radio，当该列被选择时                 |
| formatter       | data-formatter         | Function                            | undefined | 格式化单元格内容，function(value, row, index)，value：该cell本来的值，row：该行数据，index：该行序号（从0开始） |
| footerFormatter | data-footer-formatter  | Function                            | undefined | 格式化footer内容，function(rows)，rows：所有行数据           |
| events          | data-events            | Object                              | undefined | The cell 的事件监听，当使用formatter function的时候，有三个参数：event：the jQuery event，value：该cell的值，row：该行的数据，index：该行的序号 |
| sorter          | data-sorter            | Function                            | undefined | 自定义字段排序函数，function(a, b)                           |
| sortName        | data-sort-name         | String                              | undefined | 当列中有 html 等标签时，只排序实际内容（忽略标签和样式），例如字段为：”**abc**“，则 sortName=abc |
| cellStyle       | data-cell-style        | Function                            | undefined | 单元格样式，支持 css 和 classes，function(value, row, index)，value: 该cell的值，row: 该行的数据，index: 该行的序号 |
| searchable      | data-searchable        | Boolean                             | true      | 搜索时是否搜索此列                                           |
| searchFormatter | data-search-formatter  | Boolean                             | true      | 搜索是否使用格式化后的数据（即显示在页面上的数据）           |



## 2.2、事件

```javascript
$('#table').bootstrapTable({
    onEventName: function (arg1, arg2, ...) {
    // ...
    }
});

$('#table').on('event-name.bs.table', function (e, arg1, arg2, ...) {
// ...
});
```

| Option 事件      | jQuery 事件              | 参数                        | 描述                                                         |
| ---------------- | ------------------------ | --------------------------- | ------------------------------------------------------------ |
| onAll            | all.bs.table             | name, args                  | 所有的事件都会触发该事件，参数包括： <br />name：事件名， <br />args：事件的参数。 |
| onClickRow       | click-row.bs.table       | row, $element               | 当用户点击某一行的时候触发，参数包括： <br />row：点击行的数据， <br />$element：tr 元素， <br />field：点击列的 field 名称。 |
| onDblClickRow    | dbl-click-row.bs.table   | row, $element               | 当用户双击某一行的时候触发，参数包括： <br />row：点击行的数据， <br />$element：tr 元素， <br />field：点击列的 field 名称。 |
| onClickCell      | click-cell.bs.table      | field, value, row, $element | 当用户点击某一列的时候触发，参数包括： <br />field：点击列的 field 名称， <br />value：点击列的 value 值， <br />row：点击列的整行数据， <br />$element：td 元素。 |
| onDblClickCell   | dbl-click-cell.bs.table  | field, value, row, $element | 当用户双击某一列的时候触发，参数包括： <br />field：点击列的 field 名称， <br />value：点击列的 value 值， <br />row：点击列的整行数据， <br />$element：td 元素。 |
| onSort           | sort.bs.table            | name, order                 | 当用户对列进行排序时触发，参数包含： <br />name: 排序列字段名， <br />order: 排序列的顺序。 |
| onCheck          | check.bs.table           | row                         | 当用户检查行时触发，参数包含： <br />row: 与单击的行对应的记录，<br />$element: 选中DOM元素。 |
| onUncheck        | uncheck.bs.table         | row                         | 在用户取消选中行时触发，参数包含：  <br />row: 与单击的行对应的记录， <br />$element: 取消选中DOM元素。 |
| onCheckAll       | check-all.bs.table       | rows                        | 当用户检查所有行时触发，参数包含： <br />rows: 与新检查的行对应的记录数组。 |
| onUncheckAll     | uncheck-all.bs.table     | rows                        | 当用户取消选中所有行时触发，参数包含： <br />rows:与先前检查的行对应的记录数。 |
| onCheckSome      | check-some.bs.table      | rows                        | 当用户检查某些行时触发，参数包含：  <br />rows: 与先前检查的行对应的记录数组。 |
| onUncheckSome    | uncheck-some.bs.table    | rows                        | 当用户取消选中某些行时触发，参数包含： <br />rows: 与先前检查的行对应的记录数组。 |
| onLoadSuccess    | load-success.bs.table    | data                        | 在成功加载远程数据时触发。                                   |
| onLoadError      | load-error.bs.table      | status                      | 在加载远程数据时发生某些错误时触发。                         |
| onColumnSwitch   | column-switch.bs.table   | field, checked              | 切换列可见时触发。                                           |
| onColumnSearch   | column-search.bs.table   | field, text                 | 在按列搜索时触发。                                           |
| onPageChange     | page-change.bs.table     | number, size                | 更改页码或页面大小时触发。                                   |
| onSearch         | search.bs.table          | text                        | 在搜索表时触发。                                             |
| onToggle         | toggle.bs.table          | cardView                    | 切换表视图时触发。                                           |
| onPreBody        | pre-body.bs.table        | data                        | 在呈现表体之前触发。                                         |
| onPostBody       | post-body.bs.table       | none                        | 在表体表示并在DOM中可用之后触发。                            |
| onPostHeader     | post-header.bs.table     | none                        | 在表头之后触发，并在DOM中可用。                              |
| onExpandRow      | expand-row.bs.table      | index, row, $detail         | 当点击详细图标展开详细页面的时候触发。                       |
| onCollapseRow    | collapse-row.bs.table    | index, row                  | 当点击详细图片收起详细页面的时候触发。                       |
| onRefreshOptions | refresh-options.bs.table | options                     | 在刷新选项之后和在销毁和初始化表之前触发。                   |
| onRefresh        | refresh.bs.table         | params                      | 单击刷新按钮后触发。                                         |



## 2.3、官方文档

| 名称              | 参数           | 描述                                                         |
| ----------------- | -------------- | ------------------------------------------------------------ |
| getOptions        | none           | 返回表格的 Options。                                         |
| getSelections     | none           | 返回所选的行，当没有选择任何行的时候返回一个空数组。         |
| getAllSelections  | none           | 返回所有选择的行，包括搜索过滤前的，当没有选择任何行的时候返回一个空数组。 |
| getData           | useCurrentPage | 或者当前加载的数据。假如设置 useCurrentPage 为 true，则返回当前页的数据。 |
| getRowByUniqueId  | id             | 根据 uniqueId 获取行数据。                                   |
| load              | data           | 加载数据到表格中，旧数据会被替换。                           |
| showAllColumns    | none           | 显示所有列。                                                 |
| hideAllColumns    | none           | 隐藏所有列.                                                  |
| append            | data           | 添加数据到表格在现有数据之后。                               |
| prepend           | data           | 插入数据到表格在现有数据之前。                               |
| remove            | params         | 从表格中删除数据，包括两个参数： <br />field: 需要删除的行的 field 名称， <br />values: 需要删除的行的值，类型为数组。 |
| removeAll         | -              | 删除表格所有数据。                                           |
| removeByUniqueId  | id             | 根据 uniqueId 删除指定的行。                                 |
| insertRow         | params         | 插入新行，参数包括： <br />index: 要插入的行的 index， <br />row: 行的数据，Object 对象。 |
| updateRow         | params         | 更新指定的行，参数包括： <br />index: 要更新的行的 index， <br />row: 行的数据，Object 对象。 |
| showRow           | params         | 显示指定的行，参数包括： <br />index: 要更新的行的 index 或者 uniqueId， <br />isIdField: 指定 index 是否为 uniqueid。 |
| hideRow           | params         | 显示指定的行，参数包括： <br />index: 要更新的行的 index，<br />uniqueId: 或者要更新的行的 uniqueid。 |
| getRowsHidden     | show           | 获取所有行隐藏，如果show参数为true，行将再次显示，否则，方法 只返回隐藏的行。 |
| mergeCells        | options        | 将某些单元格合并到一个单元格，选项包含以下属性： <br />index：行索引，<br />field：字段名称，<br />rowspan：要合并的rowspan数量，<br />colspan：要合并的colspan数量。 |
| updateCell        | params         | 更新一个单元格，params包含以下属性： <br />index：行索引，<br />field：字段名称，<br />value：新字段值。 |
| refresh           | params         | 刷新远程服务器数据，可以设置` {silent：true} `以静默方式刷新数据，并设置` {url：newUrl} `更改URL。 要提供特定于此请求的查询参数，请设置` {query：{foo：’bar’}}` |
| refreshOptions    | options        | 刷新选项。                                                   |
| resetSearch       | text           | 设置搜索文本。                                               |
| showLoading       | none           | 显示加载状态。                                               |
| hideLoading       | none           | 隐藏加载状态。                                               |
| checkAll          | none           | 检查所有当前页面行。                                         |
| uncheckAll        | none           | 取消选中所有当前页面行。                                     |
| check             | index          | 检查一行，行索引从0开始。                                    |
| uncheck           | index          | 取消选中一行，行索引从0开始。                                |
| checkBy           | params         | 按值数组检查一行，参数包含： <br />field：用于查找记录的字段的名称，<br />values：要检查的行的值数组 <br />例:  $(“#table”).bootstrapTable(<br />“checkBy”, {field:”field_name”, values:[“value1”,”value2”,”value3”]}) |
| uncheckBy         | params         | 按值数组取消选中一行，参数包含： <br />field：用于查找记录的字段的名称，<br />values：要取消选中的行的值的数组 <br />例:  $(“#table”).bootstrapTable(<br />“uncheckBy”, {field:”field_name”, values:[“value1”,”value2”,”value3”]}) |
| resetView         | params         | 重置引导表视图，例如重置表高度。                             |
| resetWidth        | none           | 调整页眉和页脚的大小以适合当前列宽度。                       |
| destroy           | none           | 销毁引导表。                                                 |
| showColumn        | field          | 显示指定的列。                                               |
| hideColumn        | field          | 隐藏指定的列。                                               |
| getHiddenColumns  | -              | 获取隐藏的列。                                               |
| getVisibleColumns | -              | 获取可见列。                                                 |
| scrollTo          | value          | 滚动到指定位置，单位为 px，设置 ‘bottom’ 表示跳到最后。      |
| getScrollPosition | none           | 获取当前滚动条的位置，单位为 px。                            |
| filterBy          | params         | （只能用于 client 端）过滤表格数据， 你可以通过过滤`{age: 10}`来显示 age 等于 10 的数据。 |
| selectPage        | page           | 跳到指定的页。                                               |
| prevPage          | none           | 跳到上一页。                                                 |
| nextPage          | none           | 跳到下一页。                                                 |
| togglePagination  | none           | 切换分页选项。                                               |
| toggleView        | none           | 切换 card/table 视图                                         |
| expandRow         | index          | 如果详细视图选项设置为 True，请展开具有通过参数传递的索引的行。 |
| collapseRow       | index          | 如果详细视图选项设置为 True，则折叠具有通过参数传递的索引的行。 |
| expandAllRows     | is subtable    | 如果详细视图选项设置为 True，请展开所有行。                  |
| collapseAllRows   | is subtable    | 如果详细信息视图选项设置为 True，则折叠所有行。              |



# 3、图标

在做网页开发中，前端页面显示时经常会用到字体库图标，如 iconfont，fontawesome，glyphicons，lonicons 等。这里使用 fontawesome，下载地址：https://fontawesome.com/download



**基于 SVG 格式,使用 JS 调用图标**

压缩包中含有 v4-shims.js 文件。这个是为了兼容 4.x 版本的升级使用。如果已有 4.x 版本的项目，想更换成 svg 图标，那么需要额外调用 v4-shims.js或 v4-shims.min.js 文件。以保证兼容性。

若想引入所有图标:

```html
<script src="all.js"></script>
```

若只需要引入 solid 系的图标:

```html
<script src="solid.min.js"></script>
<!-- 在调用solid,brand,regular系js时都要调用的文件 -->
<script src="fontawesome.min.js"></script>
```

使用 js 方式调用的图标，最终在 DOM 中会以 svg 代码显示图标。



**基于网页字体,使用 CSS 调用图标**

CSS 方法要调用两组文件,一个是 css(或 less 或 scss) 样式表, 另一组是图标字体文件。即 css 方法调用时，实际上要用到的是 **css 文件夹及 webfonts 文件夹**里的文件。

若引入所有图标:

```html
<link rel="stylesheet" href="all.css">
```

若只想引入brand的图标:

```html
<link rel="stylesheet" href="brands.min.css">
<!-- 在调用solid,brand,regular系css时都要调用的文件 -->
<link rel="stylesheet" href="fontawesome.min.css">
```

使用 css 方式调用图标，以网页字体的方式显示，则 dom 结构内没有 svg 代码。



# 4、其他

## 4.1、页脚合并

首先在 columns 里面定义 footerFormatter：

```javascript
footerFormatter: function (rows) {
    let sum = 0;
    for (let i = 0; i < rows.length; i++) {
        sum += rows[i].productPrice;
    }
    return sum;
}
```

然后在 columns 下面实现 onPostBody 方法，进行合并处理：

```javascript
onPostBody: function () {
    //获取table表中footer 并获取到这一行的所有列
    let footer_tr = $('.fixed-table-body table tfoot tr');
    let footer_th = footer_tr.find('>th');
    //遍历隐藏中间的列 下标从1开始
    for (let i = 1; i < footer_th.length - 5; i++) {
        footer_th.eq(i).hide();
    }
    //设置跨列
    footer_th.eq(0).attr('colspan', footer_th.length - 5).show();
}
```



## 4.2、行内编辑

**思路一**

Bootstrap 的添加和修改数据都是通过模态框实现的，现在需要将其改为点击行来编辑。

```html
<div class="table-box" style="margin: 20px;">
    <div id="toolbar">
        <button id="button" class="btn btn-default">insertRow</button>
        <button id="getTableData" class="btn btn-default">getTableData</button>
    </div>
    <table id="table"></table>
</div>
```

```javascript
$(function() {
    let $table = $('#table');
    let $button = $('#button');
    let $getTableData = $('#getTableData');

    $button.click(function() {
        $table.bootstrapTable('insertRow', {
            index: 0,
            row: {
                id: '',
                name: '',
                price: ''
            }
        });
    });

    $table.bootstrapTable({
        url: 'data2.json',
        toolbar: '#toolbar',
        clickEdit: true,
        showToggle: true,
        pagination: true,       //显示分页条
        showColumns: true,
        showPaginationSwitch: true,     //显示切换分页按钮
        showRefresh: true,      //显示刷新按钮
        //clickToSelect: true,  //点击row选中radio或CheckBox
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: 'Item ID'
        }, {
            field: 'name',
            title: 'Item Name'
        }, {
            field: 'price',
            title: 'Item Price'
        }, ],
        /**
         * @param {点击列的 field 名称} field
         * @param {点击列的 value 值} value
         * @param {点击列的整行数据} row
         * @param {td 元素} $element
         */
        onClickCell: function(field, value, row, $element) {
            $element.attr('contenteditable', true);
            $element.blur(function() {
                let index = $element.parent().data('index');
                let tdValue = $element.html();

                saveData(index, field, tdValue);
            })
        }
    });

    $getTableData.click(function() {
        alert(JSON.stringify($table.bootstrapTable('getData')));
    });

    function saveData(index, field, value) {
        $table.bootstrapTable('updateCell', {
            index: index,       //行索引
            field: field,       //列名
            value: value        //cell值
        })
    }

});
```

```json
[
    { "id": 1, "name": "Item 1", "price": "￥1" },
    { "id": 2, "name": "Item 2", "price": "￥2" },
    { "id": 3, "name": "Item 3", "price": "￥3" }
]
```

**实现原理**

通过bootstrap table自带的 `onClickCell` 方法，点击 td 添加 `contenteditable` 属性(ps: 使元素可编辑),于是 td 元素具有了类似于文本框的 *focus* 和 *blur* 事件，用户点击 td 获取焦点，编辑完内容失去焦点后，调用 `updateCell`方法更新单元格数据。



**思路二**

实现思路参照 bootstrapTable API 中的 onDblClickCell 和updateCell 方法

| 方法名         | 参数                        | 说明                                                         |
| -------------- | --------------------------- | ------------------------------------------------------------ |
| onDblClickCell | field, value, row, $element | 当用户双击某一列的时候触发，参数包括：<br/>field：点击列的 field 名称，<br/>value：点击列的 value 值，<br/>row：点击列的整行数据，<br/>$element：td 元素。 |
| updateCell     | index, field, value         | 更新一个单元格，params包含以下属性：<br/>index: 行索引。<br/>field: 字段名称。<br/>value: 新字段值。 |

```javascript
onDblClickCell: function (field, value, row, $element) {
    let upIndex = $element[0].parentElement.rowIndex - 1;
    let editFields = options.editFileds;
    editFields.forEach(function (editFiled) {
        if (field == editFiled) {
            $element[0].innerHTML = "<input id='inputCell' type='text' name='inputCell'  value='" + value + "'>";
            $("#inputCell").focus();
            $("#inputCell").blur(function () {
                let newValue = $("#inputCell").val();
                row[field] = newValue;
                $(this).remove();
                $('#bootstrap-table').bootstrapTable('updateCell', {
                    index: upIndex,
                    field: field,
                    value: newValue
                });
                rowedit(row);
            });
        }
    })
},
```

双击单元格后触发 onDblClickCell 事件，获取到行号，插入 input 元素即可行内编辑，编辑完后触发 updateCell 方法更新单元格数据。

下面是js封装的操作函数，用于提交数据，返回操作结果。（使用若依框架）

```javascript
// 操作封装处理
operate: {
	// post请求传输
	post: function(url, data) {
		$.operate.submit(url, "post", "json", data);
	},
	// 修改行
	rowedit: function(row) {
		$.modal.loading("正在处理中，请稍后...");
		var url =  $.table._option.roweditUrl;
		var config = {
			url: url,
			type: "post",
			dataType: "json",
			data: row,
			success: function(result) {
				$.operate.ajaxSuccess(result);
			}
		};
		$.ajax(config)
    },
	// 保存结果弹出msg刷新table表格
	ajaxSuccess: function (result) {
	if (result.code == web_status.SUCCESS) {
        $.modal.msgSuccess(result.msg);
        $.table.refresh();
    } else {
        $.modal.alertError(result.msg);
    }
        $.modal.closeLoading();
    }
}
```

在html页面文件的

```javascript
$(function () {
    var options = {
        editFileds: ["zhAuditedName", "zhParentName"],  //加入需要编辑的属性字段名称  
```
