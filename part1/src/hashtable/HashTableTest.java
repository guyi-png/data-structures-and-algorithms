package hashtable;

import java.util.Scanner;

/**
 *  哈希表
 */
public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);
        String key = "";
        while (true){
            System.out.println("add：添加成员");
            System.out.println("find：查看某员工");
            System.out.println("list：显示所有成员");
            System.out.println("exit：退出程序");
            Scanner scanner = new Scanner(System.in);
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    hashTable.add(new Employee(id, name));
                    break;
                case "find":
                    System.out.println("请输入要查找的员工的id");
                    id = scanner.nextInt();
                    hashTable.findById(id);
                    break;
                case "list":
                    hashTable.showList();
                    break;
                case "exit":
                    System.out.println("程序退出");
                    scanner.close();
                    System.exit(0);
            }
        }
    }

}

//员工类
class Employee{
    public final int id;
    private final String name;
    public Employee next;

    public Employee(int id , String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//员工链表类
class EmployeeLinkedList{
    private Employee head;

    // 假设id是从小到大分配的，则直接将emp加到最后
    public void add(Employee emp){
        //如果是第一个员工，id=1,则做头节点
        if (head == null){
            head = emp;
            return;
        }
        //定义一个临时变量temp
        Employee temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = emp;
    }

    //通过id查找节点
    public Employee getById(int id){
        if (head == null){
            System.out.println("链表空");
            return null;
        }
        Employee temp = head;
        while (temp != null){
            if (temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    //遍历链表
    public void showList(int no){
        if (head == null){
            System.out.println("第"+no+"链表为空");
            return;
        }
        System.out.print("第"+no+"链表的信息:  ");
        Employee temp = head;
        while (temp != null){
            System.out.print(temp);
            temp = temp.next;
        }
        System.out.println();
    }
}

//定义哈希表(数组+链表)
class HashTable{
    private final EmployeeLinkedList[] empArray;
    private final int size;

    public HashTable(int size){
        this.size = size;
        empArray = new EmployeeLinkedList[size];
        for (int i =0; i < size; i++){
            empArray[i] = new EmployeeLinkedList();
        }
    }

    // 添加成员到hashTable
    public void add(Employee emp){
        int empArrayNo = hashFunc(emp.id);
        empArray[empArrayNo].add(emp);
    }

    // 通过id查找hashTable中的某员工
    public void findById(int id){
        int empArrayNo = hashFunc(id);
        Employee employee = empArray[empArrayNo].getById(id);
        if (employee == null){
            System.out.println("没有找到id为"+id+"的员工");
        }else{
            System.out.println("id为"+id+"的员工的信息："+ employee);
        }
    }

    //遍历hashtable
    public void showList(){
        for (int i = 0; i < size; i++){
            empArray[i].showList(i);
        }
    }

    // 定义散列函数
    public int hashFunc(int id){
        return id % size;  //取模法
    }
}