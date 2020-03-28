import com.snow.stack.LinkStack;
import com.snow.stack.MyResizingArrayStack;
import org.junit.Test;

import java.util.Iterator;

public class TestStack {
    @Test
    public void testArrayStack(){
        MyResizingArrayStack<Integer> integers = new MyResizingArrayStack<>();

        integers.push(1);
        integers.push(2);
        integers.push(3);
        integers.push(4);
        integers.push(5);
        System.out.println(integers.size());


        System.out.println(integers.pop());
        System.out.println(integers.pop());

        System.out.println(integers.size());
        for (Integer integer : integers) {
            System.out.println("debug");
            System.out.println(integer);
        }
        Iterator<Integer> iterator = integers.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
    @Test
    public void testLinkStack(){
        LinkStack<String> stringStack = new LinkStack<>();
        System.out.println("判空测试===>"+stringStack.isEmpty());
        System.out.println("进栈测试====>");
        stringStack.push("我和我的祖国");
        stringStack.push("一刻也不能分离");
        stringStack.push("每当我走在");
        stringStack.push("永远的歌");
        System.out.println("size()===>"+stringStack.size());

        for (String s : stringStack) {
            System.out.println(s);
        }
        System.out.println("出栈测试=====>");
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());



    }


}
