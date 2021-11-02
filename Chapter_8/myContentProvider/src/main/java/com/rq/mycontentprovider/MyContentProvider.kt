package com.rq.mycontentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3

    private val authority = "com.rq.provider"
    private var dbHelper: MyDatabaseHelper? = null

    //使用懒加载，只有当 uriMatcher 首次调用时，才会执行其初始化
    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        //book 表所有数据
        matcher.addURI(authority, "book", bookDir)
        //book 表单条数据
        matcher.addURI(authority, "book/#", bookItem)

        //category 表所有数据
        matcher.addURI(authority, "category", categoryDir)
        //category 表单条数据
        matcher.addURI(authority, "category/#", categoryItem)
        matcher
    }

    // 返回一个 布尔值，创建成功返回 true，创建失败 返回 false
    override fun onCreate() = context?.let {
        dbHelper = MyDatabaseHelper(it, "BookStore.db", 2)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {

        val db = it.readableDatabase
        // 使用 when 语句进行分支判断
        val cursor = when (uriMatcher.match( uri)) {

            bookDir -> db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)

            bookItem -> {
                // 调用 getPathSegments() 方法，将 URI权限之后的部分 以 "/" 符号进行分割，并将分隔后的结果放入一个字符串列表
                // 列表的第 0个位置存放的是路径，第一个位置存放的是具体的 id
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "id = ?", arrayOf(bookId), null, null, sortOrder)
            }

            categoryDir -> db.query("Category", projection, selection, selectionArgs, null, null, sortOrder)

            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category", projection, "id = ?", arrayOf(categoryId), null, null, sortOrder)
            }

            else -> null
        }
        cursor
    }

    // 需要返回能表示该新增数据的 uri，该 uri 以新增数据的 id 作为结尾
    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {

        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {

            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://$authority/book/$newBookId")
            }

            categoryDir,categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                // 进行 Uri 解析，将 Uri对象作为返回值返回
                Uri.parse("content://$authority/category/$newCategoryId")
            }

            else -> null
        }
        uriReturn
    }

    // 受到影响的行数作为返回值返回
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {

        val db = it.writableDatabase
        val updatedRows = when (uriMatcher.match(uri)){

            bookDir -> db.update("Book", values, selection, selectionArgs)

            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book", values, "id = ?", arrayOf(bookId))
            }

            categoryDir -> db.update("Category", values, selection, selectionArgs)

            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(categoryId))
            }

            else -> 0
        }
        updatedRows
    } ?: 0

    // 被删除的行数作为返回值返回
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbHelper?.let {

        val db = it.writableDatabase
        val deletedRows = when (uriMatcher.match( uri)) {

            bookDir -> db.delete("Book", selection, selectionArgs)

            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.delete("Book", "id = ?", arrayOf(bookId))
            }

            categoryDir -> db.delete("Category", selection, selectionArgs)

            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.delete("Category", "id = ?", arrayOf(categoryId))
            }

            else -> 0
        }
        deletedRows
    } ?: 0

    override fun getType(uri: Uri) = when (uriMatcher.match(uri) ) {

        bookDir -> "vnd.android.cursor.dir/vnd.com.rq.provider.book"
        bookItem -> "vnd.android.cursor.item/vnd.com.rq.provider.book"

        categoryDir -> "vnd.android.cursor.dir/vnd.com.rq.provider.category"
        categoryItem -> "vnd.android.cursor.item/vnd.com.rq.provider.category"

        else -> null
    }

}