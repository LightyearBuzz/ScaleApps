//test-app консольное приложение способное производить
//арифметические действия, и выводить результат в консоль или в фаил

package com.github.lightyear;

import java.util.Scanner;

//класс реализует шаблон контроллер, тоесть является посредником между
//ползователем и моделью. Если пользователь при запуске передает аргумент
// - то программа выполняет ввод и вывод из косоли. А если указаны input
// и output файлы, ввод и вывод происходит в файл.
public class Controller {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Scanner scanner = new Scanner(System.in);
        String request = null;
        String result = null;

        if (args[0].equals("-")) {
            view.showEnterConsoleParameters();//выводит приглашение на ввод параметров
            request = scanner.nextLine();//читает строку из консоли
            result = model.parseConsolRequest(request);//обрабатывает полученный запрос
            while(!model.getIsActionCorrect()){
                view.showEnterConsoleParameters();//выводит приглашение на ввод параметров
                request = scanner.nextLine();//читаем строку из консоли
                result = model.parseConsolRequest(request);//обрабатываем полученный запрос
            }
            view.showResult(result);//выводим результат
        } else {
            model.parseFileRequest(args);//обрабатываем запрос
        }
    }
}