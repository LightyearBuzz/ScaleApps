package com.github.lightyear;

public class View {
    //выводит приглашение на ввод параметров
    void showEnterConsoleParameters(){
        System.out.println("Enter parameters.");
        System.out.println("Example: mul 6 7");
    }

    //выводит сообщение о некорректных аргументах
    void showEnterCorrectArguments(){
        System.out.println("Enter correct Arguments");
    }

    //выводит результат
    void showResult(String res){
        System.out.println(res);
    }

    //выводит сообщение о некорректных инструкциях
    void showInputFileHasIncorrectInstructions() {
        System.out.println("input file has incorrect instructions");
    }
}