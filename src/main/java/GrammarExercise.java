import java.util.*;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";
        Scanner in = new Scanner(System.in);
        firstWordList = in.nextLine();
        secondWordList = in.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        for (String resultString:result)
            System.out.println(resultString);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<Character> charList = new ArrayList<>();
        for (char ch : firstWordList.toCharArray())
            charList.add(ch);
        for (char ch : secondWordList.toCharArray())
            charList.add(ch);
        long count = charList.stream().map(Character::toLowerCase).filter(ch -> ch != ',' && (ch > 'z' || ch < 'a')).count();
        if (count > 0) throw new RuntimeException("input not valid");
        List<String> firstLineWordList = Arrays.asList(firstWordList.split(","));
        List<String> secondLineWordList = Arrays.asList(secondWordList.split(","));
        count = firstLineWordList.stream().filter(string -> string.isEmpty()).count() + secondLineWordList.stream().filter(string -> string.isEmpty()).count();
        if (count > 0) throw new RuntimeException("input not valid");
        Set<String> firstWorldSet = firstLineWordList.stream().map(String::toUpperCase).collect(Collectors.toSet());
        List<String> repeatWordList = secondLineWordList.stream().map(String::toUpperCase).distinct().filter(string -> firstWorldSet.contains(string)).sorted().map(string -> string.replaceAll("(.{1})", "$1 ").trim()).collect(Collectors.toList());
        return repeatWordList;
    }
}
