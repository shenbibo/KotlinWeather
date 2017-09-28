// java中
public class Person {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        his.name = name;
    }
}

Person person = new Person();
person.setName("name");
String name = person.getName();


// kotlin中
public class Person {
    var name: String = ""
}

val person = Person()
person.name = "name"   // 实际调用的是person.setName
val name = person.name  // 实际调用的是person.getName

public classs Person {
    var name: String = ""
        get() = field.toUpperCase()    // field 作为幕后字段，只能在它的访问器中使用
        set(value){
            field = "Name: $value"
        }
}

fun toString(age: Int, name: String = "sky", high: Int = 170) :String{
    return name + age + high
}

// 默认方法可以使用如下方法调用
toString(22)

// 使用命名参数的方法，只实现自己想重新赋值的参数
toString(22, high = 180)

// 单表达式函数
fun foo(x:Int) = x * 2 // 此处不需要显式的写出函数的返回类型，编译器可以直接推断

// 同样可以将上面的toString方法变为
fun toString(age: Int, name: String = "sky", high: Int = 170) = name + age + high

val str: String = "123456"
assert('3' == str[2])

/**
 * 不同的参数还可以实现操作符的不同重载实现
 * */
class Foo(val startIndex:Int) {

    operator fun plus(addChar:Char) : Foo = Foo(startIndex + addChar.toInt())

    operator fun plus(foo2:Foo): Int = this.startIndex + foo2.startIndex
}

val foo1 = Foo(0)
println((foo1 + 'a').startIndex)  // 打印97
println(foo1 + Foo(123))          //打印 123

// 而在kotlin中我们只需要定义一个Context的扩展方法
fun Context.toast(msg: String, duration: Int = Toast.LENGTH_LONG){
     Toast.makeText(this, msg,duration).show();
}

// 如果在context的子类中我们可以直接调用
toast("hello kotlin")

// 在Fragment中
activity.toast("hello fragment", Toast.LENGTH_SHORT)

// 扩展属性
val ViewGroup.children: Iterable<View>
    get() = (0 until childCount).map { getChildAt(it) }

data class User(var name: String, var age: Int)

val user("sky", 12)
val(name, age) = user

// 编译器会将上述解构编译成以下代码：
val name = user.compent1()  // 实际就是调用user.getName()
val age = user.compent2()   // 实际就是调用user.getAge()

view.setOnClickListener{ toast("kotlin click v = $it") }

// java中
view.setOnClickListener(new OnClickListener(){
    @Override
    public onClick(View v){
        longToast(MainActivity.this, "java click v = " + v);
    }
});

// kotlin中
view.setOnClickListener{ toast("kotlin click v = $it") }

val ints: List<Int> = ArrayList()

ints.filter(fun(item): Boolean = item > 0)
// 当然对于编译器可以自动推断返回值类型的也可以省略
ints.filter(fun(item) = item > 0)

val view = View(this)
with(view){
    isClickable = false  // view.setClickable(false)
    isFocusable = true   // view.setFocusable(true)
    val getWidth = width
}

interface Base {
    fun print()
    fun print2()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }

    override fun print2(){print(x + 2)}
}

class TestImpl(b: Base) : Base by b {
    override fun print2() = print("testImpl")
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    val test = TestImpl(b)
    test.print()     // 输出 10
    test.print2()    // 输出 testImpl
}

val items = listOf(1, 2, 3, 4)
items.first() == 1
items.last() == 4
items.filter { it % 2 == 0 }   // 返回 [2, 4]
items.forEach( print(it))      // 遍历所有的数组输出 1 2 3 4

val rwList = mutableListOf(1, 2, 3)
rwList.requireNoNulls()        // 返回 [1, 2, 3]
if (rwList.none { it > 6 }) println("No items above 6")  // 输出“No items above 6”
val item = rwList.firstOrNull()

val max = if (a > b) a else b
fun max(a: Int, b: Int) = if (a > b) a else b


fun whenTest(x: Any) = when(x){
    0,1 -> x
    in 2..10 -> x.toString() + 10
    !in 11..30 -> print("$x is not in 11..30")
    is String -> print(x.length)
    is TextView -> x.text.toString()
    !is Long -> print("$x is not a Long")
    else -> false
}

while (x > 0) {
    x--
}

do {
  val y = retrieveData()
} while (y != null) // y 在此处可见

for (item in collection) print(item)

// 使用下标访问list与数组
var list:List<Int> = ArrayList()
for (index in list.indices) print(list.get(index))

// .. 表示的是是rangeTo函数，返回的是一个 *Range 对象
for (i in 1..4) print(i) // 输出“1234”
for (i in 4..1) print(i) // 什么都不输出

// 反转后迭代
for (i in (1..4).reversed() ) print(i)  // 输出“4321”
// 倒序迭代
for (i in 4 downTo 1) print(i) // 输出“4321”

// 指定迭代步长
for (i in 4 downTo 1 step 2) print(i) // 输出“42”

// 不包括末尾元素
for (i in 1 until 10) print(i)   // i in [1, 10) 排除了 10

if (obj is String) {
    print(obj.length)
}

if (obj !is String) { // 与 !(obj is String) 相同
    print("Not a String")
}
else {
    print(obj.length)
}

// `||` 右侧的 x 自动转换为字符串
if (x !is String || x.length == 0) return

// `&&` 右侧的 x 自动转换为字符串
if (x is String && x.length > 0) {
    print(x.length) // x 自动转换为字符串
}

var str: String?
...
if(str != null){
    print(str.length)
}

// 不安全的操作符
val x: String? = y as String?

// 安全不抛出异常的操作符
val x: String? = y as? String

interface MyInterface {
    // 抽象的属性， 实现类必须要复写它
    val prop: Int 

    // 带有访问器的属性，但是不能有幕后字段
    val propertyWithImplementation: String   
        get() = "foo"

    // 类似于java8接口中的默认方法
    fun foo() {            
        print(prop)
    }

    fun bar()
}

class Child : MyInterface {
    override val prop: Int = 29

    override fun bar(){
        print("the child class have bar")
    }
}


open class Person constructor(private var name: String, age: Int) {
    var firstName: String = name.substring(0, 2)

    init {
        println("Person init is called")
    }

    // 子类可以复写
    open var age = age

    open fun foo() {
        println("this is person foo $name")
    }
}

class Engineer(name: String, age: Int, private var skillList: MutableList<String>) : Person(name, age) {
    // 工程师消耗脑力过渡，年轮+5
    override var age = super.age + 5

    // 初始化代码，主构造函数被调用时，会调用
    init {
        println("Engineer init is called")
    }

    constructor(name: String, age: Int) : this(name, age, ArrayList()) {
        skillList.add("java")
        skillList.add("kotlin")
        skillList.add("c++")
    }

    override fun foo() {
        println("this is Engineer foo")
    }
}

var nullableStr: String? = "nullable"
val size = nullableStr.length   // 无法编译

// 两种访问方式
// 1、先判空
if(nullableStr != null){
    print(nullableStr.length)
}
// 2、使用安全访问符"?."，如果不为
val size = nullableStr?.length