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