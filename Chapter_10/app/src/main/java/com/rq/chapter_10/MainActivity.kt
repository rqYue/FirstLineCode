package com.rq.chapter_10

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test(this)

    }

    fun test(context: Context){
//        val intent = Intent(context, TestActivity::class.java)
//        context.startActivity(intent)
//
//        startActivity<TestActivity>(context)

//        startActivity<TestActivity>(context) {
//            putExtra("param1", "data")
//            putExtra("param2", 123)
//        }
    }

//    inline fun <reified T> startActivity(context: Context) {
//        val intent = Intent(context, T::class.java)
//        context.startActivity(intent)
//    }

//    泛型实化
//    inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
//        val intent = Intent(context, T::class.java)
//        intent.block()
//        context.startActivity(intent)
//    }

    open class Person(val name: String, val age:Int)
    class Student(name: String, age: Int) : Person(name, age)
    class Teacher(name: String, age: Int) : Person(name, age)

//    class SimpleData<T> {
//        private var data: T? = null
//
//        fun set(t: T?){
//            data = t
//        }
//
//        fun get(): T? {
//            return data
//        }
//    }
//
//    fun main() {
//        val student = Student("Tom", 19)
//        val data = SimpleData<Student>()
//        data.set(student)
//        handleSimpleData(student)
//        val studentData = data.get()
//    }
//
//    fun handleSimpleData(data: SimpleData<Person>) {
//        val teacher = Teacher("Jack", 35)
//        data.set(teacher)
//    }

     class SimpleData<out T>(val data: T?) {
        fun get(): T? {
            return data
        }
    }

    public interface List<out E> : Collection<E> {
        override val size: Int
        override fun isEmpty() : Boolean
        override fun contains(element: @UnsafeVariance E): Boolean
        override fun iterator(): Iterator<E>
        public operator fun get(index : Int) : E

    }

//    interface Transformer<in T> {
//        fun transform(t: T): String
//    }
//
//    fun main() {
//        val trans = object : Transformer<Person> {
//            override fun transform(t: Person): String {
//                return "${t.name} ${t.age}"
//            }
//        }
//        handleTransformer(trans)
//    }
//
//    fun handleTransformer(trans: Transformer<Student>) {
//        val student = Student("Tom", 19)
//        val result = trans.transform(student)
//    }

    interface Transformer<in T> {
        fun transform(name: String, age: Int): @UnsafeVariance T
    }

    fun main() {
        val trans = object : Transformer<Person> {
            override fun transform(name: String, age: Int): Person {
                return Teacher(name, age)
            }
        }
        handleTransformer(trans)
    }

    fun handleTransformer(trans: Transformer<Student>) {
        val result = trans.transform("Tom", 19)
    }

    interface Comparable<in T> {
        operator fun compareTo(other: T): Int
    }

}