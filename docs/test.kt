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
val ViewGroup.children: List<View>
    get() {
        val childes: MutableList<View> = ArrayList()
        for (i in 0 until childCount){
            childes[i] = getChildAt(i)
        }
        return childes
    }

data class User(var name: String, var age: Int)

val user("sky", 12)
val(name, age) = user

// 编译器会将上述解构编译成以下代码：
val name = user.compent1()  // 实际就是调用user.getName()
val age = user.compent2()   // 实际就是调用user.getAge()

